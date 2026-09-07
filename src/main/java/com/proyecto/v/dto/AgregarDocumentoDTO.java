package com.proyecto.v.dto;

import java.time.LocalDate;

public class AgregarDocumentoDTO {
    private Long documentoId;
    private LocalDate fechaExpedicion;
    private LocalDate fechaVencimiento;

    // Getters y Setters
    public Long getDocumentoId() { return documentoId; }
    public void setDocumentoId(Long documentoId) { this.documentoId = documentoId; }

    public LocalDate getFechaExpedicion() { return fechaExpedicion; }
    public void setFechaExpedicion(LocalDate fechaExpedicion) { this.fechaExpedicion = fechaExpedicion; }

    public LocalDate getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(LocalDate fechaVencimiento) { this.fechaVencimiento = fechaVencimiento; }
}