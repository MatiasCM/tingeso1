package com.example.demo.controllers;

import com.example.demo.entities.AcopioLecheEntity;
import com.example.demo.entities.GrasaSolidoEntity;
import com.example.demo.entities.PagoEntity;
import com.example.demo.entities.ProveedorEntity;
import com.example.demo.services.AcopioLecheService;
import com.example.demo.services.GrasaSolidoService;
import com.example.demo.services.PagoService;
import com.example.demo.services.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class PagoController {
    @Autowired
    private PagoService pagoService;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private AcopioLecheService acopioLecheService;

    @Autowired
    private GrasaSolidoService grasaSolidoService;

    @GetMapping("/listarPagos")
    public String listar(Model model) {
        ArrayList<PagoEntity> pagos = pagoService.obtenerPagos();
        model.addAttribute("pagos", pagos);
        return "pagos";
    }

    @GetMapping("/calcular")
    public String calcular(){
        //pagoService.eliminarPagos();
        ArrayList<ProveedorEntity> proveedores = proveedorService.obtenerProveedores();
        for(ProveedorEntity proveedor:proveedores){
            String codigo = proveedor.getCodigo();
            String categoria = proveedor.getCategoria();
            PagoEntity pago = new PagoEntity(null, "", codigo, proveedor.getNombre_proveedor(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
            ArrayList<AcopioLecheEntity> acopios = acopioLecheService.obtenerPorProveedor(codigo);
            double kls_leche = acopioLecheService.sumarKls(acopios);
            double klsPorCategoria = acopioLecheService.klsPorCategoria(categoria, kls_leche);
            GrasaSolidoEntity gs = grasaSolidoService.obtenerGSPorProveedor(codigo);
            double pagoGrasa;
            double grasa = 0.0;
            double st;
            double pagoST;
            if(gs == null){
                //GrasaSolidoEntity newGS = new GrasaSolidoEntity(null,codigo,0,0);
                pagoGrasa = grasaSolidoService.pagoPorGrasa(0.0, kls_leche);
                st = 0.0;
                pagoST = 0.0;

            }else{
                grasa = grasaSolidoService.obtenerGrasa(gs);
                pagoGrasa = grasaSolidoService.pagoPorGrasa(grasa, kls_leche);
                st = grasaSolidoService.obtenerST(gs);
                pagoST = grasaSolidoService.pagoPorST(st, kls_leche);
            }
            //double pagoGrasa = grasaSolidoService.pagoPorGrasa(grasa, kls_leche);
            double bono = acopioLecheService.bonoFrecuencia(codigo, kls_leche);
            double pagoAcopioLeche = klsPorCategoria + pagoGrasa + pagoST + bono;

            String quincena = acopioLecheService.obtenerQuincena(codigo);
            ArrayList<PagoEntity> pagosProveedor = pagoService.obtenerPagosByCodigoProveedor(codigo);
            PagoEntity ultimoPago = pagoService.obtenerUltimoPago(codigo);
            double totalKlsUltimoPago = pagoService.obtenerTotalKls(ultimoPago);
            int variacionLeche = pagoService.variacionLeche(totalKlsUltimoPago, kls_leche);
            double descuentoLeche = kls_leche * (variacionLeche / 100.0);
            double grasaUltimoPago = pagoService.obtenerGrasa(ultimoPago);
            int variacionGrasa = pagoService.variacionGrasa(grasaUltimoPago, grasa);
            double descuentoGrasa = grasa * (variacionGrasa / 100.0);
            double stUltimoPago = pagoService.obtenerSt(ultimoPago);
            int variacionSt = pagoService.variacionSt(stUltimoPago, st);
            double descuentoSt = st * (variacionSt / 100.0);
            double descuentos = descuentoLeche + descuentoGrasa + descuentoSt;
            double pagoTotal = pagoAcopioLeche - descuentos;

            double retencion = 0.0;
            if(pagoTotal >= 950000){
                retencion = pagoTotal * 0.13;
            }
            double pagoFinal = pagoTotal - retencion;

            pago.setQuincena(quincena);
            pago.setTotal_kls(kls_leche);
            pago.setDias(acopioLecheService.cantidadDias(codigo));
            pago.setPromedio_kls(acopioLecheService.promedioKls(codigo, acopios));
            pago.setVariacion_leche(variacionLeche);
            pago.setGrasa(grasa);
            pago.setVariacion_grasa(variacionGrasa);
            pago.setSolidos_totales(st);
            pago.setVariacion_st(variacionSt);
            pago.setPago_leche(klsPorCategoria);
            pago.setPago_grasa(pagoGrasa);
            pago.setPago_st(pagoST);
            pago.setBono(bono);
            pago.setDcto_leche(descuentoLeche);
            pago.setDcto_grasa(descuentoGrasa);
            pago.setDcto_st(descuentoSt);
            pago.setTotal(pagoTotal);
            pago.setMonto_retencion(retencion);
            pago.setMonto_final(pagoFinal);
            pagoService.guardarPago(pago);
        }
        return "redirect:/listarPagos";
    }


}
