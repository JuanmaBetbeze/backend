package com.example.demo.repositories.dispositivo;

import com.example.demo.models.Dispositivo.Dispositivo;
import com.example.demo.models.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo,Integer> {
    boolean existsByModelo(String modelo);
}
