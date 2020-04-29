package com.sda.botaonavergar.valefeito;

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
public class ValeFeitoDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public ValeFeitoDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }
    //abrir DB
    public ValeFeitoDao openDB() {
        try {
            db = helper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
    //fechar
    public void close() {
        try {
            helper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //inserir no bd
    public long adiciona(String identificador, String nome, String relacao, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.IDENTIFICADOR, identificador);
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.RELACIONAMENTO, relacao);
            cv.put(Constantes.VALOR, valor);
            return db.insert(Constantes.TABELA_VALES_FEITO, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.IDENTIFICADOR, Constantes.NOME, Constantes.RELACIONAMENTO,Constantes.VALOR};
        return db.query(Constantes.TABELA_VALES_FEITO, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id, String identificador, String nome, String relacao, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.IDENTIFICADOR, identificador);
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.RELACIONAMENTO, relacao);
            cv.put(Constantes.VALOR, valor);
            return db.update(Constantes.TABELA_VALES_FEITO, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_VALES_FEITO, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
