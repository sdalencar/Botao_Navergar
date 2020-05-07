package com.sda.botaonavergar.fech;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.sda.botaonavergar.helper.CriarBancoDados;
import com.sda.botaonavergar.util.Constantes;

public class FechamentoDao {

    private CriarBancoDados helper;
    private Context ctx;
    private SQLiteDatabase db;

    public FechamentoDao(Context ctx) {
        this.ctx = ctx;
        helper = new CriarBancoDados(ctx);
    }

    //OPEN DB
    public FechamentoDao openDB() {
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
    public long adiciona(String user, String data, String turno, double entrada, double saida, double sangria, double pagamentoManual, double pgtFunc, double despesas, double outros, double valeFeito, double valePago, double bonus, double cartao, double cheque, double dinheiro) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.USER, user);
            cv.put(Constantes.DATA_ATUAL, data);
            cv.put(Constantes.TURNO, turno);
            cv.put(Constantes.ENTRADA, entrada);
            cv.put(Constantes.SAIDA, saida);
            cv.put(Constantes.SANGRIA, sangria);
            cv.put(Constantes.PGT_MANUAL, pagamentoManual);
            cv.put(Constantes.PGT_FUNC, pgtFunc);
            cv.put(Constantes.DESPESA, despesas);
            cv.put(Constantes.OUTRO, outros);
            cv.put(Constantes.VALE_FEITO, valeFeito);
            cv.put(Constantes.VALE_PAGO, valePago);
            cv.put(Constantes.BONUS, bonus);
            cv.put(Constantes.CARTAO, cartao);
            cv.put(Constantes.CHEQUE, cheque);
            cv.put(Constantes.DINHEIRO, dinheiro);
            return db.insert(Constantes.TABELA_FECHAMENTO, Constantes.ID, cv);
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
