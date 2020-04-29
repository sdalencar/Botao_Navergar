package com.sda.botaonavergar.valefeito;

import androidx.annotation.NonNull;

public class ValeFeito {

    private int id;
    private String identificador;
    private String nome;
    private String relaco;
    private double valor;

    public ValeFeito() {
    }

    public ValeFeito(int id, String identificador, String nome, String relaco, double valor) {
        this.id = id;
        this.identificador = identificador;
        this.nome = nome;
        this.relaco = relaco;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRelaco() {
        return relaco;
    }

    public void setRelaco(String relaco) {
        this.relaco = relaco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @NonNull
    @Override
    public String toString() {
        return getNome();
    }
}