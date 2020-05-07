package com.sda.botaonavergar.chq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sda.botaonavergar.helper.CriarBancoDados;
import com.sda.botaonavergar.util.Constantes;

/**
 * made by sda
 */
public class ChequeDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public ChequeDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public ChequeDao openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }

    //CLOSE
    public void close() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //inserir no bd
    public long adicionar(String nome_chq, String nome_cli, String banco, String numero, double valor, String data_atual, String depositar) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME_CHQ, nome_chq);
            cv.put(Constantes.NOME_CLI, nome_cli);
            cv.put(Constantes.BANCO, banco);
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.VALOR, valor);
            cv.put(Constantes.DATA_ATUAL, data_atual);
            cv.put(Constantes.DATA_DEPOSITO, depositar);
            return db.insert(Constantes.TABELA_CHEQUE, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.NOME_CHQ, Constantes.NOME_CLI, Constantes.BANCO, Constantes.NUMERO, Constantes.VALOR, Constantes.DATA_ATUAL,Constantes.DATA_DEPOSITO};
        return db.query(Constantes.TABELA_CHEQUE, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id,String nome_chq, String nome_cli, String banco, String numero, double valor, String data_atual, String depositar) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME_CHQ, nome_chq);
            cv.put(Constantes.NOME_CLI, nome_cli);
            cv.put(Constantes.BANCO, banco);
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.VALOR, valor);
            cv.put(Constantes.DATA_ATUAL, data_atual);
            cv.put(Constantes.DATA_DEPOSITO, depositar);
            return db.update(Constantes.TABELA_CHEQUE, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apaga(int id) {
        try {
            return db.delete(Constantes.TABELA_CHEQUE, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
