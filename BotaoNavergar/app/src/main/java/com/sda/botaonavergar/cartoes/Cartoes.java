package com.sda.botaonavergar.cartoes;


/**
 * made by sda
 */
public class Cartoes {

    private int id;
    private String identificador;
    private String doc;
    private String tipo;
    private double valor;

    public Cartoes() {
    }

    public Cartoes(int id, String identificador, String doc, String tipo, double valor) {
        this.id = id;
        this.identificador = identificador;
        this.doc = doc;
        this.tipo = tipo;
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

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Cartoes{" +
                "id=" + id +
                ", identificador='" + identificador + '\'' +
                ", doc='" + doc + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                '}';
    }
}
