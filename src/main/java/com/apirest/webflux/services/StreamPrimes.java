package com.apirest.webflux.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import reactor.core.publisher.Flux;

public class StreamPrimes {
	public static void main(String[] args) {
		//List<Integer> numbers = countToMax(1, 99999);
		// Prime number
		//System.out.println(numbers.parallelStream().filter(StreamPrimes::isPrime).collect(toList()) + "\n");
		primesMultiThread(1,9999);

	}
	
	public static Flux<Integer> streamPrimes(Integer firstNumber, Integer lastNumber) {
		List<Integer> numbers = countToMax(firstNumber, lastNumber);
		//System.out.println(numbers.stream().filter(StreamPrimes::isPrime).collect(toList()) + "\n");
		return (Flux.fromStream(numbers.parallelStream().filter(StreamPrimes::isPrime) ));
	}

	public static boolean isPrime(int number) {
		// Even numbers
		if (number % 2 == 0) {
			return number == 2;
		}

		// Odd numbers
		int limit = (int) (0.1 + Math.sqrt(number));
		for (int i = 3; i <= limit; i += 2) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	private static List<Integer> countToMax(Integer firstNumber, Integer lastNumber) {
		List<Integer> numbers = new ArrayList<Integer>();
		
		for (int i = firstNumber; i < lastNumber; i++) {
			numbers.add(i);
		}

		return numbers;
	}
	
	public static Flux<Integer> primesMultiThread(Integer firstNumber, Integer lastNumber){
		int parallelism = 7; 
		int cores = Runtime.getRuntime().availableProcessors(); //Número de Threads de CPU 
		//parallelism = (cores -1);
		
		ForkJoinPool forkJoinPool = null;
		try {
		    forkJoinPool = new ForkJoinPool(parallelism);
		    final List<Integer> primes = forkJoinPool.submit(() ->
		        // Tarefa paralela
		        IntStream.range(firstNumber, lastNumber).parallel()
		                .filter(StreamPrimes::isPrime)
		                .boxed().collect(Collectors.toList())
		    ).get();
		    //System.out.println(primes);
		    return (Flux.fromStream(primes.parallelStream() ));
		} catch (InterruptedException | ExecutionException e) {
		    throw new RuntimeException(e);
		} finally {
		    if (forkJoinPool != null) {
		        forkJoinPool.shutdown(); //Desliga pool de Threads
		    }
		}
		
	}
	
	public static List<Integer> primesMultiThreadImperative(Integer firstNumber, Integer lastNumber){
		int parallelism = 7; 
		int cores = Runtime.getRuntime().availableProcessors(); //Número de Threads de CPU 
		//parallelism = (cores -1);
		
		ForkJoinPool forkJoinPool = null;
		try {
		    forkJoinPool = new ForkJoinPool(parallelism);
		    final List<Integer> primes = forkJoinPool.submit(() ->
		        // Tarefa paralela
		        IntStream.range(firstNumber, lastNumber).parallel()
		                .filter(StreamPrimes::isPrime)
		                .boxed().collect(Collectors.toList())
		    ).get();
		    //System.out.println(primes);
		    return primes;
		} catch (InterruptedException | ExecutionException e) {
		    throw new RuntimeException(e);
		} finally {
		    if (forkJoinPool != null) {
		        forkJoinPool.shutdown(); //Desliga pool de Threads
		    }
		}
		
	}
	
	public static List<Integer> primesSingleThreadImperative(Integer firstNumber, Integer lastNumber){
		
		List<Integer> listPrimes = new ArrayList<Integer>();
		List<Integer> numbers = countToMax(firstNumber, lastNumber);
		
		for (Integer i : numbers) {
			if(isPrime(i)) {
				listPrimes.add(i);
			}
		}
		
		return listPrimes;
	}
	
}