package com.proyecto.v.service;

import com.proyecto.v.dto.AgregarDocumentoDTO;
import com.proyecto.v.dto.VehiculoDTO;
import com.proyecto.v.entity.Documento;
import com.proyecto.v.entity.Vehiculo;
import com.proyecto.v.entity.VehiculoDocumento;
import com.proyecto.v.repository.DocumentoRepository;
import com.proyecto.v.repository.VehiculoDocumentoRepository;
import com.proyecto.v.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Autowired
    private VehiculoDocumentoRepository vehiculoDocumentoRepository;

    // Crear vehículo garantizando al menos un documento con estado "En Verificación"
    @Transactional
    public Vehiculo crearVehiculo(VehiculoDTO dto) {
        if (dto.getDocumentoIds() == null || dto.getDocumentoIds().isEmpty()) {
            throw new IllegalArgumentException("No se puede crear un vehículo sin al menos un documento asociado.");
        }

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setTipoServicio(dto.getTipoServicio());
        vehiculo.setTipoCombustible(dto.getTipoCombustible());
        vehiculo.setCapacidadPasajeros(dto.getCapacidadPasajeros());
        vehiculo.setColorHex(dto.getColorHex());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setLinea(dto.getLinea());

        Vehiculo vehiculoGuardado = vehiculoRepository.save(vehiculo);

        List<VehiculoDocumento> relaciones = new ArrayList<>();
        for (Long docId : dto.getDocumentoIds()) {
            Documento doc = documentoRepository.findById(docId)
                    .orElseThrow(() -> new IllegalArgumentException("Documento no encontrado con ID: " + docId));

            VehiculoDocumento vd = new VehiculoDocumento();
            vd.setVehiculo(vehiculoGuardado);
            vd.setDocumento(doc);
            vd.setFechaExpedicion(LocalDate.now());
            vd.setFechaVencimiento(LocalDate.now().plusYears(1));
            vd.setEstado("En Verificación"); // Estado inicial obligatorio

            relaciones.add(vd);
        }

        vehiculoDocumentoRepository.saveAll(relaciones);
        vehiculoGuardado.setDocumentos(relaciones);
        return vehiculoGuardado;
    }

    // CRUD: Leer todos
    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    // CRUD: Leer por ID
    public Vehiculo obtenerPorId(Long id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));
    }

    // CRUD: Actualizar
    public Vehiculo actualizarVehiculo(Long id, VehiculoDTO dto) {
        Vehiculo vehiculo = obtenerPorId(id);
        vehiculo.setTipoVehiculo(dto.getTipoVehiculo());
        vehiculo.setPlaca(dto.getPlaca());
        vehiculo.setTipoServicio(dto.getTipoServicio());
        vehiculo.setTipoCombustible(dto.getTipoCombustible());
        vehiculo.setCapacidadPasajeros(dto.getCapacidadPasajeros());
        vehiculo.setColorHex(dto.getColorHex());
        vehiculo.setModelo(dto.getModelo());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setLinea(dto.getLinea());
        return vehiculoRepository.save(vehiculo);
    }

    // CRUD: Eliminar
    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

    // Búsquedas específicas
    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con placa: " + placa));
    }

    public List<Vehiculo> buscarPorTipoVehiculo(String tipo) {
        return vehiculoRepository.findByTipoVehiculo(tipo);
    }

    public List<Vehiculo> buscarPorTipoDocumento(Long documentoId) {
        return vehiculoRepository.findByDocumentoId(documentoId);
    }

    public List<Vehiculo> buscarPorEstadoDocumento(String estado) {
        return vehiculoRepository.findByEstadoDocumento(estado);
    }

    // Agregar documento a un vehículo existente
    @Transactional
    public VehiculoDocumento agregarDocumentoAVehiculo(Long vehiculoId, AgregarDocumentoDTO dto) {
        Vehiculo vehiculo = obtenerPorId(vehiculoId);
        Documento documento = documentoRepository.findById(dto.getDocumentoId())
                .orElseThrow(() -> new RuntimeException("Documento no encontrado con ID: " + dto.getDocumentoId()));

        VehiculoDocumento vd = new VehiculoDocumento();
        vd.setVehiculo(vehiculo);
        vd.setDocumento(documento);
        vd.setFechaExpedicion(dto.getFechaExpedicion());
        vd.setFechaVencimiento(dto.getFechaVencimiento());
        vd.setEstado("En Verificación");

        return vehiculoDocumentoRepository.save(vd);
    }
    public List<Vehiculo> buscarPorCodigoDocumento(String codigoDocumento) {
        List<Vehiculo> vehiculos = vehiculoRepository.findByCodigoDocumento(codigoDocumento);
        if (vehiculos.isEmpty()) {
            throw new RuntimeException("No se encontraron vehículos asociados al documento: " + codigoDocumento);
        }
        return vehiculos;
    }
}