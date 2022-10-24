package com.example.demo.controllers;

import com.example.demo.models.ReportetotalesModel;
import com.example.demo.models.RepotepedidoproductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


@CrossOrigin(origins = "http://localhost:4200",maxAge = 10600)
@RestController
public class RepotepedidoproductoController {

  @Autowired
  public DataSource dataSource;



  //----------------------------------------------------------------------REPORTE VENTA DEL DIA --cajero -- todos los productos
  @GetMapping(path = "/reporte/cajero/{idempleado}" )
  public List<RepotepedidoproductoModel> reportediarioCajero(@PathVariable String idempleado ){
    List<RepotepedidoproductoModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT  ( row_number()OVER(ORDER BY nombreproducto)) as idproducto, " +
        "(SELECT pr.nombreproducto FROM producto pr WHERE pr.idproducto = pp.idproducto )as nombreproducto ,  SUM(pp.cantidad) AS totalcantidad,  " +
        "SUM(pp.preciounitario*pp.cantidad) AS totalprecio , " +
        "ROUND((SUM(pp.cantidad)/(SELECT SUM(xpp.cantidad)FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as xp, " +
                                                "( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as xpp , pedido xpe " +
                                              "WHERE xp.idpedido = xpp.idpedido  AND xpe.idpedido = xp.idpedido  AND xp.fecha = CURDATE() AND xpe.idempleado = pe.idempleado)),3 )*100 as 'xzencantidad',  " +
        "ROUND((SUM(pp.preciounitario*pp.cantidad)/(SELECT sum(xp.preciototal) FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as xp, " +
                                                  "( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as xpp , pedido xpe " +
                                                  "WHERE xp.idpedido = xpp.idpedido AND xpe.idpedido = xp.idpedido AND xp.fecha = CURDATE() AND xpe.idempleado = pe.idempleado)),3 )*100 as 'xzenprecio' " +
        "FROM pedidoproducto as pp, pedido pe, " +
              "(SELECT x.id, x.idcliente, x.idpedido, x.preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago as x ) as p " +
        "WHERE pp.idpedido = pe.idpedido " +
              "AND pe.idempleado = '"+idempleado+"' " +
              "AND pp.idpedido = p.idpedido AND p.fecha = CURDATE()" +
        "GROUP BY  pp.idproducto " +
        "ORDER BY nombreproducto" +""
      );
      while (rs.next()){
        RepotepedidoproductoModel reporte= new RepotepedidoproductoModel();
        reporte.idproducto = rs.getInt("idproducto");
        reporte.nombreproducto = rs.getString("nombreproducto");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        reporte.xzencantidad = rs.getFloat("xzencantidad");
        reporte.xzenprecio = rs.getFloat("xzenprecio");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }

//----------------------------------------------------------------------REPORTE VENTA DEL DIA --cajero -- TOTALES
  @GetMapping(path = "/reporte/cajerototales/{idempleado}" )
  public List<ReportetotalesModel> reportediarioCajeroTotales(@PathVariable String idempleado ){
    List<ReportetotalesModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT ( row_number()OVER(ORDER BY nombreempleado )) as num, " +
        "pe.idempleado,(select concat(e.nombre,' ',e.apellidos) from empleado e where e.ci = pe.idempleado) nombreempleado, " +
        "COUNT(p.idpedido) as totalpedidos , SUM(pp.cantidad) as totalcantidad, SUM(p.preciototal) totalprecio, date_format(p.fecha, \"%Y-%m-%d\") as fecha " +
        "FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as p, ( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as pp , pedido pe " +
        "WHERE p.idpedido = pp.idpedido and pe.idpedido = p.idpedido and p.fecha = CURDATE() " +
        "and pe.idempleado = "+idempleado+" " +
        "GROUP BY pe.idempleado ORDER BY nombreempleado;" );

      while (rs.next()){
        ReportetotalesModel reporte= new ReportetotalesModel();
        reporte.num = rs.getInt("num");
        reporte.idempleado = rs.getInt("idempleado");
        reporte.nombreempleado = rs.getString("nombreempleado");
        reporte.totalpedidos = rs.getInt("totalpedidos");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        reporte.fecha = rs.getString("fecha");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }

