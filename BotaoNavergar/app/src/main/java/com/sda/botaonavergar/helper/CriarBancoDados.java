package com.sda.botaonavergar.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sda.botaonavergar.util.Constantes;

public class CriarBancoDados extends SQLiteOpenHelper {

    public CriarBancoDados(Context context) {
        super(context, Constantes.DATA_BASE, null, Constantes.DATA_VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(Constantes.CREATE_TB_EMPRESA);
            db.execSQL(Constantes.CREATE_TB_TURNO);
            db.execSQL(Constantes.CREATE_TB_CARGO);
            db.execSQL(Constantes.CREATE_TB_RELACIONAMENTO);
            db.execSQL(Constantes.CREATE_TB_PESSOA);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Constantes.DELETE_TABLE + Constantes.CREATE_TB_EMPRESA);
        onCreate(db);
    }
}
