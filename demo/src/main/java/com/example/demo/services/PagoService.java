package com.example.demo.services;

import com.example.demo.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {

    @Autowired
    PagoRepository pagoRepository;

    public void eliminarPagos(){pagoRepository.deleteAll();}

}
