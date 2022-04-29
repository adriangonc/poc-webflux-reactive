package com.apirest.webflux.services;

import java.util.List;

import reactor.core.publisher.Flux;

public interface PrimeNumberService {

	List<Integer> findPrimeNumbers(Integer firstNumber, Integer lastNumber);
	List<Integer> findPrimeNumbersImperativeMultithread(Integer firstNumber, Integer lastNumber);
	Flux<Integer> findPrimeNumbersReactive(Integer firstNumber, Integer lastNumber);
	Flux<Integer> streamPrimeNumbers(Integer firstNumber, Integer lastNumber);
	
}
