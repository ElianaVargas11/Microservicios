package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
public class ClienteModel {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Integer ci;

  private String nombre;

//--------se crea getter ang setters


  public Integer getCi() {
    return ci;
  }

  public void setCi(Integer ci) {
    this.ci = ci;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
