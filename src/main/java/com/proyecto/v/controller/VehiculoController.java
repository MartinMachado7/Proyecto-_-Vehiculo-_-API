package com.proyecto.v.controller;

import com.proyecto.v.dto.AgregarDocumentoDTO;
import com.proyecto.v.dto.VehiculoDTO;
import com.proyecto.v.entity.Vehiculo;
import com.proyecto.v.entity.VehiculoDocumento;
import com.proyecto.v.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // CRUD: Crear Vehículo
    @PostMapping
    public ResponseEntity<?> crearVehiculo(@RequestBody VehiculoDTO dto) {
        try {
            Vehiculo nuevoVehiculo = vehiculoService.crearVehiculo(dto);
            return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // CRUD: Obtener Todos
    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculoService.obtenerTodos();
    }

    // CRUD: Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculoService.obtenerPorId(id));
    }

    // CRUD: Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody VehiculoDTO dto) {
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(id, dto));
    }

    // CRUD: Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
        return ResponseEntity.noContent().build();
    }

    // --- Servicios de Búsqueda Especificados ---

    // 1. Buscar por número de placa
    @GetMapping("/buscar/placa/{placa}")
    public ResponseEntity<?> buscarPorPlaca(@PathVariable String placa) {
        try {
            Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
            return ResponseEntity.ok(vehiculo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // 2. Buscar por tipo de vehículo
    @GetMapping("/buscar/tipo/{tipo}")
    public List<Vehiculo> buscarPorTipo(@PathVariable String tipo) {
        return vehiculoService.buscarPorTipoVehiculo(tipo);
    }

    // 3. Buscar vehículos que tengan en común un tipo de documento
    @GetMapping("/buscar/documento-id/{documentoId}")
    public List<Vehiculo> buscarPorDocumento(@PathVariable Long documentoId) {
        return vehiculoService.buscarPorTipoDocumento(documentoId);
    }

    // 4. Buscar según el estado del documento (Habilitado, Vencido, En Verificación)
    @GetMapping("/buscar/estado-documento/{estado}")
    public List<Vehiculo> buscarPorEstadoDocumento(@PathVariable String estado) {
        return vehiculoService.buscarPorEstadoDocumento(estado);
    }
 // Buscar vehículos por número / código de documento
    @GetMapping("/buscar/documento/{numeroDocumento}")
    public ResponseEntity<?> buscarPorNumeroDocumento(@PathVariable String numeroDocumento) {
        try {
            List<Vehiculo> vehiculos = vehiculoService.buscarPorCodigoDocumento(numeroDocumento);
            return ResponseEntity.ok(vehiculos);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    // --- Servicio para agregar documento a vehículo ---
    @PostMapping("/{vehiculoId}/documentos")
    public ResponseEntity<VehiculoDocumento> agregarDocumento(
            @PathVariable Long vehiculoId, 
            @RequestBody AgregarDocumentoDTO dto) {
        return new ResponseEntity<>(vehiculoService.agregarDocumentoAVehiculo(vehiculoId, dto), HttpStatus.CREATED);
    }
}