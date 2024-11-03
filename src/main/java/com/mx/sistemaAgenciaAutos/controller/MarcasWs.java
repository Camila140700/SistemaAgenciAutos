package com.mx.sistemaAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.service.MarcasImp;

@RestController
@RequestMapping(path = "MarcasWebServie")
@CrossOrigin
public class MarcasWs {

	@Autowired
	MarcasImp marcasImp;

	// URL: http://localhost:9002/MarcasWebServie/listar
	@PostMapping(path = "listar")
	public List<Marcas> listar() {
		return marcasImp.listar();
	}

	// URL: http://localhost:9002/MarcasWebServie/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Marcas marca) {
		String response = marcasImp.guardar(marca);

		if (response.equals("idExiste"))
			return new ResponseEntity<>("Ese id ya existe", HttpStatus.OK);
		else if (response.equals("nombreExiste"))
			return new ResponseEntity<>("Ese nombre ya existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(marca, HttpStatus.CREATED);

	}

	// URL: http://localhost:9002/MarcasWebService/buscar
	@PostMapping(path = "buscar")
	public Marcas buscar(@RequestBody Marcas marcas) {
		return marcasImp.buscar(marcas.getId());
	}

	// URL: http://localhost:9002/MarcasWebService/editar
	@PostMapping(path = "editar")
	public ResponseEntity<String> editar(@RequestBody Marcas marca) {
	    boolean response = marcasImp.editar(marca);

	    if (!response) {
	        return new ResponseEntity<>("Ese registro no existe", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Registro editado con éxito", HttpStatus.CREATED);
	    }
	}

	// URL: http://localhost:9002/MarcasWebService/eliminar
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Marcas marca) {
	    boolean response = marcasImp.eliminar(marca.getId());

	    if (!response) {
	        return new ResponseEntity<>("Ese registro no existe", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Se elimino  con éxito", HttpStatus.OK);
	    }
	}

	
}
