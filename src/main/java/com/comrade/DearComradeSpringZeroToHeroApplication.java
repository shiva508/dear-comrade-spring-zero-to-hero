package com.comrade;

import com.azure.messaging.eventhubs.EventProcessorClientBuilder;
import com.azure.messaging.eventhubs.checkpointstore.blob.BlobCheckpointStore;
import com.azure.storage.blob.BlobContainerAsyncClient;
import com.azure.storage.blob.BlobContainerClientBuilder;
import com.comrade.azure.model.NotificationRequest;
import com.comrade.azure.producer.NotificationProducer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;
import java.util.stream.IntStream;

@SpringBootApplication
public class DearComradeSpringZeroToHeroApplication {
	private static final Sinks.Many<Message<String>> many = Sinks.many().unicast().onBackpressureBuffer();

	public static void main(String[] args) {
		SpringApplication.run(DearComradeSpringZeroToHeroApplication.class, args);
	}

//	@Bean
//	public BlobContainerAsyncClient blobContainerAsyncClient() {
//		var blobContainerAsyncClient = new BlobContainerClientBuilder()
//				.connectionString("UseDevelopmentStorage=true")
//				.containerName("sample-container")
//				.buildAsyncClient();
//
//		blobContainerAsyncClient.createIfNotExists().block();
//		return blobContainerAsyncClient;
//	}
//
//	@Bean
//	public BlobCheckpointStore blobCheckpointStore(BlobContainerAsyncClient blobContainerAsyncClient) {
//		return new BlobCheckpointStore(blobContainerAsyncClient);
//	}
//
//	public EventProcessorClientBuilder eventProcessorClientBuilder(BlobCheckpointStore blobCheckpointStore){
//		return new EventProcessorClientBuilder().checkpointStore(blobCheckpointStore);
//	}

	@Bean
	public Supplier<Flux<Message<String>>> supply() {
		return ()->many.asFlux()
				.doOnNext(System.out::println)
				.doOnError(System.out::println);
	}

	@Bean
	public ApplicationRunner applicationRunner(NotificationProducer notificationProducer){
		return args -> {
			//many.emitNext(MessageBuilder.withPayload("Hello World").build(), Sinks.EmitFailureHandler.FAIL_FAST);
			IntStream.range(1,5).boxed().forEach(integer -> {
				NotificationRequest notificationRequest = new NotificationRequest("Shiva_"+integer);
				notificationProducer.sendingNotification(notificationRequest);
			});
		};
	}
}
