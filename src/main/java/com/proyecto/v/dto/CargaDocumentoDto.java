package com.proyecto.v.dto;

public class CargaDocumentoDto {
    private Long vehiculoId;
    private Long documentoId;
    private String archivoPdfBase64; // Documento en Base64

    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }
    public Long getDocumentoId() { return documentoId; }
    public void setDocumentoId(Long documentoId) { this.documentoId = documentoId; }
    public String getArchivoPdfBase64() { return archivoPdfBase64; }
    public void setArchivoPdfBase64(String archivoPdfBase64) { this.archivoPdfBase64 = archivoPdfBase64; }
}