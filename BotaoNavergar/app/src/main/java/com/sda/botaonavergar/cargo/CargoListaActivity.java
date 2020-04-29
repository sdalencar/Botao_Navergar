package com.sda.botaonavergar.cargo;

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
public class CargoListaActivity extends AppCompatActivity {

    private Mensagem msg;
    private Context ctx;

    private ArrayList<Cargo> cargos = new ArrayList<>();
    private RecyclerView rv;
    private CargoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargo_lista_activity);

        iniciaComponentes();

        rv = (RecyclerView) findViewById(R.id.myRecyclerCargo);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        adapter = new CargoAdapter(ctx, cargos);

        bucarCargo();

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

    private void bucarCargo(){
        CargoDao dao = new CargoDao(ctx);
        dao.openDB();
        cargos.clear();
        Cursor cs = (Cursor) dao.getAllCargo();
        while (cs.moveToNext()){
            int id = cs.getInt(0);
            String nome = cs.getString(1);
            Cargo cargo = new Cargo(id,nome);
            cargos.add(cargo);
        }
        if (!(cargos.size() < 1)){
            rv.setAdapter(adapter);
        }
        dao.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bucarCargo();
    }
}
