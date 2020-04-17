package com.sda.sobcontrole.model;


import java.io.Serializable;

public class Caixa implements Serializable {

    private int id ;
    private String identificador;
    private float entrada;
    private float pgManual;
    private float pgFuncionario;
    private float despesa;
    private float despExtra;
    private float cheque;
    private float cartoes;
    private float outros;
    private float vales;
    private float fdoCaixa;

    public Caixa() {
    }

    public Caixa(int id, String identificador, float entrada, float pgManual, float pgFuncionario, float despesa, float despExtra, float cheque, float cartoes, float outros, float vales, float fdoCaixa) {
        this.id = id;
        this.identificador = identificador;
        this.entrada = entrada;
        this.pgManual = pgManual;
        this.pgFuncionario = pgFuncionario;
        this.despesa = despesa;
        this.despExtra = despExtra;
        this.cheque = cheque;
        this.cartoes = cartoes;
        this.outros = outros;
        this.vales = vales;
        this.fdoCaixa = fdoCaixa;
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

    public float getEntrada() {
        return entrada;
    }

    public void setEntrada(float entrada) {
        this.entrada = entrada;
    }

    public float getPgManual() {
        return pgManual;
    }

    public void setPgManual(float pgManual) {
        this.pgManual = pgManual;
    }

    public float getPgFuncionario() {
        return pgFuncionario;
    }

    public void setPgFuncionario(float pgFuncionario) {
        this.pgFuncionario = pgFuncionario;
    }

    public float getDespesa() {
        return despesa;
    }

    public void setDespesa(float despesa) {
        this.despesa = despesa;
    }

    public float getDespExtra() {
        return despExtra;
    }

    public void setDespExtra(float despExtra) {
        this.despExtra = despExtra;
    }

    public float getCheque() {
        return cheque;
    }

    public void setCheque(float cheque) {
        this.cheque = cheque;
    }

    public float getCartoes() {
        return cartoes;
    }

    public void setCartoes(float cartoes) {
        this.cartoes = cartoes;
    }

    public float getOutros() {
        return outros;
    }

    public void setOutros(float outros) {
        this.outros = outros;
    }

    public float getVales() {
        return vales;
    }

    public void setVales(float vales) {
        this.vales = vales;
    }

    public float getFdoCaixa() {
        return fdoCaixa;
    }

    public void setFdoCaixa(float fdoCaixa) {
        this.fdoCaixa = fdoCaixa;
    }

    @Override
    public String toString() {
        return "Caixa{" +
                "identificador='" + identificador + '\'' +
                '}';
    }
}
