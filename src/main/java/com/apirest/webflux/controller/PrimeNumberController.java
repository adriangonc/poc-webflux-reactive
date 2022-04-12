package com.apirest.webflux.controller;

import java.time.Duration;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.services.PrimeNumberService;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
public class PrimeNumberController {
	@Autowired
	PrimeNumberService primeNumberService;

	@GetMapping(value = "/calculate-primes")
	public ArrayList<Integer> getPrimeNumbers(@RequestParam(name = "firstNumber") Integer firstNumber,
			@RequestParam(name = "lastNumber") Integer lastNumber) {
		System.out.println("Streaming event thread: " + Thread.currentThread().getName());
		return primeNumberService.findPrimeNumbers(firstNumber, lastNumber);

	}

	@GetMapping(value = "/calculate-primes/stream-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Integer>> getPrimeNumbersEvents(@RequestParam(name = "firstNumber") Integer firstNumber,
			@RequestParam(name = "lastNumber") Integer lastNumber) {
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Flux<Integer> primeEvents = primeNumberService.findPrimeNumbersReactive(firstNumber, lastNumber);
		System.out.println("Streaming event thread: " + Thread.currentThread().getName());
		return Flux.zip(interval, primeEvents);

	}
	
	@GetMapping(value = "/calculate-primes/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Integer>> getPrimeNumbersStream(@RequestParam(name = "firstNumber") Integer firstNumber,
			@RequestParam(name = "lastNumber") Integer lastNumber) {
		Flux<Long> interval = Flux.interval(Duration.ofMillis(150));
		Flux<Integer> primeEvents = primeNumberService.streamPrimeNumbers(firstNumber, lastNumber);
		System.out.println("Streaming event thread: " + Thread.currentThread().getName());
		return Flux.zip(interval, primeEvents);

	}
	
	@GetMapping(value = "/calculate-primes/stream-no-delay", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Integer> getPrimeNumbersStreamNoDelay(@RequestParam(name = "firstNumber") Integer firstNumber,
			@RequestParam(name = "lastNumber") Integer lastNumber) {
		Flux<Integer> primeEvents = primeNumberService.streamPrimeNumbers(firstNumber, lastNumber);
		System.out.println("Streaming event thread: " + Thread.currentThread().getName());
		return primeEvents;
	}
	
	
}
