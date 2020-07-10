package com.example.api.cliente;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.api.articulo.Articulo;

@FeignClient(name = "articulos-api", url = "localhost:9082/api/articulo")
public interface ArticuloFeignClient {
	
	@GetMapping("/listar")
	public List<Articulo> listarArticulos();

}
