package com.sda.botaonavergar.maq;

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
public class MaquinaDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public MaquinaDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }
    //abrir DB
    public MaquinaDao openDB() {
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
    public long adiciona(String grupo, int numero) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.GRUPO, grupo);
            cv.put(Constantes.NUMERO, numero);
            return db.insert(Constantes.TABELA_MAQUINA, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.GRUPO, Constantes.NUMERO};
        return db.query(Constantes.TABELA_MAQUINA, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id, String grupo, int numero) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.GRUPO, grupo);
            cv.put(Constantes.NUMERO, numero);
            return db.update(Constantes.TABELA_MAQUINA, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_MAQUINA, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
