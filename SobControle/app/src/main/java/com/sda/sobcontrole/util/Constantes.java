package com.sda.sobcontrole.util;

public class Constantes {

    public static final String ID = "id";
    public static final String IDENTIFICADOR = "identificador";
    public static final String ENTRADA = "entrada";
    public static final String PG_MANUAL = "pg_manual";
    public static final String PG_FUNCIONARIO = "pg_funcionario";
    public static final String DESPESAS = "despesas";
    public static final String DESP_EXTRAS = "desp_extras";
    public static final String CHEQUE = "cheque";
    public static final String CARTOES = "cartoes";
    public static final String OUTROS = "outros";
    public static final String VALES = "vales";
    public static final String FDO_CAIXA = "fdo_caixa";

    public static final String GRUPO = "grupo";
    public static final String SAIDA = "saida";
    public static final String NUMERO = "numero";

    public static final String DATA_BASE = "sobControleDB";
    public static final int DATA_VERSAO = 1;


    public static final String TABELA_LEITURA = "leitura";



    public static final String CREATE_TB="CREATE TABLE " + TABELA_LEITURA + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO + " integer, " + ENTRADA + " integer," + SAIDA + " integer);";


}
