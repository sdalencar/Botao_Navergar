package com.sda.botaonavergar.turno;


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

import java.util.ArrayList;

/**
 * made by sda
 */
public class TurnoListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Turno> turnos = new ArrayList<>();
    private RecyclerView rv;
    private TurnoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerTurno);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new TurnoAdapter(ctx, turnos);

        buscar();

        FloatingActionButton fab = findViewById(R.id.fab_turno_lista);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void iniciaComponentes(){
        msg = new Mensagem();
        ctx = this;
    }

    private void buscar(){
        TurnoDao dao = new TurnoDao(ctx);
        dao.openDB();
        turnos.clear();
        Cursor cs = (Cursor) dao.buscar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String numero = cs.getString(1);
            String nome = cs.getString(2);
            Turno turno = new Turno(id, numero, nome);
            turnos.add(turno);
        }
        if (!(turnos.size() < 1)){
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
