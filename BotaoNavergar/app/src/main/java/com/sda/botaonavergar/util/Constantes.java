package com.sda.botaonavergar.util;

public class Constantes {

    public static final String DATA_BASE = "sobControleDB";
    public static final int DATA_VERSAO = 1;

    public static final String TABELA_RELACIONAMENTO = "tb_relacionamento";
    public static final String TABELA_CARGO = "tb_cargo";
    public static final String TABELA_CARTAO = "tb_cartao";
    public static final String TABELA_TIPO_BONUS = "tb_tipo_bonus";
    public static final String TABELA_TURNO = "tb_turno";
    public static final String TABELA_PESSOA = "tb_pessoa";
    public static final String TABELA_EMPRESA = "tb_empresa";
    public static final String TABELA_VALES_PAGO = "tb_vales_pago";
    public static final String TABELA_VALES_FEITO = "tb_vales_feito";
    public static final String TABELA_BONUS = "tb_bonus";

    public static final String TABELA_PG_MANUAL = "tb_pg_manual";
    public static final String TABELA_PG_FUNCIONARIO = "tb_pg_funcionario";
    public static final String TABELA_DESPESA = "tb_despesa";
    public static final String TABELA_DESPESA_ADD = "tb_despesa_add";
    public static final String TABELA_DESP_EXTRAS = "tb_desp_extras";
    public static final String TABELA_CHEQUE = "tb_cheque";
    public static final String TABELA_CARTOES = "tb_cartoes";
    public static final String TABELA_OUTROS = "tb_outros";
    public static final String TABELA_FDO_CAIXA = "tb_fdo_caixa";


    public static final String TABELA_LEITURA = "tb_leitura";
    public static final String GRUPO = "grupo";
    public static final String NUMERO = "numero";
    public static final String ENTRADA = "entrada";
    public static final String SAIDA = "saida";
    public static final String ID = "id";
    public static final String IDENTIFICADOR = "identificador";
    public static final String ALIAS = "alias";
    public static final String CELULAR = "celular";
    public static final String RELACIONAMENTO = "relacionamento";
    public static final String TIPO = "tipo";
    public static final String DOC = "doc";

    public static final String DATA_ATUAL = "data_atual";
    public static final String DATA_DEPOSITO = "data_deposito";

    public static final String VALOR = "valor";

    public static final String NOME = "nome";
    public static final String ENDERECO = "endereco";
    public static final String TELEFONE = "telefone";


    public static final String DELETE_TABLE = " DROP TABLE IF EXISTS ";

    public static final String CREATE_TB_EMPRESA = " CREATE TABLE " + TABELA_EMPRESA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text, " + ENDERECO + " text, " + TELEFONE + " text );";

    public static final String CREATE_TB_TURNO = " CREATE TABLE " + TABELA_TURNO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NUMERO + " integer, " + NOME + " text );";

    public static final String CREATE_TB_CARGO = " CREATE TABLE " + TABELA_CARGO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_CARTAO = " CREATE TABLE " + TABELA_CARTAO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_TIPO_BONUS = " CREATE TABLE " + TABELA_TIPO_BONUS + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_PESSOA = " CREATE TABLE " + TABELA_PESSOA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text, " + ALIAS + " text, " + CELULAR + " text, " + RELACIONAMENTO + " text );";

    public static final String CREATE_TB_LEITURA = "CREATE TABLE " + TABELA_LEITURA + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + GRUPO + " integer, " + NUMERO + " integer, " + ENTRADA + " integer," + SAIDA + " integer);";

    public static final String CREATE_TB_VALE_PAGO = " CREATE TABLE " + TABELA_VALES_PAGO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDENTIFICADOR + " text, " + NOME + " text, " + RELACIONAMENTO + " text, " + VALOR + " number);";

    public static final String CREATE_TB_VALE_FEITO = " CREATE TABLE " + TABELA_VALES_FEITO + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDENTIFICADOR + " text, " + NOME + " text, " + RELACIONAMENTO + " text, " + VALOR + " number);";

    public static final String CREATE_TB_BONUS = " CREATE TABLE " + TABELA_BONUS + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDENTIFICADOR + " text, " + NOME + " text, " + RELACIONAMENTO + " text, " + VALOR + " number);";

    public static final String CREATE_TB_CARTOES = " CREATE TABLE " + TABELA_CARTOES + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDENTIFICADOR + " text, " + TIPO + " text, " + DOC + " text, " + VALOR + " number);";

    public static final String CREATE_TB_DESPESA = " CREATE TABLE " + TABELA_DESPESA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_RELACIONAMENTO = " CREATE TABLE " + TABELA_DESPESA + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " text );";

    public static final String CREATE_TB_DESPESA_ADD = " CREATE TABLE " + TABELA_DESPESA_ADD + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + IDENTIFICADOR + " text, " + DATA_ATUAL + " text, " + TIPO + " text, " + VALOR + " number);";

}
