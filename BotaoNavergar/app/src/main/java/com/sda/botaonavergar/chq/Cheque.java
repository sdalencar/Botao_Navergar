package com.sda.botaonavergar.chq;

public class Cheque {
    private int id;
    private String nome_chq;
    private String nome_cli;
    private String banco;
    private String numero;
    private double valor;
    private String data_atual;
    private String depositar;

    public Cheque() {
    }

    public Cheque(int id, String nome_chq, String nome_cli, String banco, String numero, double valor, String data_atual, String depositar) {
        this.id = id;
        this.nome_chq = nome_chq;
        this.nome_cli = nome_cli;
        this.banco = banco;
        this.numero = numero;
        this.valor = valor;
        this.data_atual = data_atual;
        this.depositar = depositar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_chq() {
        return nome_chq;
    }

    public void setNome_chq(String nome_chq) {
        this.nome_chq = nome_chq;
    }

    public String getNome_cli() {
        return nome_cli;
    }

    public void setNome_cli(String nome_cli) {
        this.nome_cli = nome_cli;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData_atual() {
        return data_atual;
    }

    public void setData_atual(String data_atual) {
        this.data_atual = data_atual;
    }

    public String getDepositar() {
        return depositar;
    }

    public void setDepositar(String depositar) {
        this.depositar = depositar;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", nome_chq='" + nome_chq + '\'' +
                ", nome_cli='" + nome_cli + '\'' +
                ", banco='" + banco + '\'' +
                ", numero='" + numero + '\'' +
                ", valor=" + valor +
                ", data_atual='" + data_atual + '\'' +
                ", depositar='" + depositar + '\'' +
                '}';
    }
}
