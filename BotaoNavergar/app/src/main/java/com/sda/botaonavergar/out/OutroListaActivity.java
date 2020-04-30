package com.sda.botaonavergar.out;

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
public class OutroListaActivity extends AppCompatActivity {

    private Utilidades msg;
    private Context ctx;

    private ArrayList<Outro> outros = new ArrayList<>();
    private RecyclerView rv;
    private OutroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dois_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerDois);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new OutroAdapter(ctx, outros);

        buscarOutro();

        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void iniciaComponentes(){

        TextView tv = findViewById(R.id.um_titulo);
        tv.setText(getResources().getString(R.string.listar_outros));
        tv.setTextSize(40);

        msg = new Utilidades();
        ctx = this;
    }

    private void buscarOutro(){
        OutroDao dao = new OutroDao(ctx);
        dao.openDB();
        outros.clear();
        Cursor cs = dao.busca();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String nome = cs.getString(1);
            double vlr = cs.getDouble(2);
            Outro out = new Outro(id,nome, vlr);
            outros.add(out);
        }
        if (!(outros.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buscarOutro();
    }
}
