package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "pago")
public class PagoModel {
  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Integer id;

  private Integer facturanumero;
  private Integer idcliente;
  private Integer idpedido;
  private Float preciototal;
  private String fecha;

//--------se crea getter ang setters


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getFacturanumero() {
    return facturanumero;
  }

  public void setFacturanumero(Integer facturanumero) {
    this.facturanumero = facturanumero;
  }

  public Integer getIdcliente() {
    return idcliente;
  }

  public void setIdcliente(Integer idcliente) {
    this.idcliente = idcliente;
  }

  public Integer getIdpedido() {
    return idpedido;
  }

  public void setIdpedido(Integer idpedido) {
    this.idpedido = idpedido;
  }

  public Float getPreciototal() {
    return preciototal;
  }

  public void setPreciototal(Float preciototal) {
    this.preciototal = preciototal;
  }

  public String getFecha() {
    return fecha;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }
}
