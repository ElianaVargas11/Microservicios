package com.example.demo.repositories;

import com.example.demo.models.EmpleadoModel;
import com.example.demo.models.PedidoModel;
import com.example.demo.models.PedidoproductoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.*;

@Repository
public interface PedidoproductoRepository extends CrudRepository<PedidoproductoModel, Long> {

    //public abstract ArrayList<PedidoproductoModel> findByIdpedidoAndIdproducto (Integer idpedido, Integer idproducto);
    public PedidoproductoModel findByIdpedidoAndIdproducto (Integer idpedido, Integer idproducto);
    public abstract ArrayList<PedidoproductoModel> findAllByIdpedidoOrderByNombreproducto (Integer idpedido);
//----------------
    @Query(
      value = "SELECT 0 as id, 0 as idpedido, 0 as idproducto, SUM(PP.cantidad) as cantidad, 'total' as nombreproducto, SUM(pp.preciounitario*pp.cantidad) as preciounitario FROM pedidoproducto pp WHERE idpedido = :filtroidpedido",
      nativeQuery = true
    )
    PedidoproductoModel totalCantidadPrecioPedido(Integer filtroidpedido);


//----------------para reporte de productos mas vendidos entre fecha1 y fecha2
  @Query(
    value = "SELECT 0 AS id, 0 AS idpedido, 0 AS idproducto, SUM(pp.cantidad) AS cantidad, pp.nombreproducto, SUM(pp.preciounitario*pp.cantidad) AS preciounitario FROM pedidoproducto as pp, pago as p WHERE pp.idpedido = p.idpedido AND p.fecha like %?1%  GROUP BY pp.nombreproducto",
    nativeQuery = true
  )
  ArrayList<PedidoproductoModel> productosMasVendidosEntreFechas(String filtroa, String filtrob);



}
