package com.sda.botaonavergar.desadd;

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
public class AddDespesaDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public AddDespesaDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public AddDespesaDao openDB() {
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
    public long adicionar(String iden, String data, String tipo, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.IDENTIFICADOR, iden);
            cv.put(Constantes.DATA_ATUAL, data);
            cv.put(Constantes.TIPO, tipo);
            cv.put(Constantes.VALOR, valor);
            return db.insert(Constantes.TABELA_DESPESA_ADD, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.IDENTIFICADOR, Constantes.DATA_ATUAL,Constantes.TIPO,Constantes.VALOR };
        return db.query(Constantes.TABELA_DESPESA_ADD, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualiza(int id,String iden, String data, String tipo, double valor) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.IDENTIFICADOR, iden);
            cv.put(Constantes.DATA_ATUAL, data);
            cv.put(Constantes.TIPO, tipo);
            cv.put(Constantes.VALOR, valor);
            return db.update(Constantes.TABELA_DESPESA_ADD, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apaga(int id) {
        try {
            return db.delete(Constantes.TABELA_DESPESA_ADD, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
