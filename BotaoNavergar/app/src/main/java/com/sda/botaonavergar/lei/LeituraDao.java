package com.sda.botaonavergar.lei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sda.botaonavergar.helper.CriarBancoDados;
import com.sda.botaonavergar.util.Constantes;

public class LeituraDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public LeituraDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public LeituraDao openDB() {
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
    public long adiciona(int numero, int entrada, int saida) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.ENTRADA, entrada);
            cv.put(Constantes.SAIDA, saida);
            return db.insert(Constantes.TABELA_LEITURA, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor burcar() {
        String[] columns = {Constantes.ID, Constantes.NUMERO, Constantes.ENTRADA, Constantes.SAIDA};
        return db.query(Constantes.TABELA_LEITURA, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualizar(int id,int numero, int entrada, int saida) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NUMERO, numero);
            cv.put(Constantes.ENTRADA, entrada);
            cv.put(Constantes.SAIDA, saida);
            return db.update(Constantes.TABELA_LEITURA, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_LEITURA, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
