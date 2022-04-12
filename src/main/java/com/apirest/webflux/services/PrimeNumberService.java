package com.apirest.webflux.services;

import java.util.ArrayList;

import reactor.core.publisher.Flux;

public interface PrimeNumberService {

	ArrayList<Integer> findPrimeNumbers(Integer firstNumber, Integer lastNumber);
	Flux<Integer> findPrimeNumbersReactive(Integer firstNumber, Integer lastNumber);
	Flux<Integer> streamPrimeNumbers(Integer firstNumber, Integer lastNumber);
	
}
