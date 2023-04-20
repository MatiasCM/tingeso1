package com.example.demo;

import com.example.demo.entities.PagoEntity;
import com.example.demo.services.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PagoServiceTest {

    PagoEntity pagoEntity = new PagoEntity();

    PagoService pagoService = new PagoService();

    @Test
    void obtenerTotalKls(){
        pagoEntity.setQuincena("2023/03/Q1");
        pagoEntity.setCodigo_proveedor("01001");
        pagoEntity.setNombre_proveedor("Peter Parker");
        pagoEntity.setTotal_kls(90.0);
        pagoEntity.setDias(10.0);
        pagoEntity.setPromedio_kls(80.0);
        pagoEntity.setVariacion_leche(20.0);
        pagoEntity.setGrasa(30.0);
        pagoEntity.setVariacion_grasa(10.0);
        pagoEntity.setSolidos_totales(15.0);
        pagoEntity.setVariacion_st(5.0);
        pagoEntity.setPago_leche(700000.0);
        pagoEntity.setPago_grasa(20000.0);
        pagoEntity.setPago_st(-10000.0);
        pagoEntity.setBono(1000.0);
        pagoEntity.setDcto_leche(10000.0);
        pagoEntity.setDcto_grasa(5000.0);
        pagoEntity.setDcto_st(2000.0);
        pagoEntity.setTotal(674000.0);
        pagoEntity.setMonto_retencion(0.0);
        pagoEntity.setMonto_final(674000.0);
        double total_kls = pagoService.obtenerTotalKls(pagoEntity);
        assertEquals(90.0,total_kls);

        double total_klsNull = pagoService.obtenerTotalKls(null);
        assertEquals(0.0,total_klsNull);

    }

    @Test
    void variacionLeche(){
        double anterior = 100.0;
        double actual1 = 95.0;
        int variacion1 = pagoService.variacionLeche(anterior, actual1);
        assertEquals(0,variacion1);

        double actual2 = 90.0;
        int variacion2 = pagoService.variacionLeche(anterior, actual2);
        assertEquals(7,variacion2);

        double actual3 = 70.0;
        int variacion3 = pagoService.variacionLeche(anterior, actual3);
        assertEquals(15,variacion3);

        double actual4 = 50.0;
        int variacion4 = pagoService.variacionLeche(anterior, actual4);
        assertEquals(30,variacion4);

        double actual5 = 110.0;
        int variacion5 = pagoService.variacionLeche(anterior, actual5);
        assertEquals(0,variacion5);
    }

    @Test
    void obtenerGrasa(){
        pagoEntity.setQuincena("2023/03/Q1");
        pagoEntity.setCodigo_proveedor("01001");
        pagoEntity.setNombre_proveedor("Peter Parker");
        pagoEntity.setTotal_kls(90.0);
        pagoEntity.setDias(10.0);
        pagoEntity.setPromedio_kls(80.0);
        pagoEntity.setVariacion_leche(20.0);
        pagoEntity.setGrasa(30.0);
        pagoEntity.setVariacion_grasa(10.0);
        pagoEntity.setSolidos_totales(15.0);
        pagoEntity.setVariacion_st(5.0);
        pagoEntity.setPago_leche(700000.0);
        pagoEntity.setPago_grasa(20000.0);
        pagoEntity.setPago_st(-10000.0);
        pagoEntity.setBono(1000.0);
        pagoEntity.setDcto_leche(10000.0);
        pagoEntity.setDcto_grasa(5000.0);
        pagoEntity.setDcto_st(2000.0);
        pagoEntity.setTotal(674000.0);
        pagoEntity.setMonto_retencion(0.0);
        pagoEntity.setMonto_final(674000.0);
        double grasa = pagoService.obtenerGrasa(pagoEntity);
        assertEquals(30.0,grasa);

        double grasaNull = pagoService.obtenerGrasa(null);
        assertEquals(0.0,grasaNull);
    }

    @Test
    void variacionGrasa(){
        double anterior = 100.0;
        double actual1 = 90.0;
        int variacion1 = pagoService.variacionGrasa(anterior, actual1);
        assertEquals(0,variacion1);

        double actual2 = 80.0;
        int variacion2 = pagoService.variacionGrasa(anterior, actual2);
        assertEquals(12,variacion2);

        double actual3 = 70.0;
        int variacion3 = pagoService.variacionGrasa(anterior, actual3);
        assertEquals(20,variacion3);

        double actual4 = 50.0;
        int variacion4 = pagoService.variacionGrasa(anterior, actual4);
        assertEquals(30,variacion4);

        double actual5 = 110.0;
        int variacion5 = pagoService.variacionGrasa(anterior, actual5);
        assertEquals(0,variacion5);
    }

    @Test
    void obtenerSt(){
        pagoEntity.setQuincena("2023/03/Q1");
        pagoEntity.setCodigo_proveedor("01001");
        pagoEntity.setNombre_proveedor("Peter Parker");
        pagoEntity.setTotal_kls(90.0);
        pagoEntity.setDias(10.0);
        pagoEntity.setPromedio_kls(80.0);
        pagoEntity.setVariacion_leche(20.0);
        pagoEntity.setGrasa(30.0);
        pagoEntity.setVariacion_grasa(10.0);
        pagoEntity.setSolidos_totales(15.0);
        pagoEntity.setVariacion_st(5.0);
        pagoEntity.setPago_leche(700000.0);
        pagoEntity.setPago_grasa(20000.0);
        pagoEntity.setPago_st(-10000.0);
        pagoEntity.setBono(1000.0);
        pagoEntity.setDcto_leche(10000.0);
        pagoEntity.setDcto_grasa(5000.0);
        pagoEntity.setDcto_st(2000.0);
        pagoEntity.setTotal(674000.0);
        pagoEntity.setMonto_retencion(0.0);
        pagoEntity.setMonto_final(674000.0);
        double st = pagoService.obtenerSt(pagoEntity);
        assertEquals(15.0,st);

        double stNull = pagoService.obtenerSt(null);
        assertEquals(0.0,stNull);
    }

    @Test
    void variacionSt(){
        double anterior = 100.0;
        double actual1 = 95.0;
        int variacion1 = pagoService.variacionSt(anterior, actual1);
        assertEquals(0,variacion1);

        double actual2 = 90.0;
        int variacion2 = pagoService.variacionSt(anterior, actual2);
        assertEquals(18,variacion2);

        double actual3 = 70.0;
        int variacion3 = pagoService.variacionSt(anterior, actual3);
        assertEquals(27,variacion3);

        double actual4 = 50.0;
        int variacion4 = pagoService.variacionSt(anterior, actual4);
        assertEquals(45,variacion4);

        double actual5 = 110.0;
        int variacion5 = pagoService.variacionSt(anterior, actual5);
        assertEquals(0,variacion5);
    }

}
