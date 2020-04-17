package com.sda.sobcontrole.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sda.sobcontrole.dao.LeituraDao;
import com.sda.sobcontrole.util.Constantes;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context ctx) {
        super(ctx, Constantes.DATA_BASE, null, Constantes.DATA_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LeituraDao.CREATE_TABLE_LEITURA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
