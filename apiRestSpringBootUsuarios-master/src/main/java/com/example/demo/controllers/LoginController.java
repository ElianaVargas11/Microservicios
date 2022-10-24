package com.example.demo.controllers;

import com.example.demo.models.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
public class LoginController {

    @Autowired
    public DataSource dataSource;

    //-------------------------------------busca un empleado por empleado USUARIO y PASSWORD--------------------GET
    //----------------------------------------------------------------------------------------------------------
    @GetMapping(path = "/empleado/login/{usuario}/{pass}" )
    public LoginModel EmpleadoporUsuarioContrasena (@PathVariable String usuario, @PathVariable String pass){
        LoginModel result = new LoginModel();
        try{
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM empleado " +
                    "WHERE activo = 'si' and `usuario`= '"
                    + usuario + "' "+
                    "and `usuariopass` = '" +
                    pass +
                    "'" );
            if (rs.next()){
                result.usuario=usuario;
                result.ci = rs.getString("ci");
                result.nombre = rs.getString("nombre");
                result.apellidos = rs.getString("apellidos");
                result.rol = rs.getString("rol");
            }
            conn.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

}
