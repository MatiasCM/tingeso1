package com.example.demo;

import com.example.demo.entities.AcopioLecheEntity;
import com.example.demo.repositories.AcopioLecheRepository;
import com.example.demo.services.AcopioLecheService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AcopioLecheServiceTest {

    @Autowired
    private AcopioLecheService acopioLecheService;

    @Autowired
    private AcopioLecheRepository acopioLecheRepository;

    @Test
    public void sumarKls(){
        AcopioLecheEntity acopioLeche = new AcopioLecheEntity();
        ArrayList<AcopioLecheEntity> lista = new ArrayList<>();
        acopioLeche.setId_acopio(1);
        acopioLeche.setProveedor("01001");
        acopioLeche.setFecha("2023/02/10");
        acopioLeche.setTurno("M");
        acopioLeche.setKls_leche(20);
        lista.add(acopioLeche);
        double kls = acopioLecheService.sumarKls(lista);
        assertEquals(20.0,kls);
    }

    @Test
    void klsPorCategoria(){
        double kilos = 1.0;
        String A = "A";
        double suma1 = acopioLecheService.klsPorCategoria(A,kilos);
        assertEquals(700.0,suma1);

        String B = "B";
        double suma2 = acopioLecheService.klsPorCategoria(B,kilos);
        assertEquals(550.0,suma2);

        String C = "C";
        double suma3 = acopioLecheService.klsPorCategoria(C,kilos);
        assertEquals(400.0,suma3);

        String D = "D";
        double suma4 = acopioLecheService.klsPorCategoria(D,kilos);
        assertEquals(250.0,suma4);
    }

    @Test
    void bonoFrecuencia(){
        AcopioLecheEntity acopio1 = new AcopioLecheEntity();
        acopio1.setFecha("2023/02/10");
        acopio1.setKls_leche(20);
        acopio1.setProveedor("01001");
        acopio1.setTurno("M");
        acopioLecheRepository.save(acopio1);
        double bono = acopioLecheService.bonoFrecuencia("01001",20);
        assertEquals(0.0,bono);
    }

    @Test
    void cantidadDias(){
        AcopioLecheEntity acopioLeche = new AcopioLecheEntity();
        acopioLeche.setProveedor("01003");
        acopioLeche.setFecha("2023/02/10");
        acopioLeche.setTurno("M");
        acopioLeche.setKls_leche(20);
        acopioLecheRepository.save(acopioLeche);

        double dias = acopioLecheService.cantidadDias("01003");
        assertEquals(1.0,dias);
    }

    @Test
    void promedioKls(){
        AcopioLecheEntity acopioLeche = new AcopioLecheEntity();
        ArrayList<AcopioLecheEntity> lista = new ArrayList<>();
        acopioLeche.setProveedor("01001");
        acopioLeche.setFecha("2023/02/10");
        acopioLeche.setTurno("M");
        acopioLeche.setKls_leche(20);
        lista.add(acopioLeche);
        double promedio = acopioLecheService.promedioKls("01001",lista);
        assertEquals(20.0,promedio);
    }

    @Test
    void obtenerQuincena(){
        AcopioLecheEntity acopioLeche = new AcopioLecheEntity();
        acopioLeche.setProveedor("01001");
        acopioLeche.setFecha("2023/02/10");
        acopioLeche.setTurno("M");
        acopioLeche.setKls_leche(20);
        acopioLecheRepository.save(acopioLeche);
        String quincena = acopioLecheService.obtenerQuincena("01001");
        assertEquals("2023/02/Q1",quincena);

        AcopioLecheEntity acopioLeche2 = new AcopioLecheEntity();
        acopioLeche2.setProveedor("01002");
        acopioLeche2.setFecha("2023/02/20");
        acopioLeche2.setTurno("M");
        acopioLeche2.setKls_leche(20);
        acopioLecheRepository.save(acopioLeche2);
        String quincena2 = acopioLecheService.obtenerQuincena("01002");
        assertEquals("2023/02/Q2",quincena2);
    }

    @Test
    void obtenerAcopio(){
        AcopioLecheEntity acopio1 = new AcopioLecheEntity();
        acopio1.setFecha("2023/02/10");
        acopio1.setKls_leche(20);
        acopio1.setProveedor("01001");
        acopio1.setTurno("M");
        acopioLecheRepository.save(acopio1);
        ArrayList<AcopioLecheEntity> acopios = acopioLecheService.obtenerAcopio();

        assertEquals(4, acopios.size());
    }

    @Test
    void obtenerPorProveedor(){
        AcopioLecheEntity acopio1 = new AcopioLecheEntity();
        acopio1.setFecha("2023/02/10");
        acopio1.setKls_leche(20);
        acopio1.setProveedor("01001");
        acopio1.setTurno("M");
        acopioLecheRepository.save(acopio1);

        AcopioLecheEntity acopio2 = new AcopioLecheEntity();
        acopio2.setFecha("2023/02/10");
        acopio2.setKls_leche(20);
        acopio2.setProveedor("01001");
        acopio2.setTurno("M");
        acopioLecheRepository.save(acopio2);

        ArrayList<AcopioLecheEntity> acopios = acopioLecheService.obtenerPorProveedor("01001");
        assertEquals(5, acopios.size());
    }

    @Test
    void guardarDataDB(){
        String fecha = "2023/02/10";
        String turno = "T";
        String proveedor = "02002";
        int kls = 20;

        acopioLecheService.guardarDataDB(fecha,turno,proveedor,kls);

        List<AcopioLecheEntity> dataGuardada = acopioLecheRepository.findAll();
        assertEquals(7, dataGuardada.size());

        AcopioLecheEntity data = dataGuardada.get(6);
        assertEquals(fecha, data.getFecha());
        assertEquals(turno, data.getTurno());
        assertEquals(proveedor, data.getProveedor());
        assertEquals(kls, data.getKls_leche());
    }
}
