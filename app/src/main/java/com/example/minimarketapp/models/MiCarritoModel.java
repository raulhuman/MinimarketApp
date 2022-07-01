package com.example.minimarketapp.models;

import java.io.Serializable;

public class MiCarritoModel implements Serializable {

    String productoNombre;
    String productoPrecio;
    String fecha;
    String hora;
    String totalCantidad;
    int totalPrecio;
    String documentId;


    public MiCarritoModel() {
    }

    public MiCarritoModel(String productoNombre, String productoPrecio, String fecha, String hora, String totalCantidad, int totalPrecio, String documentId) {
        this.productoNombre = productoNombre;
        this.productoPrecio = productoPrecio;
        this.fecha = fecha;
        this.hora = hora;
        this.totalCantidad = totalCantidad;
        this.totalPrecio = totalPrecio;
        this.documentId = documentId;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public String getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(String productoPrecio) {
        this.productoPrecio = productoPrecio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTotalCantidad() {
        return totalCantidad;
    }

    public void setTotalCantidad(String totalCantidad) {
        this.totalCantidad = totalCantidad;
    }

    public int getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(int totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
