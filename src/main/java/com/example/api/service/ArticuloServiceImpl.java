package com.example.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.articulo.Articulo;
import com.example.api.articulo.ArticuloWeb;
import com.example.api.articulo.Autor;
import com.example.api.cliente.ArticuloFeignClient;
import com.example.api.cliente.AutorClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Service
public class ArticuloServiceImpl implements ArticuloService {

	@Autowired
	private ArticuloFeignClient clientArticulo;
	
	@Autowired
	private AutorClient autorClient;
	
	@Override
	public Iterable<ArticuloWeb> listarArticulos() throws JsonMappingException, JsonProcessingException {

		//simula que viene de node
		
//		Autor a1 = new Autor(1L,"Jhon Smith", "@jhonsmith", "http://www.johns-blog.com/", 1L);
//		Autor a2 = new Autor(2L, "Max Rules", "@max_rules", "http://www.johns-blog.com/",2L);
//		
//		List<Autor> listaAutores = Arrays.asList(a1,a2);
		//obtiene datos de servicio autores
		List<Autor> listaAutores = autorClient.obtenerAutores();
		// obtiene datos de microservicio articulo
		List<Articulo> articulos = clientArticulo.listarArticulos();
		
		listaAutores = listaAutores.stream().map(r -> {
			articulos.forEach(p ->{
				
				if(p.getId() == r.getId()) {
					r.setTitulo(p.getNombre());
				}
			});
			return r;
		}).collect(Collectors.toList());
		
		List<ArticuloWeb> ArticulosWeb = listaAutores.stream()
				.map(r-> new ArticuloWeb(r.getId(), r.getTitulo(), r.getNombre(), r.getTwiter(), r.getBlog()))
				.collect(Collectors.toList());

				
		return ArticulosWeb;
	}

}
