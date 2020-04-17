package com.sda.botaonavergar.util;

public class Constantes {

    public static final String ID = "id";
    public static final String IDENTIFICADOR = "identificador";

    public static final String TABELA_PG_MANUAL = "tb_pg_manual";
    public static final String TABELA_PG_FUNCIONARIO = "tb_pg_funcionario";
    public static final String TABELA_DESPESAS = "tb_despesas";
    public static final String TABELA_DESP_EXTRAS = "tb_desp_extras";
    public static final String TABELA_CHEQUE = "tb_cheque";
    public static final String TABELA_CARTOES = "tb_cartoes";
    public static final String TABELA_OUTROS = "tb_outros";
    public static final String TABELA_VALES = "tb_vales";
    public static final String TABELA_FDO_CAIXA = "tb_fdo_caixa";

    public static final String DATA_BASE = "sobControleDB";
    public static final int DATA_VERSAO = 1;

    public static final String TABELA_LEITURA = "tb_leitura";
    public static final String GRUPO = "grupo";
    public static final String NUMERO = "numero";
    public static final String ENTRADA = "entrada";
    public static final String SAIDA = "saida";

    public static final String ALIAS = "alias";
    public static final String CELULAR = "celular";
    public static final String RELACIONAMENTO = "relacionamento";



    public static final String TABELA_EMPRESA = "tb_empresa";
    public static final String NOME = "nome";
    public static final String ENDERECO = "endereco";
    public static final String TELEFONE = "telefone";
    public static final String TABELA_TURNO = "tb_turno";
    public static final String TABELA_PESSOA = "tb_pessoa";


    public static final String DELETE_TABLE = " DROP TABLE IF EXISTS ";

    public static final String TABELA_RELACIONAMENTO = "tb_relacionamento";

    public static final String TABELA_CARGO = "tb_cargo";

    public static final String CREATE_TB_EMPRESA = " CREATE TABLE " + TABELA_EMPRESA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text, " + ENDERECO + " text, " + TELEFONE + " text );";

    public static final String CREATE_TB_TURNO = " CREATE TABLE " + TABELA_TURNO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO + " integer, " + NOME + " text );";

    public static final String CREATE_TB_CARGO = " CREATE TABLE " + TABELA_CARGO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_RELACIONAMENTO = " CREATE TABLE " + TABELA_RELACIONAMENTO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_PESSOA = " CREATE TABLE " + TABELA_PESSOA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text, " + ALIAS + " text, " + CELULAR + " text, " + RELACIONAMENTO + " text );";


    public static final String CREATE_TB_LEITURA = "CREATE TABLE " + TABELA_LEITURA + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GRUPO + " integer, " + NUMERO + " integer, " + ENTRADA + " integer," + SAIDA + " integer);";


}
