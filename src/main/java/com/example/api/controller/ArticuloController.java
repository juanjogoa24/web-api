package com.example.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.articulo.Articulo;
import com.example.api.articulo.ArticuloWeb;
import com.example.api.service.ArticuloService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api/web/articulos")
public class ArticuloController {
	
	 private static final Logger LOG = LoggerFactory.getLogger(ArticuloController.class);
	
	@Autowired
	private ArticuloService articuloService;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private Gson gson;

	@GetMapping("/listar")
	public List<ArticuloWeb> listar() {
//		la respuesta se hizo sencilla
		List<ArticuloWeb> articulos = null;
		try {
			articulos = (List<ArticuloWeb>) articuloService.listarArticulos();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			LOG.error(e.getMessage());
			//falto implementar el error
			return new ArrayList<>();
		}
		if(articulos == null) {
			articulos = new ArrayList<ArticuloWeb>();
		}

		return articulos;
	}
	
	@PostMapping("/adicionar")
	public ResponseEntity<?> crearArticulo( @RequestBody Articulo articulo, BindingResult result) 
			throws InterruptedException, ExecutionException {
		
		ListenableFuture<SendResult<String, String>> resultKafka=null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			//articuloNew = articuloService.save(articulo);
			resultKafka = kafkaTemplate
					.send("articulo", gson.toJson(articulo));
			
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El articulo ha sido creado con éxito!");
		response.put("cliente", resultKafka.get().getProducerRecord().value());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
