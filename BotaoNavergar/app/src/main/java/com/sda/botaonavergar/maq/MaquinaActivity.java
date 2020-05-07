package com.sda.botaonavergar.maq;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.gru.GrupoDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class MaquinaActivity extends AppCompatActivity {
    private EditText enumero;
    private Spinner sp_grupo;
    private TextView titulo;
    private Utilidades msg;
    private Context ctx;

    private ArrayList<String> maquinas = new ArrayList<>();
    private ArrayAdapter<String> adapterNomeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_activity);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        titulo = findViewById(R.id.titulo);
        titulo.setText(getResources().getString(R.string.maquina));
        titulo.setTextSize(40);

        sp_grupo = findViewById(R.id.quatro_segundo_sp);
        enumero = findViewById(R.id.quatro_quarto_et);
        enumero.setHint(getResources().getString(R.string.n_mero));

        msg = new Utilidades();
        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        preencherSpinners();

        adapterNomeSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, maquinas);

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, MaquinaListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (sp_grupo.getSelectedItem().toString().equals("-") || enumero.getText().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salva();
        }
        limpaCampos();
    }

    private void salva() {
        MaquinaDao dao = new MaquinaDao(ctx);
        dao.openDB();
        long salvou = dao.adiciona(sp_grupo.getSelectedItem().toString(), Integer.parseInt(enumero.getText().toString()));
        if (salvou != 0) {
            msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
        } else {
            msg.mensagenLonga(ctx, "ERRO Salvando os dados");
        }

    }

    private void limpaCampos() {
        enumero.setText("");
        enumero.requestFocus();
        preencherSpinners();

    }

    private void preencherSpinners() {
        GrupoDao dao = new GrupoDao(ctx);
        maquinas.clear();
        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscar();
        while (c.moveToNext()) {
            String grupo = c.getString(2);
            maquinas.add(grupo);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER
        sp_grupo.setAdapter(adapterNomeSpinner);
        sp_grupo.setPrompt(getResources().getString(R.string.escolhoa_opcao));
    }

    @Override
    protected void onResume() {
        preencherSpinners();
        super.onResume();
    }
}
