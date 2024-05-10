package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import reactor.core.publisher.Mono;

@Client("http://localhost:6969")
public interface SimpleClient {
	@Get("/simple-void")
	public Mono<Void> getSimpleVoid();


	@Get("/not-simple-void")
	public Mono<HttpResponse<Void>> getNotSimpleVoid();
}
