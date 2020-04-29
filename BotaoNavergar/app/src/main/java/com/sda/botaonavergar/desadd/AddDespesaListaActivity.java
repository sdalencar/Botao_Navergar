package com.sda.botaonavergar.desadd;

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
import com.sda.botaonavergar.cartao.CartaoDao;
import com.sda.botaonavergar.des.Despesa;
import com.sda.botaonavergar.des.DespesaAdapter;
import com.sda.botaonavergar.des.DespesaDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class AddDespesaListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<AddDespesa> despesasAdd = new ArrayList<>();
    private RecyclerView rv;
    private AddDespesaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.um_edittext_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerUmEdittext);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new AddDespesaAdapter(ctx, despesasAdd);

        bucarDespesa();

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

    private void bucarDespesa(){
        AddDespesaDao dao = new AddDespesaDao(ctx);
        dao.openDB();
        despesasAdd.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String siden = cs.getString(1);
            String sdata = cs.getString(2);
            String snome = cs.getString(3);
            double svlr = cs.getDouble(4);
            AddDespesa despesa = new AddDespesa(id,siden,sdata,snome,svlr);
            despesasAdd.add(despesa);
        }
        if (!(despesasAdd.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucarDespesa();
    }
}
