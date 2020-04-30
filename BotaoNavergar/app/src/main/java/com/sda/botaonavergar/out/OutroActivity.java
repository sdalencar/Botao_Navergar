package com.sda.botaonavergar.out;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.rel.RelacionamentoDao;
import com.sda.botaonavergar.rel.RelacionamentoListaActivity;
import com.sda.botaonavergar.util.Utilidades;

public class OutroActivity extends AppCompatActivity {

    private EditText enome, evalor;
    private Utilidades msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dois_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome = findViewById(R.id.dois_activity_dois_tv);
        enome.setHint("Descreva");
        evalor = findViewById(R.id.dois_activity_um_tv);
        evalor.setHint("Valor");
        msg = new Utilidades();
        ctx = this;

        TextView titulo = findViewById(R.id.um_titulo);
        titulo.setText(getResources().getString(R.string.outros));
        titulo.setTextSize(40);

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, OutroListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if(enome.getText().toString().equals("") || evalor.getText().toString().equals("")){
             msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        }else {
            salvar();
        }
        limpaCampos();
    }

    private void salvar() {
            //salva na variael os valores do editTexts
            String s_nome = enome.getText().toString();
            double dvalor = Double.parseDouble(evalor.getText().toString());
            //salva no banco os dados guardados nas variaveis
            OutroDao dao = new OutroDao(ctx);
            dao.openDB();
            long salvou = dao.adiciona(s_nome, dvalor);
            if (salvou != 0) {
                msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
            } else {
                msg.mensagenLonga(ctx, "ERRO Salvando os dados");
            }

    }

    private void limpaCampos(){
        enome.setText("");
        enome.requestFocus();
        evalor.setText("");
    }
}
