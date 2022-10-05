package com.example.demo.repositories.dispositivo;

import com.example.demo.models.Dispositivo.TipoDispositivoModel;
import com.example.demo.models.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDispositivoRepository extends JpaRepository<TipoDispositivoModel,Integer> {
    boolean existsByTipo(String tipo);
    TipoDispositivoModel findByTipo(String tipo);
}
