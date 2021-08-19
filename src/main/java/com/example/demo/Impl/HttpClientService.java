package com.example.demo.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class HttpClientService {
    private WebClient client=WebClient.create("https://jsonplaceholder.typicode.com/");
    public Mono<String> get(String uri){
        return client.get().uri(uri)
                .retrieve()
                .bodyToMono(String.class);
    }
}
