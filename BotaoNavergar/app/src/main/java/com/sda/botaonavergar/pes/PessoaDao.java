package com.sda.botaonavergar.pes;

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
public class PessoaDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public PessoaDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public PessoaDao openDB() {
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
    public long adicionar(String nome, String alias, String celular, String relacionamento) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.ALIAS, alias);
            cv.put(Constantes.CELULAR, celular);
            cv.put(Constantes.RELACIONAMENTO, relacionamento);
            return db.insert(Constantes.TABELA_PESSOA, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor buscar() {
        String[] columns = {Constantes.ID, Constantes.NOME, Constantes.ALIAS, Constantes.CELULAR, Constantes.RELACIONAMENTO};
        return db.query(Constantes.TABELA_PESSOA, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long atualizar(int id, String nome, String alias, String celular, String relacionamento) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.ALIAS, alias);
            cv.put(Constantes.CELULAR, celular);
            cv.put(Constantes.RELACIONAMENTO, relacionamento);
            return db.update(Constantes.TABELA_PESSOA, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long apagar(int id) {
        try {
            return db.delete(Constantes.TABELA_PESSOA, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
