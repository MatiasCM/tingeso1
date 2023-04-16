package com.example.demo.repositories;

import com.example.demo.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PagoRepository extends JpaRepository<PagoEntity, String> {

    //obtener pagos por codigo_proveedor
    @Query(value = "select * from pagos a where a.codigo_proveedor = :codigo_proveedor", nativeQuery = true)
    public ArrayList<PagoEntity> findPagosByCodigoProveedor(@Param("codigo_proveedor") String codigo_proveedor);

    //obtener el ultimo pago de un proveedor por id_pago
    @Query(value = "select * from pagos a where a.codigo_proveedor = :codigo_proveedor order by a.id_pago desc limit 1", nativeQuery = true)
    public PagoEntity findUltimoPagoByCodigoProveedor(@Param("codigo_proveedor") String codigo_proveedor);

}
