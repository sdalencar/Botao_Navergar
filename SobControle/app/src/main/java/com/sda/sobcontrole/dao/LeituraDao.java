package com.sda.sobcontrole.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.sda.sobcontrole.bd.DbGateway;
import com.sda.sobcontrole.model.Leitura;
import com.sda.sobcontrole.util.Constantes;

import java.util.ArrayList;
import java.util.List;

public class LeituraDao {

    public static final String CREATE_TABLE_LEITURA = "create table " + Constantes.TABELA_LEITURA + " (" + Constantes.ID + " integer primary key autoincrement, " + Constantes.IDENTIFICADOR + " text, " + Constantes.GRUPO + " text," + Constantes.NUMERO + " integer," + Constantes.ENTRADA + " integer, " + Constantes.SAIDA + " integer );";

    private DbGateway gw;

    public LeituraDao(Context ctx) {
        this.gw = DbGateway.getInstance(ctx);
    }

    public boolean salvarLeitura(String identificador, String grupo, int numero, int entrada, int saida) {
        ContentValues cv = new ContentValues();
        cv.put(Constantes.IDENTIFICADOR, identificador);
        cv.put(Constantes.GRUPO, grupo);
        cv.put(Constantes.NUMERO, numero);
        cv.put(Constantes.ENTRADA, entrada);
        cv.put(Constantes.SAIDA, saida);
        return gw.getDatabase().insert(Constantes.TABELA_LEITURA, null, cv) > 0;

    }

    public boolean salvarLeitura(int id, String identificador, String grupo, int numero, int entrada, int saida) {
        ContentValues cv = new ContentValues();
        cv.put(Constantes.ID, id);
        cv.put(Constantes.IDENTIFICADOR, identificador);
        cv.put(Constantes.GRUPO, grupo);
        cv.put(Constantes.NUMERO, numero);
        cv.put(Constantes.ENTRADA, entrada);
        cv.put(Constantes.SAIDA, saida);
        if (id > 0) {
            return gw.getDatabase().update(Constantes.TABELA_LEITURA, cv, Constantes.ID + " = ?", new String[]{id + ""}) > 0;
        } else {
            return salvarLeitura(identificador, grupo, numero, entrada, saida);
        }
    }

    public List<Leitura> retornaLeitura() {
        List<Leitura> listaLeituras = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("select * from " + Constantes.TABELA_LEITURA, null);
        while (cursor.moveToNext()) {
            Leitura leitura;
            int id = cursor.getInt(cursor.getColumnIndex(Constantes.ID));
            String identificador = cursor.getString(cursor.getColumnIndex(Constantes.IDENTIFICADOR));
            String grupo = cursor.getString(cursor.getColumnIndex(Constantes.GRUPO));
            int numero = cursor.getInt(cursor.getColumnIndex(Constantes.NUMERO));
            int entrada = cursor.getInt(cursor.getColumnIndex(Constantes.ENTRADA));
            int saida = cursor.getInt(cursor.getColumnIndex(Constantes.SAIDA));

            leitura = new Leitura(id, identificador, grupo, numero, entrada, saida);

            listaLeituras.add(leitura);

        }
        cursor.close();
        return listaLeituras;
    }

    public boolean excluirLeitura(int id) {
        return gw.getDatabase().delete(Constantes.TABELA_LEITURA, Constantes.ID + " = ? ", new String[]{id + ""}) > 0;
    }

    public Leitura retornaUltimoLeitura() {
        Cursor cs = gw.getDatabase().rawQuery("select * from " + Constantes.TABELA_LEITURA + " order by " + Constantes.ID + " desc ", null);
        if (cs.moveToFirst()) {
            int id = cs.getInt(cs.getColumnIndex(Constantes.ID));
            String identificador = cs.getString(cs.getColumnIndex(Constantes.IDENTIFICADOR));
            String grupo = cs.getString(cs.getColumnIndex(Constantes.GRUPO));
            int numero = cs.getInt(cs.getColumnIndex(Constantes.NUMERO));
            int entrada = cs.getInt(cs.getColumnIndex(Constantes.ENTRADA));
            int saida = cs.getInt(cs.getColumnIndex(Constantes.SAIDA));
            cs.close();
            return new Leitura(id, identificador, grupo, numero, entrada, saida);
        }
        return null;
    }


}
