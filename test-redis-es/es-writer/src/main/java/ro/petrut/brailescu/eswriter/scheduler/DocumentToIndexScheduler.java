package ro.petrut.brailescu.eswriter.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ro.petrut.brailescu.eswriter.model.DocumentKey;

@Component
public class DocumentToIndexScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentToIndexScheduler.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Scheduled(fixedRate=5_000)
    public void executeDocumentToIndex() {
        Set<String> keys = redisTemplate.keys("*_N_*");
        if (keys != null) {
            for (String key : keys) {
                List<String> elements = redisTemplate.boundListOps(key).range(0, -1);
                redisTemplate.delete(key);
                // call backend endpoint to get the data
                // if error put in status failed
                // index document
                // if error put in status failed
                // success save with Sent status
                DocumentKey documentKey = DocumentKey.fromKey(key);
                documentKey.setStatus("S");
                elements.forEach(el -> redisTemplate.boundListOps(documentKey.asKey()).rightPush(el));
            }
        }
    }

    @Scheduled(fixedRate = 5_000)
    public void executeDocumentsIndexed() {
        Set<String> keys = redisTemplate.keys("*_S_*");
        if (keys != null) {
            for (String key : keys) {
                List<String> elements = redisTemplate.boundListOps(key).range(0, -1);
                //redisTemplate.delete(key);
                // get last update date in ES and move those with date less than the one from ES in status Visible
                DocumentKey documentKey = DocumentKey.fromKey(key);
                documentKey.setStatus("V");
                elements.forEach(el -> redisTemplate.boundListOps(documentKey.asKey()).rightPush(el));
            }
        }
    }

    @Scheduled(fixedRate = 5 * 1_000)
    public void executeCleanUp() {
        Set<String> keys = redisTemplate.keys("*_S_*");
        if (keys != null) {
            for (String key : keys) {
                List<String> elements = redisTemplate.boundListOps(key).range(0, -1);
                dateFromES = esService.get(documentid);
                for (String element : elements) {
                    if (LocalDateTime.parse(element).plus(30, ChronoUnit.MINUTES).isBefore(LocalDateTime.now())) {
                        LOGGER.info("Remove element {}", element);
                        redisTemplate.boundListOps(key).remove(0, element);
                    }
                }
            }
        }
    }
}
