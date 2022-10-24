package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
public class PedidoqueryController {

    @Autowired
    public DataSource dataSource;
    //------------------------------------------------------------------------------MAXIMO
    @GetMapping(path = "/pedido/maximo" )
    public PedidoqueryModel catMaximo(){
        PedidoqueryModel result = new PedidoqueryModel();
        try{
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT (max(idpedido)+1) maxi FROM pedido");
            if (rs.next()){
                result.idpedido = rs.getLong("maxi");
                result.idcliente=0;
                result.idempleado=0;
                result.idmesa=0;
                result.fechapedido=""+LocalDate.now();
                result.idestado=0;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
