package com.sda.botaonavergar.des;

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
import com.sda.botaonavergar.cartao.Cartao;
import com.sda.botaonavergar.cartao.CartaoAdapter;
import com.sda.botaonavergar.cartao.CartaoDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class DespesaListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Despesa> despesas = new ArrayList<>();
    private RecyclerView rv;
    private DespesaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.um_edittext_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerUmEdittext);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new DespesaAdapter(ctx, despesas);

        bucarDespesas();

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

    private void bucarDespesas(){
        DespesaDao dao = new DespesaDao(ctx);
        dao.openDB();
        despesas.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String tipo = cs.getString(1);
            Despesa desp = new Despesa(id,tipo);
            despesas.add(desp);
        }
        if (!(despesas.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucarDespesas();
    }
}
