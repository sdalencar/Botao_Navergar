package com.sda.botaonavergar.addbon;

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
public class BonusDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public BonusDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public BonusDao openDB() {
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
    public long addTipoBonus(String nome) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, nome);
            return db.insert(Constantes.TABELA_TIPO_BONUS, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscaTipoBonus() {
        String[] columns = {Constantes.ID, Constantes.NOME};
        return db.query(Constantes.TABELA_TIPO_BONUS, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long updateTipoBonus(int id, String nome) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME, nome);
            return db.update(Constantes.TABELA_TIPO_BONUS, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long deletaTipoBonus(int id) {
        try {
            return db.delete(Constantes.TABELA_TIPO_BONUS, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
