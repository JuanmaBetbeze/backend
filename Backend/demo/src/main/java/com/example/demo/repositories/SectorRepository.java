package com.example.demo.repositories;

import com.example.demo.models.Empleado.SectorModel;
import com.example.demo.security.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface SectorRepository extends JpaRepository<SectorModel,Integer>{
    boolean existsBySector(String sector);

}
