package com.example.api.service;

import com.example.api.articulo.ArticuloWeb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


public interface ArticuloService {
	
	public Iterable<ArticuloWeb> listarArticulos() throws JsonMappingException, JsonProcessingException;
	
	

}
