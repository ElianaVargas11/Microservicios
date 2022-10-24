package com.example.demo.repositories;

import com.example.demo.models.PagoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends CrudRepository<PagoModel, Integer>  {

  //SELECT (max(idpago)+1)as idpago , (MAX(facturanumero)+1)as facturanumero, 0 as idcliente,0 as idpedido, 0 as importetotal, NOW() as fechaemision FROM pago
 @Query(
        value = "SELECT (max(id)+1)as id , (MAX(facturanumero)+1)as facturanumero, 0 as idcliente,0 as idpedido, 0 as preciototal, NOW() as fecha FROM pago",
        nativeQuery = true
  )
  PagoModel parafactura();


}
