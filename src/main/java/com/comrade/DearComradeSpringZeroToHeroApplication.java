package com.comrade;

import com.comrade.domine.Topic;
import com.comrade.domine.TopicBuilder;
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
	public ApplicationRunner applicationRunner(){
		return args -> {
            var topic = new Topic(1, "Who is winning");
            System.out.println("topic = " + topic);
            var duplicate = TopicBuilder.builder(topic).topicId(2).build();
            System.out.println("duplicate = " + duplicate);
			var deDuplicate = duplicate.withName("KCR winning").withTopicId(3);
			System.out.println("deDuplicate = " + deDuplicate);
		};
	}

}
