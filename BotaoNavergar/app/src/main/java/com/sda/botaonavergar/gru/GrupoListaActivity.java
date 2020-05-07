package com.sda.botaonavergar.gru;

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
import com.sda.botaonavergar.pes.Pessoa;
import com.sda.botaonavergar.pes.PessoaAdapter;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class GrupoListaActivity extends AppCompatActivity {

    private Utilidades msg;
    private Context ctx;

    private ArrayList<Grupo> grupos = new ArrayList<>();
    private RecyclerView rv;
    private GrupoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_edit_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerTresEdit);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new GrupoAdapter(ctx, grupos);

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
        msg = new Utilidades();
        ctx = this;
    }

    private void buscar() {
        GrupoDao dao = new GrupoDao(ctx);
        dao.openDB();
        grupos.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String ppt = cs.getString(1);
            String nm = cs.getString(2);
            double vlr = cs.getDouble(3);
            Grupo gr = new Grupo(id, ppt, nm, vlr);
            grupos.add(gr);
        }
        if (!(grupos.size() < 1)) {
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
