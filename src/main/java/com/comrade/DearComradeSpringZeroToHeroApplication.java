package com.comrade;

import com.comrade.rabbitmq.model.NotificationRequest;
import com.comrade.rabbitmq.producer.NotificationProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class DearComradeSpringZeroToHeroApplication {

	public static void main(String[] args) {
		SpringApplication.run(DearComradeSpringZeroToHeroApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(NotificationProducer notificationProducer){
		return args -> {
			IntStream.range(1,100).boxed().forEach(integer -> {
				NotificationRequest notificationRequest = new NotificationRequest("Shiva_"+integer);
				notificationProducer.sendingNotification(notificationRequest);
			});

		};
	}
}
