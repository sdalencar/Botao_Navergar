package com.sda.botaonavergar.desadd;


/**
 * made by sda
 */
public class AddDespesa {

    private int id;
    private String identificador;
    private String data;
    private String nome;
    private double valor;

    public AddDespesa() {
    }

    public AddDespesa(int id, String identificador, String data, String nome, double valor) {
        this.id = id;
        this.identificador = identificador;
        this.data = data;
        this.nome = nome;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "AddDespesa{" +
                "id=" + id +
                ", identificador='" + identificador + '\'' +
                ", data='" + data + '\'' +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                '}';
    }
}
