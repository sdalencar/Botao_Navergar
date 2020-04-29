package com.sda.botaonavergar.addbontipo;


/**
 * made by sda
 */
public class AddBonus {

    private int id;
    private String identificador;
    private String nome;
    private String tipo;
    private double valor;

    public AddBonus() {
    }

    public AddBonus(int id, String identificador, String nome, String tipo, double valor) {
        this.id = id;
        this.identificador = identificador;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        return "Bonus{" +
                "id=" + id +
                ", identificador='" + identificador + '\'' +
                ", nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", valor=" + valor +
                '}';
    }
}