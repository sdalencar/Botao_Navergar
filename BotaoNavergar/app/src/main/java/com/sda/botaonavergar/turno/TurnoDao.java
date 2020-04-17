package com.sda.botaonavergar.turno;

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
public class TurnoDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public TurnoDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public TurnoDao openDB() {
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
    public long adiciona(int numero, String nome) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.NOME, nome);
            return db.insert(Constantes.TABELA_TURNO, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.NUMERO, Constantes.NOME};
        return db.query(Constantes.TABELA_TURNO, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id, int numero, String nome) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.NOME, nome);
            return db.update(Constantes.TABELA_TURNO, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_TURNO, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
