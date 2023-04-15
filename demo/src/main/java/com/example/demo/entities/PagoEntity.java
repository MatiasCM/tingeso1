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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pago;

    private String quincena;
    private String codigo_proveedor;
    private String nombre_proveedor;
    private Integer total_kls;
    private Integer dias;
    private Integer promedio_kls;
    private Integer variacion_leche;
    private Integer grasa;
    private Integer variacion_grasa;
    private Integer solidos_totales;
    private Integer variacion_st;
    private Integer pago_leche;
    private Integer pago_grasa;
    private Integer pago_st;
    private Integer bono;
    private Integer dcto_leche;
    private Integer dcto_grasa;
    private Integer dcto_st;
    private Integer total;
    private Integer monto_retencion;
    private Integer monto_final;
}
