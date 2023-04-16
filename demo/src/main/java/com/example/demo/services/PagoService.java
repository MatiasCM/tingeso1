package com.example.demo.services;

import com.example.demo.entities.PagoEntity;
import com.example.demo.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PagoService {

    @Autowired
    PagoRepository pagoRepository;

    //obtener pagos
    public ArrayList<PagoEntity> obtenerPagos(){
        return (ArrayList<PagoEntity>) pagoRepository.findAll();
    }

    //guardar pago
    public void guardarPago(PagoEntity pago){
        pagoRepository.save(pago);
    }

    public void eliminarPagos(){pagoRepository.deleteAll();}

    //obtener pago por codigo_proveedor
    public ArrayList<PagoEntity> obtenerPagosByCodigoProveedor(String codigo_proveedor){
        return pagoRepository.findPagosByCodigoProveedor(codigo_proveedor);
    }

    //de la lista de pagos por codigo obtener el ultimo
    public PagoEntity obtenerUltimoPago(String codigo_proveedor){
        return pagoRepository.findUltimoPagoByCodigoProveedor(codigo_proveedor);
    }

    //obtener kls_leche de un pago
    public double obtenerTotalKls(PagoEntity pago){
        if(pago == null)
            return 0.0;
        else
            return pago.getTotal_kls();
    }

    //calcular variacion negativa de leche
    public int variacionLeche(double klsAnterior, double klsActual){
        int descuento = 0;
        double variacion = ((klsAnterior - klsActual)/klsAnterior)*100;
        if(variacion >= 0 && variacion <= 8){
            descuento = 0;
        }else if(variacion >= 9 && variacion <= 25){
            descuento = 7;
        }else if(variacion >= 26 && variacion <= 45){
            descuento = 15;
        }else if(variacion >= 46){
            descuento = 30;
        }
        return descuento;
    }

    //obtener grasa de un pago
    public double obtenerGrasa(PagoEntity pago){
        if(pago == null)
            return 0.0;
        else
            return pago.getGrasa();
    }

    //calcular variacion negativa de grasa
    public int variacionGrasa(double grasaAnterior, double grasaActual){
        int descuento = 0;
        double variacion = ((grasaAnterior - grasaActual)/grasaAnterior)*100;
        if(variacion >= 0 && variacion <= 15){
            descuento = 0;
        }else if(variacion >= 16 && variacion <= 25){
            descuento = 12;
        }else if(variacion >= 26 && variacion <= 40){
            descuento = 20;
        }else if(variacion >= 41){
            descuento = 30;
        }
        return descuento;
    }

    //obtener st de un pago
    public double obtenerSt(PagoEntity pago){
        if(pago == null)
            return 0.0;
        else
            return pago.getSolidos_totales();    
    }

    //calcular variacion negativa de st
    public int variacionSt(double stAnterior, double stActual){
        int descuento = 0;
        double variacion = ((stAnterior - stActual)/stAnterior)*100;
        if(variacion >= 0 && variacion <= 6){
            descuento = 0;
        }else if(variacion >= 7 && variacion <= 12){
            descuento = 18;
        }else if(variacion >= 13 && variacion <= 35){
            descuento = 27;
        }else if(variacion >= 36){
            descuento = 45;
        }
        return descuento;
    }


}
