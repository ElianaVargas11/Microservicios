package com.example.demo.repositories;

import com.example.demo.models.PedidoModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface QueryRepository extends CrudRepository<PedidoModel, Long> {

    @Query(
            value = "SELECT (max(idpedido)+1) as idpedido, 0 as idempleado, NOW() as fechapedido, 1 as idestado  FROM pedido",
            nativeQuery = true
    )
    Optional<PedidoModel> maxNativo();

}
