package com.example.api.cliente;

import java.util.List;

import com.example.api.articulo.Autor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface AutorClient {
	
	public List<Autor> obtenerAutores() throws JsonMappingException, JsonProcessingException;

}
