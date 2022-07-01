package com.example.minimarketapp.models;

public class NavCategoriaDetalleModel {

    String nombre;
    String tipo;
    String img_url;
    int precio;

    public NavCategoriaDetalleModel() {
    }

    public NavCategoriaDetalleModel(String nombre, String tipo, String img_url, int precio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.img_url = img_url;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
