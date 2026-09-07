package com.proyecto.v.repository;

import com.proyecto.v.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    
    // Buscar por placa
    Optional<Vehiculo> findByPlaca(String placa);

    // Buscar por tipo de vehículo (Automóvil / Motocicleta)
    List<Vehiculo> findByTipoVehiculo(String tipoVehiculo);

    // Buscar vehículos que tengan en común un tipo de documento (por ID de documento)
    @Query("SELECT DISTINCT v FROM Vehiculo v JOIN v.documentos vd WHERE vd.documento.id = :documentoId")
    List<Vehiculo> findByDocumentoId(@Param("documentoId") Long documentoId);

    // Buscar vehículos según el estado del documento asociado (Habilitado, Vencido, En Verificación)
    @Query("SELECT DISTINCT v FROM Vehiculo v JOIN v.documentos vd WHERE vd.estado = :estado")
    List<Vehiculo> findByEstadoDocumento(@Param("estado") String estado);
    
 // Busca vehículos que tengan asociado un documento con el código/número especificado
    @Query("SELECT DISTINCT v FROM Vehiculo v JOIN v.documentos vd JOIN vd.documento d WHERE d.codigoDocumento = :codigoDocumento")
    List<Vehiculo> findByCodigoDocumento(@Param("codigoDocumento") String codigoDocumento);

}