package com.mx.sistemaAgenciaAutos.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.sistemaAgenciaAutos.dao.MarcasDao;
import com.mx.sistemaAgenciaAutos.dao.ModelosDao;
import com.mx.sistemaAgenciaAutos.model.Marcas;
import com.mx.sistemaAgenciaAutos.model.Modelos;

@Service
public class ModelosImp {

	@Autowired
	ModelosDao modelosDao;

	@Autowired
	MarcasDao marcasDao;

	@Transactional
	public List<Modelos> listar() {
		return modelosDao.findAll();
	}

	// Validar: IdModelo, nombre no exista, id Marca exista, guardar
	@Transactional
	public String guardar(Modelos modelo) {
		String respuesta = "";
		boolean banderaMar = false;
		boolean banderaMod = false;

		for (Marcas m : marcasDao.findAll()) {
			if (m.getId().equals(modelo.getMarca().getId())) {
				// Que existe el IdMarca
				banderaMar = true;
				for (Modelos mo : modelosDao.findAll()) {
					if (mo.getId().equals(modelo.getId())) {
						// Que modelo ya existe
						respuesta = "idModeloExiste";
						banderaMod = true;
						break;

					} else if (mo.getNombre().equals(modelo.getNombre())) {
						// Que nombre ya existe
						respuesta = "nombreModExiste";
						banderaMod = true;
						break;
					}

				}
				break;

			}

		}
		// idMarcaNoExiste
		if (banderaMar == false) {
			respuesta = "idMarNoExiste";
			banderaMod = true;

		} else if (banderaMod == false) {
			modelosDao.save(modelo);
			respuesta = "guardado";
		}

		return respuesta;
	}

	@Transactional(readOnly = true)
	public Modelos buscar(Long id) {
		for (Modelos modelo : modelosDao.findAll()) {
			if (modelo.getId().equals(id)) {
				return modelo;
			}
		}
		return null;
	}

	@Transactional
	public String editar(Modelos modelo) {
		List<Modelos> listaModelos = modelosDao.findAll();
		List<Marcas> listaMarcas = marcasDao.findAll();

		boolean modeloExiste = false;
		boolean marcaExiste = false;

		// Verificar si el modelo existe
		for (Modelos m : listaModelos) {
			if (m.getId().equals(modelo.getId())) {
				modeloExiste = true;

				// Verificar si la marca existe
				for (Marcas marca : listaMarcas) {
					if (marca.getId().equals(modelo.getMarca().getId())) {
						marcaExiste = true;

						// Si se cumpken las condiciones, act
						m.setNombre(modelo.getNombre());
						m.setTipo(modelo.getTipo());
						m.setPrecio(modelo.getPrecio());
						m.setFechaLanz(modelo.getFechaLanz());
						m.setMarca(marca); 
						modelosDao.save(m);
						return "actualizado"; 
					}
				}
				break; 
			}
		}

	
		if (!modeloExiste) {
			return "idModeloNoExiste"; // El modelo no existe
		}
		if (!marcaExiste) {
			return "idMarNoExiste"; // La marca no existe
		}

		return "error"; 
	}

	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera = false; // Bandera para verificar la eliminación

		// Iterar sobre todos los modelos para buscar el ID
		for (Modelos m : modelosDao.findAll()) {
			if (m.getId().equals(id)) {
				modelosDao.deleteById(id); 
				bandera = true; // Marcar que la eliminación hecha
				break;
			}
		}

		return bandera;
	}

}
