package com.sda.botaonavergar.maq;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class MaquinaListaActivity extends AppCompatActivity {

    private Utilidades msg;
    private Context ctx;

    private ArrayList<Maquina> maquinas = new ArrayList<>();
    private RecyclerView rv;
    private MaquinaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerQuatro);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new MaquinaAdapter(ctx, maquinas);

        buscar();

        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void iniciaComponentes() {
        TextView tv =findViewById(R.id.titulo);
        tv.setText(getResources().getString(R.string.listar_maquina));
        tv.setTextSize(40);
        msg = new Utilidades();
        ctx = this;
    }

    private void buscar() {
        MaquinaDao dao = new MaquinaDao(ctx);
        dao.openDB();
        maquinas.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String grupo = cs.getString(1);
            int valor = cs.getInt(2);
            Maquina mqn = new Maquina(id, grupo, valor);
            maquinas.add(mqn);
        }
        if (!(maquinas.size() < 1)) {
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscar();
    }
}
