package com.example.demo.repositories;

import com.example.demo.entities.AcopioLecheEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AcopioLecheRepository extends JpaRepository <AcopioLecheEntity, Integer> {

    @Query(value = "select * from acopios a where a.proveedor = :proveedor", nativeQuery = true)
    public ArrayList<AcopioLecheEntity> findByProveedor(@Param("proveedor") String proveedor);

    //contar acopios con por turno y proveedor
    @Query(value = "select count(*) from acopios a where a.proveedor = :proveedor and a.turno = :turno", nativeQuery = true)
    public Integer countByProveedorAndTurno(@Param("proveedor") String proveedor, @Param("turno") String turno);

    //obtener acopios con misma fecha por porveedor
    @Query(value = "select * from acopios a where a.proveedor = :proveedor and a.fecha = :fecha", nativeQuery = true)
    public ArrayList<AcopioLecheEntity> findByProveedorAndFecha(@Param("proveedor") String proveedor, @Param("fecha") String fecha);

    //contar cantidad de fechas distintas por proveedor
    @Query(value = "select count(distinct a.fecha) from acopios a where a.proveedor = :proveedor", nativeQuery = true)
    public Integer countFechaByProveedor(@Param("proveedor") String proveedor);

    //obtener todas las quincenas de un proveedor
    @Query(value = "select distinct a.fecha from acopios a where a.proveedor = :proveedor", nativeQuery = true)
    public ArrayList<String> findQuincenaByProveedor(@Param("proveedor") String proveedor);

}
