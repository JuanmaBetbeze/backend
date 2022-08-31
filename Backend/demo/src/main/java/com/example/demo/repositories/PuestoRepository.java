package com.example.demo.repositories;

import com.example.demo.models.Empleado.PuestoModel;
import com.example.demo.models.Empleado.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PuestoRepository extends JpaRepository<PuestoModel,Integer> {
    boolean existsByPuesto(String puesto);
    PuestoModel findByPuesto(String puesto);

}
