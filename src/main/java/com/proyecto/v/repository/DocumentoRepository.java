package com.proyecto.v.repository;

import com.proyecto.v.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    Optional<Documento> findByCodigoDocumento(String codigoDocumento);
}