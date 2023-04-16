package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "pagos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoEntity {
    @Id
    //@NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;

    private String quincena;
    private String codigo_proveedor;
    private String nombre_proveedor;
    private double total_kls;
    private double dias;
    private double promedio_kls;
    private double variacion_leche;
    private double grasa;
    private double variacion_grasa;
    private double solidos_totales;
    private double variacion_st;
    private double pago_leche;
    private double pago_grasa;
    private double pago_st;
    private double bono;
    private double dcto_leche;
    private double dcto_grasa;
    private double dcto_st;
    private double total;
    private double monto_retencion;
    private double monto_final;
}
