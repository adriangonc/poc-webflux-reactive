package com.apirest.webflux.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PrimeNumberServiceImp implements PrimeNumberService {

	/*
	 * A lista de números primos em um intervalo precisa ser calculada de forma
	 * syncrona a inteção desse bechmark e validar se existem ganhos em usar um
	 * servidor async como o netty devido ao fato de não bloquear uma thread por
	 * request
	 */
	@Override
	public ArrayList<Integer> findPrimeNumbers(Integer firstNumber, Integer lastNumber) {

		int low = 0, high = 0;

		ArrayList<Integer> listPrimes = new ArrayList<Integer>();

		if (firstNumber < lastNumber) {
			low = firstNumber;
			high = lastNumber;
		} else {
			low = lastNumber;
			high = firstNumber;
		}

		// java.util.Date date = new java.util.Date();
		// System.out.println(date);

		while (low < high) {
			boolean flag = false;

			for (int i = 2; i <= low / 2; ++i) {
				// condition for nonprime number
				if (low % i == 0) {
					flag = true;
					break;
				}
			}

			if (!flag && low != 0 && low != 1) {
				listPrimes.add(low);
			}

			++low;
		}
		// java.util.Date dateEnd = new java.util.Date();
		// System.out.println("\n" + dateEnd);

		return listPrimes;
	}

	@Override
	public Flux<Integer> findPrimeNumbersReactive(Integer firstNumber, Integer lastNumber) {
		// TODO Auto-generated method stub
		int low = 0, high = 0;

		ArrayList<Integer> listPrimes = new ArrayList<Integer>();

		if (firstNumber < lastNumber) {
			low = firstNumber;
			high = lastNumber;
		} else {
			low = lastNumber;
			high = firstNumber;
		}

		while (low < high) {
			boolean flag = false;

			for (int i = 2; i <= low / 2; ++i) {
				// condition for nonprime number
				if (low % i == 0) {
					flag = true;
					break;
				}
			}

			if (!flag && low != 0 && low != 1) {
				listPrimes.add(low);
			}

			++low;
		}

		return monoTofluxUsingFlatMapMany(Mono.just(listPrimes));
	}

	private <T> Flux<T> monoTofluxUsingFlatMapMany(Mono<List<T>> monoList) {
		return monoList.flatMapMany(Flux::fromIterable);
	}
	
	Flux<Integer> just = Flux.just(populatePrimes(1, 100000));
	
	private Integer populatePrimes(Integer firstNumber, Integer lastNumber) {
		int low = 0, high = 0;
		int lastPrime = 0;

		if (firstNumber < lastNumber) {
			low = firstNumber;
			high = lastNumber;
		} else {
			low = lastNumber;
			high = firstNumber;
		}

		while (low < high) {
			boolean flag = false;

			for (int i = 2; i <= low / 2; ++i) {
				// condition for nonprime number
				if (low % i == 0) {
					flag = true;
					break;
				}
			}

			if (!flag && low != 0 && low != 1) {
				lastPrime = low;
			}

			++low;
		}

		return lastPrime;
	}
	
	
	public Flux<Integer> streamPrimeNumbers(Integer firstNumber, Integer lastNumber) {
		//return StreamPrimes.streamPrimes(firstNumber, lastNumber);
		return StreamPrimes.primesMultiThread(firstNumber, lastNumber);
	}
	

	
}
