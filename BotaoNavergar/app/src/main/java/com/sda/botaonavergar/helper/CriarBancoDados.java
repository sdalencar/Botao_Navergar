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
            db.execSQL(Constantes.CREATE_TB_CARTAO);
            db.execSQL(Constantes.CREATE_TB_VALE_FEITO);
            db.execSQL(Constantes.CREATE_TB_VALE_PAGO);
            db.execSQL(Constantes.CREATE_TB_BONUS);
            db.execSQL(Constantes.CREATE_TB_TIPO_BONUS);
            db.execSQL(Constantes.CREATE_TB_CARTOES);
            db.execSQL(Constantes.CREATE_TB_DESPESA);
            db.execSQL(Constantes.CREATE_TB_DESPESA_ADD);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
