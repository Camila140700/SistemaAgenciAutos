package com.mx.sistemaAgenciaAutos.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "MARCAS_AGENCIA")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter


public class Marcas {
@Id
private Long id;
private String nombre;
private String origen;
private String eslogan;

//Cardinalidad
//1 Marca tiene muchos modelos 

@OneToMany(mappedBy = "marca", cascade = CascadeType.ALL)
@JsonIgnore
List<Modelos> lista = new ArrayList<Modelos>();
}
