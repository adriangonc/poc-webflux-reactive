package com.apirest.webflux.services;

import com.apirest.webflux.document.Playlist;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlaylistService {
	
	Mono<Playlist> save(Playlist playlist);
	
	Flux<Playlist> findAll(); //Retorna um fluxo de Playlist
	
	Mono<Playlist> findById(String id); // Mono retorna somente uma Playlist
	

}
