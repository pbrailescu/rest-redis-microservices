package ro.petrut.brailescu.eswriter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EsWriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsWriterApplication.class, args);
	}

	// reading successful queue
	// based on id get id from redis
	// index to ES
	// set successful result in redis

}
