package com.apirest.webflux;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;

import reactor.core.publisher.Mono;

//@Component
public class PlaylistHandler {
	@Autowired
	PlaylistService playlistService;

	// Metodos funcionais

	// O tipo de retorno deve ser Mono mesmo que o findAll retorne um Flux,
	// pois a resposta será retornada inteira para a requisição
	public Mono<ServerResponse> findAll(ServerRequest req) {
		return ok().contentType(MediaType.APPLICATION_JSON).body(playlistService.findAll(), Playlist.class);
	}

	public Mono<ServerResponse> findById(ServerRequest req) {
		String id = req.pathVariable("id");
		return ok().contentType(MediaType.APPLICATION_JSON).body(playlistService.findById(id), Playlist.class);
	}

	public Mono<ServerResponse> save(ServerRequest req) {
		final Mono<Playlist> playlist = req.bodyToMono(Playlist.class);
		return ok().contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playlist.flatMap(playlistService::save), Playlist.class));
	}

}
