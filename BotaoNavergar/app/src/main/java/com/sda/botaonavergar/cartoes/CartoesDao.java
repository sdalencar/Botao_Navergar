package com.sda.botaonavergar.cartoes;

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
public class CartoesDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public CartoesDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }
    //abrir DB
    public CartoesDao openDB() {
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
    public long adiciona(String identificador, String numero, String doc, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.IDENTIFICADOR, identificador);
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.DOC, doc);
            cv.put(Constantes.VALOR, valor);
            return db.insert(Constantes.TABELA_CARTOES, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.IDENTIFICADOR, Constantes.NUMERO, Constantes.DOC,  Constantes.VALOR};
        return db.query(Constantes.TABELA_CARTOES, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id, String identificador, String numero, String doc, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.IDENTIFICADOR, identificador);
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.DOC, doc);
            cv.put(Constantes.VALOR, valor);
            return db.update(Constantes.TABELA_CARTOES, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_CARTOES, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
