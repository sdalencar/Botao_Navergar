package com.sda.botaonavergar.out;

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
public class OutroDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public OutroDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public OutroDao openDB() {
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
    public long adiciona(String nome, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.VALOR, valor);
            return db.insert(Constantes.TABELA_OUTROS, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor busca() {
        String[] columns = {Constantes.ID, Constantes.NOME,Constantes.VALOR};
        return db.query(Constantes.TABELA_OUTROS, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id, String nome, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.VALOR, valor);
            return db.update(Constantes.TABELA_OUTROS, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apaga(int id) {
        try {
            return db.delete(Constantes.TABELA_OUTROS, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