  //*****************************************************************************************************************/
  //----------------------------------------------------------------------REPORTE VENTA DEL DIA -ADMIN-- TOTALES
  @GetMapping(path = "/reporte/admintotales" )
  public List<ReportetotalesModel> reportediarioAdminTotal(){
    List<ReportetotalesModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT COUNT(p.idpedido) as totalpedidos, SUM(pp.cantidad) as totalcantidad, SUM(p.preciototal) totalprecio, date_format(p.fecha, \"%Y-%m-%d\") as fecha " +
        "FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as p, " +
        "( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as pp , pedido pe " +
        "WHERE p.idpedido = pp.idpedido and pe.idpedido = p.idpedido and p.fecha = CURDATE()"
        +
        "" );

      while (rs.next()){
        ReportetotalesModel reporte= new ReportetotalesModel();
        //reporte.num = rs.getInt("num");
        //reporte.idempleado = rs.getInt("idempleado");
        //reporte.nombreempleado = rs.getString("nombreempleado");
        reporte.totalpedidos = rs.getInt("totalpedidos");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        reporte.fecha = rs.getString("fecha");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }



  //----------------------------------------------------------------------REPORTE VENTA DEL DIA -ADMIN-- TOTALES por CAJERO
  @GetMapping(path = "/reporte/admintotales/cajero" )
  public List<ReportetotalesModel> reportediarioAdminTotalesporcajero(){
    List<ReportetotalesModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT ( row_number()OVER(ORDER BY nombreempleado )) as num, " +
        "pe.idempleado,(select concat(e.nombre,' ',e.apellidos) from empleado e where e.ci = pe.idempleado) nombreempleado, " +
        "COUNT(p.idpedido) as totalpedidos , SUM(pp.cantidad) as totalcantidad, SUM(p.preciototal) totalprecio, date_format(p.fecha, \"%Y-%m-%d\") as fecha " +
        "FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as p, ( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as pp , pedido pe " +
        "WHERE p.idpedido = pp.idpedido and pe.idpedido = p.idpedido and p.fecha = CURDATE() " +
        "GROUP BY pe.idempleado ORDER BY nombreempleado;" );

      while (rs.next()){
        ReportetotalesModel reporte= new ReportetotalesModel();
        reporte.num = rs.getInt("num");
        reporte.idempleado = rs.getInt("idempleado");
        reporte.nombreempleado = rs.getString("nombreempleado");
        reporte.totalpedidos = rs.getInt("totalpedidos");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        reporte.fecha = rs.getString("fecha");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }

  //----------------------------------------------------------------------REPORTE VENTA DEL DIA --ADMIN -- TOTALES por producto
  @GetMapping(path = "/reporte/admintotales/producto" )
  public List<RepotepedidoproductoModel> reportediarioCajero( ){
    List<RepotepedidoproductoModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT  ( row_number()OVER(ORDER BY nombreproducto)) as idproducto, " +
            "(SELECT pr.nombreproducto FROM producto pr WHERE pr.idproducto = pp.idproducto )as nombreproducto , " +
            "SUM(pp.cantidad) AS totalcantidad, SUM(pp.preciounitario*pp.cantidad) AS totalprecio , " +
            "ROUND((SUM(pp.cantidad)/(SELECT SUM(xpp.cantidad) " +
                                     "FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as xp, " +
                                      "( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as xpp , pedido xpe " +
                                      "WHERE xp.idpedido = xpp.idpedido AND xpe.idpedido = xp.idpedido AND xp.fecha = CURDATE() )),3 ) * 100 as 'xzencantidad',  " +
            "ROUND((SUM(pp.preciounitario*pp.cantidad)/( SELECT sum(xp.preciototal) FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as xp, ( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as xpp , pedido xpe WHERE xp.idpedido = xpp.idpedido AND xpe.idpedido = xp.idpedido AND xp.fecha = CURDATE() )),3 ) * 100 as 'xzenprecio' FROM pedidoproducto as pp,  pedido pe, (SELECT x.id, x.idcliente, x.idpedido, x.preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago as x ) as p WHERE pp.idpedido = pe.idpedido AND pp.idpedido = p.idpedido AND p.fecha = CURDATE() GROUP BY  pp.idproducto ORDER BY nombreproducto;\n"
      );
      while (rs.next()){
        RepotepedidoproductoModel reporte= new RepotepedidoproductoModel();
        reporte.idproducto = rs.getInt("idproducto");
        reporte.nombreproducto = rs.getString("nombreproducto");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        reporte.xzencantidad = rs.getFloat("xzencantidad");
        reporte.xzenprecio = rs.getFloat("xzenprecio");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }


  //***********************************
  //----------------------------------------------------------------------REPORTE VENTA ENTRE FECHAS A Y B--ADMIN -- TOTALES Sumados

  @GetMapping(path = "/reporte/admintotales/{fechaA}/{fechaB}")
  public List<ReportetotalesModel> reporteFechasAbAdminTotal(@PathVariable String fechaA, @PathVariable String fechaB ){
    List<ReportetotalesModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT COUNT(p.idpedido) as totalpedidos, SUM(pp.cantidad) as totalcantidad, SUM(p.preciototal) totalprecio " +
        "FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as p, " +
              "(select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as pp , pedido pe " +
        "WHERE p.idpedido = pp.idpedido AND pe.idpedido = p.idpedido " +
        "AND p.fecha BETWEEN '"+fechaA+"' AND '"+fechaB+"'  " +
        "" );

      while (rs.next()){
        ReportetotalesModel reporte= new ReportetotalesModel();
        //reporte.num = rs.getInt("num");
        //reporte.idempleado = rs.getInt("idempleado");
        //reporte.nombreempleado = rs.getString("nombreempleado");
        reporte.totalpedidos = rs.getInt("totalpedidos");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        //reporte.fecha = rs.getString("fecha");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }

  //----------------------------------------------------------------------REPORTE VENTA ENTRE FECHAS A Y B--ADMIN -- TOTALES Sumados

    @GetMapping(path = "/reporte/admintotales/cajero/{fechaA}/{fechaB}")
  public List<ReportetotalesModel> reporteFechasPorCajerosAdminTotal(@PathVariable String fechaA, @PathVariable String fechaB ){
    List<ReportetotalesModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT ( row_number()OVER(ORDER BY nombreempleado )) as num, pe.idempleado," +
        "(select concat(e.nombre,' ',e.apellidos) from empleado e where e.ci = pe.idempleado) nombreempleado, " +
        "    COUNT(p.idpedido) as totalpedidos, " +
        "    SUM(pp.cantidad) as totalcantidad, " +
        "    SUM(p.preciototal) totalprecio" +
        "    " +
        "FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as p, " +
        " (select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as pp , \n" +
        "     pedido pe " +
        "WHERE p.idpedido = pp.idpedido " +
        "    AND pe.idpedido = p.idpedido " +
        "    AND p.fecha BETWEEN '"+fechaA+"' AND '"+fechaB+"' " +
        "    GROUP BY pe.idempleado\n" +
        "    ORDER BY nombreempleado" +
        "" );

      while (rs.next()){
        ReportetotalesModel reporte= new ReportetotalesModel();
        reporte.num = rs.getInt("num");
        reporte.idempleado = rs.getInt("idempleado");
        reporte.nombreempleado = rs.getString("nombreempleado");
        reporte.totalpedidos = rs.getInt("totalpedidos");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        //reporte.fecha = rs.getString("fecha");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }

  //----------------------------------------------------------------------REPORTE VENTA ENTRE FECHAS --ADMIN -- TOTALES por producto
  @GetMapping(path = "/reporte/admintotales/fechas/producto/{fechaA}/{fechaB}" )
  public List<RepotepedidoproductoModel> reporteFechasAdminProductos(@PathVariable String fechaA, @PathVariable String fechaB ){
    List<RepotepedidoproductoModel> result = new ArrayList<>();
    try{
      Connection conn = dataSource.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("" +
        "SELECT  ( row_number()OVER(ORDER BY nombreproducto)) as idproducto, \n" +
        "\t\t(SELECT pr.nombreproducto FROM producto pr WHERE pr.idproducto = pp.idproducto )as nombreproducto , \n" +
        "        SUM(pp.cantidad) AS totalcantidad, \n" +
        "        SUM(pp.preciounitario*pp.cantidad) AS totalprecio , \n" +
        "        ROUND((SUM(pp.cantidad)/(SELECT SUM(xpp.cantidad) \n" +
        "                                 FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as xp, \n" +
        "                                 ( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as xpp , \n" +
        "                                 pedido xpe \n" +
        "                                 WHERE xp.idpedido = xpp.idpedido \n" +
        "                                 AND xpe.idpedido = xp.idpedido \n" +
        "                                 AND xp.fecha BETWEEN '"+fechaA+"' AND '"+fechaB+"' \n" +
        "                                )\n" +
        "              ),3 ) * 100 as 'xzencantidad', \n" +
        "\t\tROUND((SUM(pp.preciounitario*pp.cantidad)/( SELECT sum(xp.preciototal) \n" +
        "                                                   FROM (SELECT id, idcliente, idpedido, preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago ) as xp, \n" +
        "                                                   ( select idpedido, sum(cantidad) as cantidad from pedidoproducto GROUP BY idpedido ) as xpp , \n" +
        "                                                   pedido xpe \n" +
        "                                                   WHERE xp.idpedido = xpp.idpedido \n" +
        "                                                   AND xpe.idpedido = xp.idpedido \n" +
        "                                                   AND xp.fecha BETWEEN '"+fechaA+"' AND '"+fechaB+"' )               \n" +
        "              ),3 ) * 100 as 'xzenprecio' \n" +
        "FROM pedidoproducto as pp, \n" +
        "\t pedido pe, \n" +
        "     (SELECT x.id, x.idcliente, x.idpedido, x.preciototal, date_format(fecha, \"%Y-%m-%d\") as fecha FROM pago as x ) as p \n" +
        "WHERE pp.idpedido = pe.idpedido \t\n" +
        "    AND pp.idpedido = p.idpedido \n" +
        "    AND p.fecha BETWEEN '"+fechaA+"' AND '"+fechaB+"' \n" +
        "GROUP BY  pp.idproducto \n" +
        "ORDER BY nombreproducto;" +
        ""
      );
      while (rs.next()){
        RepotepedidoproductoModel reporte= new RepotepedidoproductoModel();
        reporte.idproducto = rs.getInt("idproducto");
        reporte.nombreproducto = rs.getString("nombreproducto");
        reporte.totalcantidad = rs.getInt("totalcantidad");
        reporte.totalprecio = rs.getFloat("totalprecio");
        reporte.xzencantidad = rs.getFloat("xzencantidad");
        reporte.xzenprecio = rs.getFloat("xzenprecio");
        result.add(reporte);
      }
      conn.close();
    } catch (Exception ex){
      ex.printStackTrace();
    }
    return result;
  }



}
