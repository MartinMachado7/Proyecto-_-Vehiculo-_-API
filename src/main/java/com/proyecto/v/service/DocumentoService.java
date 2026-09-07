package com.proyecto.v.service;

import com.proyecto.v.entity.Documento;
import com.proyecto.v.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    public List<Documento> obtenerTodos() {
        return documentoRepository.findAll();
    }

    public Documento obtenerPorId(Long id) {
        return documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado con ID: " + id));
    }

    public Documento crearDocumento(Documento documento) {
        return documentoRepository.save(documento);
    }

    public Documento actualizarDocumento(Long id, Documento docDetalles) {
        Documento doc = obtenerPorId(id);
        doc.setCodigoDocumento(docDetalles.getCodigoDocumento());
        doc.setNombreDocumento(docDetalles.getNombreDocumento());
        doc.setAplicaA(docDetalles.getAplicaA());
        doc.setObligatorio(docDetalles.getObligatorio());
        doc.setDescripcion(docDetalles.getDescripcion());
        return documentoRepository.save(doc);
    }

    public void eliminarDocumento(Long id) {
        documentoRepository.deleteById(id);
    }
}