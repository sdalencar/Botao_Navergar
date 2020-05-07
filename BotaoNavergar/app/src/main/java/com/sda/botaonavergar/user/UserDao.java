package com.sda.botaonavergar.user;

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
public class UserDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public UserDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public UserDao openDB() {
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
    public long adiciona(String nome, String cargo) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.CARGO, cargo);
            return db.insert(Constantes.TABELA_USER, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.NOME, Constantes.CARGO};
        return db.query(Constantes.TABELA_USER, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id, String nome, String cargo) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.CARGO, cargo);
            return db.update(Constantes.TABELA_USER, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_USER, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
