package com.sda.botaonavergar.cartoes;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Mensagem;
import com.sda.botaonavergar.valefeito.ValeFeito;
import com.sda.botaonavergar.valefeito.ValeFeitoAdapter;
import com.sda.botaonavergar.valefeito.ValeFeitoDao;

import java.util.ArrayList;

/**
 * made by sda
 */
public class CartoesListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Cartoes> bandeira = new ArrayList<>();
    private RecyclerView rv;
    private CartoesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerTres);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new CartoesAdapter(ctx, bandeira);

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
        msg = new Mensagem();
        ctx = this;
    }

    private void buscar() {
        CartoesDao dao = new CartoesDao(ctx);
        dao.openDB();
        bandeira.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String ident = cs.getString(1);
            String doc = cs.getString(2);
            String tipo = cs.getString(2);
            double valor = cs.getDouble(3);
            Cartoes card = new Cartoes(id, ident, doc, tipo, valor);
            bandeira.add(card);
        }
        if (!(bandeira.size() < 1)) {
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
