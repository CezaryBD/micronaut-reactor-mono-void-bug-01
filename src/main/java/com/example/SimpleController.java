package com.example;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import reactor.core.publisher.Mono;

@Controller
public class SimpleController {
	@Inject
	SimpleClient simpleClient;

	@Get("/simple-void")
	public Mono<Void> getSimpleVoid() {
		return simpleClient.getSimpleVoid();
	}

	@Get("/not-simple-void")
	public Mono<HttpResponse<Void>> getNotSimpleVoid() {
		return simpleClient.getNotSimpleVoid();
	}
}
