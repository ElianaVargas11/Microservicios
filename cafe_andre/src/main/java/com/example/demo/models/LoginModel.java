package com.example.demo.models;

import javax.persistence.Table;

@Table(name = "empleado")
public class LoginModel {
    public String ci;
    public String nombre;
    public String apellidos;
    public String genero;
    public String direccion;
    public String celular;
    public String rol;
    public String fecha_registro;
    public String usuario;
    public String usuario_contraseña;
    public String activo;

    public LoginModel(){
    }
}
