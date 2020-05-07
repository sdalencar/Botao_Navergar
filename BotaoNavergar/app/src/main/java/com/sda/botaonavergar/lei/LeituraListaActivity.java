package com.sda.botaonavergar.lei;

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
import com.sda.botaonavergar.empresa.Empresa;
import com.sda.botaonavergar.empresa.EmpresaAdapter;
import com.sda.botaonavergar.empresa.EmpresaDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;


public class LeituraListaActivity extends AppCompatActivity {

    private Utilidades msg;
    private Context ctx;

    private ArrayList<Leitura> leituras = new ArrayList<>();
    private RecyclerView rv;
    private LeituraAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresa_lista_activity);

        ctx = getBaseContext();

        rv = findViewById(R.id.myRecyclerEmpresa);
        rv.setLayoutManager(new LinearLayoutManager(ctx));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new LeituraAdapter(ctx, leituras);


        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iniciaComponentes();

    }

    private void iniciaComponentes(){
        msg = new Utilidades();
        bucar();
    }

    private void bucar(){
        LeituraDao dao = new LeituraDao(ctx);
        dao.openDB();
        leituras.clear();
        Cursor cs = (Cursor) dao.burcar();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            int inumero = cs.getInt(1);
            int ientrada = cs.getInt(2);
            int isaida = cs.getInt(3);

            Leitura leitura = new Leitura(id,inumero,ientrada,isaida);
            leituras.add(leitura);
        }
        if (!(leituras.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucar();
    }
}
