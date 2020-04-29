package com.sda.botaonavergar.cartao;

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
import com.sda.botaonavergar.cargo.Cargo;
import com.sda.botaonavergar.cargo.CargoDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class CartaoListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Cartao> tiposCartoes = new ArrayList<>();
    private RecyclerView rv;
    private CartaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.um_edittext_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerUmEdittext);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new CartaoAdapter(ctx, tiposCartoes);

        bucarCartao();

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

    private void bucarCartao(){
        CartaoDao dao = new CartaoDao(ctx);
        dao.openDB();
        tiposCartoes.clear();
        Cursor cs = (Cursor) dao.buscar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String nome = cs.getString(1);
            Cartao cartao = new Cartao(id,nome);
            tiposCartoes.add(cartao);
        }
        if (!(tiposCartoes.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucarCartao();
    }
}
