package com.sda.botaonavergar.chq;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.des.Despesa;
import com.sda.botaonavergar.des.DespesaAdapter;
import com.sda.botaonavergar.des.DespesaDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class ChequeListaActivity extends AppCompatActivity {

    private Utilidades msg;
    private Context ctx;

    private ArrayList<Cheque> cheques = new ArrayList<>();
    private RecyclerView rv;
    private ChequeAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_lista_activity);

        iniciaComponentes();

        rv = findViewById(R.id.myRecyclerQuatro);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new ChequeAdapter(ctx, cheques);

        bucarCheques();

        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void iniciaComponentes(){
        TextView titulo = findViewById(R.id.titulo);
        titulo.setText(getResources().getString(R.string.cheque));
        titulo.setTextSize(40);

        msg = new Utilidades();
        ctx = this;
    }

    private void bucarCheques(){
        ChequeDao dao = new ChequeDao(ctx);
        dao.openDB();
        cheques.clear();
        Cursor cs = dao.buscar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String nome_chq = cs.getString(1);
            String nome_cli = cs.getString(2);
            String banco = cs.getString(3);
            String numero = cs.getString(4);
            double valor = cs.getDouble(5);
            String data = cs.getString(6);
            String depositar = cs.getString(7);
            Cheque dados_chq = new Cheque(id, nome_chq, nome_cli, banco, numero, valor, data, depositar);
            cheques.add(dados_chq);
        }
        if (!(cheques.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucarCheques();
    }
}
