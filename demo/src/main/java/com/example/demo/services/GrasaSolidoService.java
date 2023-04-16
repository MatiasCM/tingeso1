package com.example.demo.services;

import com.example.demo.entities.GrasaSolidoEntity;
import com.example.demo.repositories.GrasaSolidoRepository;
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
public class GrasaSolidoService {
    @Autowired
    private GrasaSolidoRepository grasaSolidoRepository;

    private final Logger logg = LoggerFactory.getLogger(GrasaSolidoService.class);

    public ArrayList<GrasaSolidoEntity> obtenerGS(){return (ArrayList<GrasaSolidoEntity>) grasaSolidoRepository.findAll();}

    @Generated
    public String guardarGS(MultipartFile file){
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
        grasaSolidoRepository.deleteAll();
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
                    guardarDataDB(bfRead.split(";")[0], Integer.parseInt(bfRead.split(";")[1]), Integer.parseInt(bfRead.split(";")[2]));
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

    public void guardarData(GrasaSolidoEntity grasaSolido){
        grasaSolidoRepository.save(grasaSolido);
    }

    public void guardarDataDB(String proveedor, Integer grasa, Integer solido){
        GrasaSolidoEntity newData = new GrasaSolidoEntity();
        newData.setProveedor(proveedor);
        newData.setGrasa(grasa);
        newData.setSolido(solido);
        guardarData(newData);
    }

    //eliminar todos los datos
    public void eliminarGS(){
        grasaSolidoRepository.deleteAll();
    }

    public GrasaSolidoEntity obtenerGSPorProveedor(String proveedor){
        return grasaSolidoRepository.findGSByProveedor(proveedor);
    }

    //obtener grasa de proveedor
    public double obtenerGrasa(String proveedor){
        return obtenerGSPorProveedor(proveedor).getGrasa();
    }

    public double pagoPorGrasa(double grasa, double kilos){
        if(grasa >= 0 && grasa <= 20){
            return kilos * 30;
        }else if(grasa >= 21 && grasa <= 45){
            return kilos * 80;
        }else{
            return kilos * 120;
        }
    }

    public double obtenerST(String proveedor){
        return obtenerGSPorProveedor(proveedor).getSolido();
    }

    public double pagoPorST(double st, double kilos){
        if(st >= 0 && st <= 7){
            return kilos * -130;
        }else if(st >= 8 && st <= 18){
            return kilos * -90;
        }else if(st >= 19 && st <= 35){
            return kilos * 95;
        }else{
            return kilos * 150;
        }
    }

}
