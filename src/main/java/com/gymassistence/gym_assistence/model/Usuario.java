package com.gymassistence.gym_assistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

private Long identificacion;

private String nombre;

private Integer edad;

private Long numero;

public Usuario() {

}

public Integer getId() {
    return id;
}

public void setId(Integer id) {
    this.id = id;
}

public Long getIdentificacion() {
    return identificacion;
}

public void setIdentificacion(Long identificacion) {
    this.identificacion = identificacion;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public Integer getEdad() {
    return edad;
}

public void setEdad(Integer edad) {
    this.edad = edad;
}

public Long getNumero() {
    return numero;
}

public void setNumero(Long numero) {
    this.numero = numero;
}

}