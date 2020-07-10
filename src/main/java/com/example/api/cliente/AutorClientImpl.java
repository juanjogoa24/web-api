package com.example.api.cliente;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.articulo.Autor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class AutorClientImpl implements AutorClient {

	private static final String URL_API_BOOKS = "http://localhost:3000/usuario";

	public List<Autor> obtenerAutores() throws JsonMappingException, JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		
		 return restTemplate.exchange(URL_API_BOOKS, HttpMethod.GET, null,   new ParameterizedTypeReference<List<Autor>>() {}).getBody();

//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//		String jsonStr = restTemplate.exchange(URL_API_BOOKS, HttpMethod.GET, entity, String.class).getBody();
//
//		ObjectMapper mapper = new ObjectMapper();

//		return lista;

	}

}
