package com.sda.botaonavergar.manual;

public class Manual {

    private int id;
    private int numeroMqn;
    private String user;
    private String data;
    private double sangria;
    private double pagamentoManual;

    public Manual() {
    }

    public Manual(int id, int numeroMqn, String user, String data, double sangria, double pagamentoManual) {
        this.id = id;
        this.numeroMqn = numeroMqn;
        this.user = user;
        this.data = data;
        this.sangria = sangria;
        this.pagamentoManual = pagamentoManual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroMqn() {
        return numeroMqn;
    }

    public void setNumeroMqn(int numeroMqn) {
        this.numeroMqn = numeroMqn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getSangria() {
        return sangria;
    }

    public void setSangria(double sangria) {
        this.sangria = sangria;
    }

    public double getPagamentoManual() {
        return pagamentoManual;
    }

    public void setPagamentoManual(double pagamentoManual) {
        this.pagamentoManual = pagamentoManual;
    }

    @Override
    public String toString() {
        return "Manual{" +
                "id=" + id +
                ", numeroMqn=" + numeroMqn +
                ", user='" + user + '\'' +
                ", data='" + data + '\'' +
                ", sangria=" + sangria +
                ", pagamentoManual=" + pagamentoManual +
                '}';
    }
}
