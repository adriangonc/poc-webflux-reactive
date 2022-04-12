package com.apirest.webflux.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@RestController // Comentado pois agora a aplicação usa a classe PlaylistHandler com funcional
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;

	@GetMapping(value = "/playlist")
	public Flux<Playlist> getPlaylist() {
		return playlistService.findAll();
	};

	@GetMapping(value = "/playlist/{id}")
	public Mono<Playlist> getPlaylistById(@PathVariable String id) {
		return playlistService.findById(id);
	}

	@PostMapping(value = "/playlist")
	public Mono<Playlist> save(@RequestBody Playlist playlist) {
		return playlistService.save(playlist);
	}
	
	@GetMapping(value="/playlis/stream-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistEvents(){
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Flux<Playlist> playlisEvents = playlistService.findAll();
		System.out.println("Streaming event thread: " + Thread.currentThread().getName());
		return Flux.zip(interval, playlisEvents);
		
	}

}
