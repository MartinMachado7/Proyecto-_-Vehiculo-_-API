package com.proyecto.v.controller;

import com.proyecto.v.entity.Documento;
import com.proyecto.v.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    // CRUD: Obtener Todos
    @GetMapping
    public List<Documento> obtenerTodos() {
        return documentoService.obtenerTodos();
    }

    // CRUD: Obtener Por ID
    @GetMapping("/{id}")
    public ResponseEntity<Documento> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(documentoService.obtenerPorId(id));
    }

    // CRUD: Crear Documento
    @PostMapping
    public ResponseEntity<Documento> crearDocumento(@RequestBody Documento documento) {
        return new ResponseEntity<>(documentoService.crearDocumento(documento), HttpStatus.CREATED);
    }

    // CRUD: Actualizar Documento
    @PutMapping("/{id}")
    public ResponseEntity<Documento> actualizarDocumento(@PathVariable Long id, @RequestBody Documento documento) {
        return ResponseEntity.ok(documentoService.actualizarDocumento(id, documento));
    }

    // CRUD: Eliminar Documento
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);
        return ResponseEntity.noContent().build();
    }
}