package com.example.demo.repositories;

import com.example.demo.entities.GrasaSolidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrasaSolidoRepository extends JpaRepository<GrasaSolidoEntity, String> {
}
