package com.sda.botaonavergar.turno;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Mensagem;

/**
 * made by sda
 */
public class TurnoActivity extends AppCompatActivity {

    private EditText enumero, enome;
    private Mensagem msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_activity);

        iniciaComponentes();

    }

    private void iniciaComponentes() {
        enumero = findViewById(R.id.editText_turno_numero);
        enome = findViewById(R.id.editText_turno_nome);
        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_turno);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onClickTurno(View view) {
        switch (view.getId()) {
            case R.id.button_turno_grava:
                verificaCampos();
                break;
            case R.id.button_turno_listar:
                startActivity(new Intent(ctx, TurnoListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if(enome.getText().toString().equals("") || enumero.getText().toString().equals("")){
             msg.mensagenCurta(ctx, getResources().getString(R.string.erro_campos_vazios));
        }else {
            salvaTurno();
        }
        limpaCampos();
    }

    private void salvaTurno() {
            //salva na variael os valores do editTexts
            int s_numero = Integer.parseInt(enumero.getText().toString());
            String s_nome = enome.getText().toString();
            //salva no banco os dados guardados nas variaveis
            TurnoDao dao = new TurnoDao(ctx);
            dao.openDB();
            long salvou = dao.adiciona(s_numero, s_nome);
            if (salvou != 0) {
                msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
            } else {
                msg.mensagenLonga(ctx, "ERRO Salvando os dados");
            }

    }

    private void limpaCampos(){
        enumero.setText("");
        enumero.requestFocus();
        enome.setText("");
    }
}
