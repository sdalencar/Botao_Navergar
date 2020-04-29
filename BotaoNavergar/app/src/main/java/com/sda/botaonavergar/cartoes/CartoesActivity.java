package com.sda.botaonavergar.cartoes;


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
import com.sda.botaonavergar.cartao.CartaoDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class CartoesActivity extends AppCompatActivity {
    private EditText evalor, edoc;
    private Spinner sp_tipo;
    private TextView identificador, titulo;
    private Mensagem msg;
    private Context ctx;

    private ArrayList<String> tipos = new ArrayList<>();
    private ArrayAdapter<String> adapterTipoSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_activity);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        titulo = findViewById(R.id.textView_tres);
        titulo.setText(getResources().getString(R.string.add_cartoes));
        identificador = findViewById(R.id.tres_primeiro_tv);
        sp_tipo = findViewById(R.id.tres_segundo_sp);
        edoc = findViewById(R.id.tres_terceiro_et);
        evalor = findViewById(R.id.tres_quarto_et);

        identificador.setText("turno / data / caixa");

        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        preencherSpinners();

        adapterTipoSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tipos);

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, CartoesListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (sp_tipo.getSelectedItem().toString().equals("-") || edoc.getText().toString().equals("") || evalor.getText().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salva();
        }
        limpaCampos();
    }

    private void salva() {
        CartoesDao dao = new CartoesDao(ctx);
        dao.openDB();
        long salvou = dao.adiciona(identificador.getText().toString(), sp_tipo.getSelectedItem().toString(), edoc.getText().toString(), Double.parseDouble(evalor.getText().toString()));
        if (salvou != 0) {
            msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
        } else {
            msg.mensagenLonga(ctx, "ERRO Salvando os dados");
        }

    }

    private void limpaCampos() {
        evalor.setText("");
        evalor.requestFocus();
        preencherSpinners();

    }

    private void preencherSpinners() {
        CartaoDao dao = new CartaoDao(ctx);
        tipos.clear();
        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscar();
        while (c.moveToNext()) {
            String banderia = c.getString(1);
            tipos.add(banderia);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER

        sp_tipo.setAdapter(adapterTipoSpinner);
        sp_tipo.setPrompt(getResources().getString(R.string.escolhoa_opcao));

    }

    @Override
    protected void onResume() {
        preencherSpinners();
        super.onResume();
    }
}
