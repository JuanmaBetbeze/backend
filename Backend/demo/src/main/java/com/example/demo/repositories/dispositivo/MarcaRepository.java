package com.example.demo.repositories.dispositivo;

import com.example.demo.models.Dispositivo.MarcaModel;
import com.example.demo.models.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaModel,Integer> {
    boolean existsByMarca(String marca);
    MarcaModel findByMarca(String marca);
}
