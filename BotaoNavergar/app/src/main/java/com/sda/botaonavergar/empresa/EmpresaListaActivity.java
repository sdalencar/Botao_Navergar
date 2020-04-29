package com.sda.botaonavergar.empresa;

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


public class EmpresaListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Empresa> empresas = new ArrayList<>();
    private RecyclerView rv;
    private EmpresaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresa_lista_activity);

        iniciaComponentes();

        rv = (RecyclerView) findViewById(R.id.myRecyclerEmpresa);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new EmpresaAdapter(ctx, empresas);

        bucarEmpresas();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_rodape_fab);
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

    private void bucarEmpresas(){
        EmpresaDao dao = new EmpresaDao(ctx);
        dao.openDB();
        empresas.clear();
        Cursor cs = (Cursor) dao.getAllEmpresa();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String nome = cs.getString(1);
            String endereco = cs.getString(2);
            String telefone = cs.getString(3);

            Empresa empresa = new Empresa(id,nome,endereco,telefone);
            empresas.add(empresa);
        }
        if (!(empresas.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucarEmpresas();
    }
}
