package com.example.minimarketapp.models;

public class PopularModel {
    String nombre;
    String descripcion;
    String rating;
    String descuento;
    String tipo;
    String img_url;

    public PopularModel() {
    }

    public PopularModel(String nombre, String descripcion, String rating, String descuento, String tipo, String img_url) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.rating = rating;
        this.descuento = descuento;
        this.tipo = tipo;
        this.img_url = img_url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
