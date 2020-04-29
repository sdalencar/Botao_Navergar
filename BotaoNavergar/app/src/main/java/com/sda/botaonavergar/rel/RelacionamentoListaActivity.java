package com.sda.botaonavergar.rel;

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
public class RelacionamentoListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Relacionamento> relacionamentos = new ArrayList<>();
    private RecyclerView rv;
    private RelacionamentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relacionamento_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerRelacionamento);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new RelacionamentoAdapter(ctx, relacionamentos);

        busbarRelacionmento();

        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
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

    private void busbarRelacionmento(){
        RelacionamentoDao dao = new RelacionamentoDao(ctx);
        dao.openDB();
        relacionamentos.clear();
        Cursor cs = (Cursor) dao.busca();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String nome = cs.getString(1);
            Relacionamento relacionamento = new Relacionamento(id,nome);
            relacionamentos.add(relacionamento);
        }
        if (!(relacionamentos.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        busbarRelacionmento();
    }
}
