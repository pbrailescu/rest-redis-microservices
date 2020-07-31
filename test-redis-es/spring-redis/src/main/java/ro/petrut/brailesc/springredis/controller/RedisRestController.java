package ro.petrut.brailesc.springredis.controller;

import static java.time.LocalDateTime.now;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.petrut.brailesc.springredis.model.DocumentKey;
import ro.petrut.brailesc.springredis.model.ExternalDocument;

@RestController
@RequestMapping("rest/redis")
public class RedisRestController {
    private static final Logger LOGGER = getLogger(RedisRestController.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @PostMapping("save")
    public void saveMessage(@RequestBody ExternalDocument externalDocument) {
        DocumentKey documentKey = DocumentKey.of(externalDocument);
        redisTemplate.boundListOps(documentKey.asKey()).rightPush(now().toString());
    }
}
