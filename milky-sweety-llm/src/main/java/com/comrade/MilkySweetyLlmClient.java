package com.comrade;

import com.comrade.model.MsRequest;
import com.comrade.model.MsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Consumer;

public class MilkySweetyLlmClient {
    private final RestClient restClient;

    private final WebClient webClient;

    public static Builder builder(){
        return new Builder();
    }

    private MilkySweetyLlmClient(String baseUrl, RestClient.Builder restClientBuilder, WebClient.Builder webClientBuilder){

        Consumer<HttpHeaders> httpHeadersConsumer = httpHeaders -> {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        };

        System.out.println("Base url"+baseUrl);
        this.restClient = restClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeadersConsumer)
                .build();
        this.webClient = webClientBuilder
                .clone()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeadersConsumer)
                .build();
    }

    public MsResponse chant(MsRequest msRequest){
        return this.restClient
                .post()
                .uri("/api/chat")
                .body(msRequest)
                .retrieve()
                .body(MsResponse.class);
    }

    public static class Builder{
        private String baseUrl = "http://localhost:8080";

        private RestClient.Builder restClientBuilder = RestClient.builder();

        private WebClient.Builder webClientBuilder = WebClient.builder();

        public Builder baseUrl(String baseUrl){
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder restClientBuilder(RestClient.Builder restClientBuilder){
            this.restClientBuilder = restClientBuilder;
            return this;
        }

        public Builder webClientBuilder(WebClient.Builder webClientBuilder){
            this.webClientBuilder = webClientBuilder;

            return this;
        }

        public MilkySweetyLlmClient build(){
            return new MilkySweetyLlmClient(this.baseUrl, this.restClientBuilder, this.webClientBuilder);
        }
    }
}