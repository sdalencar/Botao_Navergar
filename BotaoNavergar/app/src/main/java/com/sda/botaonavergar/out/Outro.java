package com.sda.botaonavergar.out;

public class Outro {

    private int id;
    private String outro;
    private double valor;

    public Outro() {
    }

    public Outro(int id, String outro, double valor) {
        this.id = id;
        this.outro = outro;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutro() {
        return outro;
    }

    public void setOutro(String outro) {
        this.outro = outro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Outro{" +
                "id=" + id +
                ", outro='" + outro + '\'' +
                ", valor=" + valor +
                '}';
    }
}