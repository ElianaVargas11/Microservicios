package com.example.demo.models;
import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idcategoria;
    private String categoriaprod;
    private String activo;

//--------se crea getter ang setters


  public Long getIdcategoria() {
    return idcategoria;
  }

  public void setIdcategoria(Long idcategoria) {
    this.idcategoria = idcategoria;
  }

  public String getCategoriaprod() {
    return categoriaprod;
  }

  public void setCategoriaprod(String categoriaprod) {
    this.categoriaprod = categoriaprod;
  }

  public String getActivo() {
    return activo;
  }

  public void setActivo(String activo) {
    this.activo = activo;
  }
}
