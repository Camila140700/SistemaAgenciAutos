package com.mx.sistemaAgenciaAutos.model;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="MODELOS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Modelos {
@Id
private Long id;
private String nombre;
private String tipo;
private Float precio;
private Date fechaLanz;

//Cardinalidad
//Muchos modelos pertenecen a una marca
//Fetch-- Indicamos como debe de ser cargada la entidad
//fetchType--Indicamos que la relacion va a ser cargada al momento

@ManyToOne (fetch = FetchType.EAGER)
@JoinColumn(name="ID_MARCA")
Marcas marca;
}
