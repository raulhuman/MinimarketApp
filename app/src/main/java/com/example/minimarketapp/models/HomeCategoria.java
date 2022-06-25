package com.example.minimarketapp.models;

public class HomeCategoria {

    String nombre;
    String img_url;
    String tipo;

    public HomeCategoria() {
    }

    public HomeCategoria(String nombre, String img_url, String tipo) {
        this.nombre = nombre;
        this.img_url = img_url;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
