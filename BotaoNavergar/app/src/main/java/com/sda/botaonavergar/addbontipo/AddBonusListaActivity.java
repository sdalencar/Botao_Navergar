package com.sda.botaonavergar.addbontipo;


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
public class AddBonusListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<AddBonus> bonus = new ArrayList<>();
    private RecyclerView rv;
    private AddBonusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerQuatro);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new AddBonusAdapter(ctx, bonus);

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
        AddBonusDao dao = new AddBonusDao(ctx);
        dao.openDB();
        bonus.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()) {
            int id = cs.getInt(0);
            String ident = cs.getString(1);
            String nome = cs.getString(2);
            String relacao = cs.getString(3);
            double valor = cs.getDouble(4);
            AddBonus bns = new AddBonus(id, ident, nome, relacao, valor);
            bonus.add(bns);
        }
        if (!(bonus.size() < 1)) {
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
