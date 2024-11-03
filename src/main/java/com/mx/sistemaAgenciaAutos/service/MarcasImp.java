package com.mx.sistemaAgenciaAutos.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.mx.sistemaAgenciaAutos.dao.MarcasDao;
import com.mx.sistemaAgenciaAutos.model.Marcas;

@Service
public class MarcasImp {

	@Autowired
	MarcasDao marcasDao;

	@Transactional
	public List<Marcas> listar() {
		return marcasDao.findAll();
	}

	@Transactional
	public String guardar(Marcas marca) {
	
		String respuesta = "";
		boolean bandera= false;
		
		for (Marcas m : marcasDao.findAll()) {
			if (m.getId().equals(marca.getId())) {
				bandera= true;
				respuesta="idExiste";
				break;
			}else if(m.getNombre().equals(marca.getNombre())) {
				bandera=true;
				respuesta="nombreExiste";
				break;
			}
			
		}
		if(bandera==false) {
			marcasDao.save(marca);
			respuesta= "guardado";
		}
		return respuesta;
	}
	
	@Transactional(readOnly = true)
	public Marcas buscar(Long id) {
	    return marcasDao.findById(id).orElse(null); 
	}
	

	
	@Transactional
	public boolean editar(Marcas marca) {
		boolean bandera= false;
		for (Marcas  m : marcasDao.findAll()) {
			if(m.getId().equals(marca.getId())) {
				marcasDao.save(marca);
				bandera= true;
				break;
			}
		}
			return bandera;

	}
	public boolean eliminar(Long id) {
		boolean bandera=false;
		for(Marcas m: marcasDao.findAll()) {
			if(m.getId().equals(id)) {
				marcasDao.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
	
}
