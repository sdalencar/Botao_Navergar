package com.sda.botaonavergar.empresa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sda.botaonavergar.helper.CriarBancoDados;
import com.sda.botaonavergar.util.Constantes;

public class EmpresaDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public EmpresaDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public EmpresaDao openDB() {
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
    public long addEmpresa(String nome, String endereco, String telefone) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.ENDERECO, endereco);
            cv.put(Constantes.TELEFONE, telefone);
            return db.insert(Constantes.TABELA_EMPRESA, Constantes.ID, cv);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //buscar todos
    public Cursor getAllEmpresa() {
        String[] columns = {Constantes.ID, Constantes.NOME, Constantes.ENDERECO, Constantes.TELEFONE};
        return db.query(Constantes.TABELA_EMPRESA, columns, null, null, null, null, null);
    }

    //atualizar no bd
    public long updateEmpresa(int id, String nome, String endereco, String telefone) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID, id);
            cv.put(Constantes.NOME, nome);
            cv.put(Constantes.ENDERECO, endereco);
            cv.put(Constantes.TELEFONE, telefone);
            return db.update(Constantes.TABELA_EMPRESA, cv, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //apagar
    public long deletaEmpresa(int id) {
        try {
            return db.delete(Constantes.TABELA_EMPRESA, Constantes.ID + " =?", new String[]{String.valueOf(id)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
