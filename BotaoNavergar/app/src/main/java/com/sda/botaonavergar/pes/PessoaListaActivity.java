package com.sda.botaonavergar.pes;

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
public class PessoaListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Pessoa> pessoas = new ArrayList<>();
    private RecyclerView rv;
    private PessoaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerPessoa);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new PessoaAdapter(ctx, pessoas);

        buscar();

        FloatingActionButton fab = findViewById(R.id.fab_pes_lista);
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
        PessoaDao dao = new PessoaDao(ctx);
        dao.openDB();
        pessoas.clear();
        Cursor cs = (Cursor) dao.buscar();
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String nome = cs.getString(1);
            String alias = cs.getString(2);
            String celular = cs.getString(3);
            String relacionamento = cs.getString(4);
            Pessoa pessoa = new Pessoa(id, nome, alias, celular, relacionamento);
            pessoas.add(pessoa);
        }
        if (!(pessoas.size() < 1)) {
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
