package com.comrade;

import com.comrade.service.GkTopicService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DearComradeSpringZeroToHeroApplication {


    public static void main(String[] args) {
        SpringApplication.run(DearComradeSpringZeroToHeroApplication.class, args);
    }


	@Bean
	public ApplicationRunner applicationRunner(GkTopicService gkTopicService) {
		return args -> {
			gkTopicService.createTopic();
			gkTopicService.view();
		};
	}
}
