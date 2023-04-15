package com.example.demo.services;

import com.example.demo.entities.AcopioLecheEntity;
import com.example.demo.repositories.AcopioLecheRepository;
import lombok.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class AcopioLecheService {
    @Autowired
    private AcopioLecheRepository acopioLecheRepository;

    private final Logger logg = LoggerFactory.getLogger(AcopioLecheService.class);

    public ArrayList<AcopioLecheEntity> obtenerAcopio(){return (ArrayList<AcopioLecheEntity>) acopioLecheRepository.findAll();}

    @Generated
    public String guardarAcopio(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    logg.info("Archivo guardado");
                }
                catch (IOException e){
                    logg.error("ERROR", e);
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        acopioLecheRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2], Integer.parseInt(bfRead.split(";")[3]));
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    logg.error("ERROR", e);
                }
            }
        }
    }

    public void guardarData(AcopioLecheEntity acopioLeche){
        acopioLecheRepository.save(acopioLeche);
    }

    public void guardarDataDB(String fecha, String turno, String proveedor, Integer kls_leche){
        AcopioLecheEntity newData = new AcopioLecheEntity();
        newData.setFecha(fecha);
        newData.setTurno(turno);
        newData.setProveedor(proveedor);
        newData.setKls_leche(kls_leche);
        guardarData(newData);
    }

    public ArrayList<AcopioLecheEntity> obtenerPorProveedor(String proveedor){
        return acopioLecheRepository.findByProveedor(proveedor);
    }

    //sumar kls de leche
    public double sumarKls(String proveedor){
        ArrayList<AcopioLecheEntity> acopios = obtenerPorProveedor(proveedor);
        double suma = 0.0;
        for(AcopioLecheEntity acopio:acopios){
            suma = suma + acopio.getKls_leche();
        }
        return suma;
    }

    public double klsPorCategoria(String categoria, double kilos){
        if(categoria.equals("A")){
            return kilos * 700;
        }else if(categoria.equals("B")){
            return kilos * 550;
        }else if(categoria.equals("C")){
            return kilos * 400;
        }else{
            return kilos * 250;
        }
    }

    //calcular cantidad de acopios con un turno por porveedor
    public int cantidadAcopiosPorTurno(String proveedor, String turno){
        int cantidad = acopioLecheRepository.countByProveedorAndTurno(proveedor, turno);
        return cantidad;
    }

    public double bonoFrecuencia(String proveedor, double kilos) {
        int mañana = cantidadAcopiosPorTurno(proveedor, "M");
        int tarde = cantidadAcopiosPorTurno(proveedor, "T");
        if (mañana >= 10 && tarde >= 10) {
            return kilos * 0.2;
        } else if (mañana >= 10 && tarde < 10) {
            return kilos * 0.12;
        } else if (mañana < 10 && tarde >= 10) {
            return kilos * 0.08;
        }
        return kilos * 0;
    }



}
