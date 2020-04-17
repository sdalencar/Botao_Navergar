package com.sda.botaonavergar.cargo;

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
public class CargoDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public CargoDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public CargoDao openDB() {
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
    public long addCargo(String nome) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, nome);
            return db.insert(Constantes.TABELA_CARGO, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor getAllCargo() {
        String[] columns = {Constantes.ID, Constantes.NOME};
        return db.query(Constantes.TABELA_CARGO, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long updateCargo(int id, String nome) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME, nome);
            return db.update(Constantes.TABELA_CARGO, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long deletaCargo(int id) {
        try {
            return db.delete(Constantes.TABELA_CARGO, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
