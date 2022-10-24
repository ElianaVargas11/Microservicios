package com.example.demo.models;
import javax.persistence.*;


@Entity
@Table(name = "pedidoproducto")
public class PedidoproductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Integer idpedido;
    private Integer idproducto;
    private Integer Cantidad;
    private String nombreproducto;
    private Float preciounitario;

//--------se crea getter ang setters


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getIdpedido() {
    return idpedido;
  }

  public void setIdpedido(Integer idpedido) {
    this.idpedido = idpedido;
  }

  public Integer getIdproducto() {
    return idproducto;
  }

  public void setIdproducto(Integer idproducto) {
    this.idproducto = idproducto;
  }

  public Integer getCantidad() {
    return Cantidad;
  }

  public void setCantidad(Integer cantidad) {
    Cantidad = cantidad;
  }

  public String getNombreproducto() {
    return nombreproducto;
  }

  public void setNombreproducto(String nombreproducto) {
    this.nombreproducto = nombreproducto;
  }

  public Float getPreciounitario() {
    return preciounitario;
  }

  public void setPreciounitario(Float preciounitario) {
    this.preciounitario = preciounitario;
  }
}
