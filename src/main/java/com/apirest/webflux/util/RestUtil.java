package com.apirest.webflux.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class RestUtil {

	WebClient client = WebClient.create();
	
	private final String postUrl = "https://webhook.site/2ef599ae-baa7-4217-a11e-0410256913db";

	@SuppressWarnings("unused")
	public void sendGet() {
		WebClient.ResponseSpec responseSpec = client.get()
			    .uri(postUrl)
			    .retrieve();
	}
	
	@SuppressWarnings("unused")
	public void sendPost(String msg) {
		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("key", "test");
		bodyValues.add("msg", msg);

		try {
			String response = client.post()
			    .uri(new URI(postUrl))
			    //.header("Authorization", "Bearer MY_SECRET_TOKEN")
			    .contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.ALL)
			    .body(BodyInserters.fromValue(bodyValues))
			    .retrieve()
			    .bodyToMono(String.class)
			    .block();
			
		System.out.println(response);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
