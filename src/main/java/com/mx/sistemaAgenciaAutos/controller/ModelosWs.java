package com.mx.sistemaAgenciaAutos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.sistemaAgenciaAutos.model.Modelos;

import com.mx.sistemaAgenciaAutos.service.ModelosImp;

@RestController
@RequestMapping(path = "ModelosWebServie")
@CrossOrigin
public class ModelosWs {
	@Autowired
	ModelosImp modelosImp;

	// URL: http://localhost:9002/ModelosWebServie/listar
	@GetMapping(path = "listar")
	public List<Modelos> listar() {
		return modelosImp.listar();
	}

	// http://localhost:9002/ModelosWebServie/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Modelos modelo) {
		String response = modelosImp.guardar(modelo);

		if (response.equals("idModeloExiste"))
			return new ResponseEntity<>("No se guardo, ese idModelo ya existe", HttpStatus.OK);
		else if (response.equals("nombreModExiste"))
			return new ResponseEntity<>("No se guardo, ese nombreModelo ya existe", HttpStatus.OK);
		else if (response.equals("idMarNoExiste"))
			return new ResponseEntity<>("No se guardo, ese idMarca no existe", HttpStatus.OK);
		else
			return new ResponseEntity<>(modelo, HttpStatus.CREATED);
	}

	// URL: http://localhost:9002/ModelosWs/buscar
	@PostMapping(path = "buscar")
	public ResponseEntity<?> buscar(@RequestBody Modelos modelo) {
		Modelos modEnc = modelosImp.buscar(modelo.getId());

		if (modEnc == null) {
			return new ResponseEntity<>("Ese modelo no existe", HttpStatus.OK);
		}

		return new ResponseEntity<>(modEnc, HttpStatus.OK);
	}
	
	// URL: http://localhost:9002/ModelosWs/editar
	@PostMapping("editar")
	public ResponseEntity<?> editarModelo(@RequestBody Modelos modelo) {
	    String response = modelosImp.editar(modelo);

	    if (response.equals("idModeloNoExiste")) {
	        return new ResponseEntity<>("No se actualizó, ese id de modelo no existe", HttpStatus.OK);
	    } else if (response.equals("idMarNoExiste")) {
	        return new ResponseEntity<>("No se actualizó, ese id de marca no existe", HttpStatus.OK);
	    } else if (response.equals("actualizado")) {
	        return new ResponseEntity<>(modelo, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	//http://localhost:9002/ModelosWebServie/eliminarMod
	@PostMapping(path = "eliminar")
	public ResponseEntity<String> eliminar(@RequestBody Modelos modelo) {
	    boolean response = modelosImp.eliminar(modelo.getId());

	    if (!response) {
	        return new ResponseEntity<>("Ese registro no existe", HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>("Se eliminó con éxito", HttpStatus.OK);
	    }
	}

}
