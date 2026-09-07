package com.proyecto.v.dto;

import java.util.List;

public class VehiculoDTO {
    private String tipoVehiculo;
    private String placa;
    private String tipoServicio;
    private String tipoCombustible;
    private Integer capacidadPasajeros;
    private String colorHex;
    private Integer modelo;
    private String marca;
    private String linea;
    
    // Lista de IDs de documentos requeridos al crear el vehículo
    private List<Long> documentoIds;

    // Getters y Setters
    public String getTipoVehiculo() { return tipoVehiculo; }
    public void setTipoVehiculo(String tipoVehiculo) { this.tipoVehiculo = tipoVehiculo; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getTipoServicio() { return tipoServicio; }
    public void setTipoServicio(String tipoServicio) { this.tipoServicio = tipoServicio; }

    public String getTipoCombustible() { return tipoCombustible; }
    public void setTipoCombustible(String tipoCombustible) { this.tipoCombustible = tipoCombustible; }

    public Integer getCapacidadPasajeros() { return capacidadPasajeros; }
    public void setCapacidadPasajeros(Integer capacidadPasajeros) { this.capacidadPasajeros = capacidadPasajeros; }

    public String getColorHex() { return colorHex; }
    public void setColorHex(String colorHex) { this.colorHex = colorHex; }

    public Integer getModelo() { return modelo; }
    public void setModelo(Integer modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getLinea() { return linea; }
    public void setLinea(String linea) { this.linea = linea; }

    public List<Long> getDocumentoIds() { return documentoIds; }
    public void setDocumentoIds(List<Long> documentoIds) { this.documentoIds = documentoIds; }
}