package com.example.demo.repositories;

import com.example.demo.models.Empleado.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SectorRepository extends JpaRepository<SectorModel,Integer>{
    boolean existsBySector(String sector);
    SectorModel findBySector(String sector);

}
