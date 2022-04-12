package com.apirest.webflux;

/*
@Component
public class MockData implements CommandLineRunner {
	
	private final PlaylistRepository playlistRepository;
	
		MockData(PlaylistRepository playlistRepository) {
			this.playlistRepository = playlistRepository;
		}
		
		@Override
		public void run(String... args) throws Exception {
			playlistRepository.deleteAll().thenMany(
					Flux.just("PROCEDURE", "PLSQL", "Centura", "JSF", "Wicket", "WebSphere", "RichFaces", 
								"Monolito", "Single user System", "Fábrica de software", "Ionic", "Oracle")
					.map(name -> new Playlist(UUID.randomUUID().toString(), name))
					.flatMap(playlistRepository::save))
					.subscribe(System.out::println);
		}

}
*/