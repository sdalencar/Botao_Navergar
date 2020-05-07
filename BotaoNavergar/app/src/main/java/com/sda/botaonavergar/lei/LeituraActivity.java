package com.sda.botaonavergar.lei;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.empresa.EmpresaDao;
import com.sda.botaonavergar.util.Utilidades;

public class LeituraActivity extends AppCompatActivity {

    private EditText enumero, eentrada, esaida;
    private Utilidades msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresa_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enumero = findViewById(R.id.et_empresa_nome);
        enumero.setHint(getResources().getString(R.string.n_mero));
        enumero.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);

        eentrada = findViewById(R.id.et_empresa_endereco);
        eentrada.setHint(getResources().getString(R.string.entrada));
        eentrada.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);

        esaida = findViewById(R.id.et_empresa_telefone);
        esaida.setHint(getResources().getString(R.string.saida));
        esaida.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);

        msg = new Utilidades();
        ctx = this;

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
                startActivity(new Intent(ctx, LeituraListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if(enumero.getText().toString().equals("") || eentrada.getText().toString().equals("")|| esaida.getText().toString().equals("")){
             msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        }else {
            salvar();
        }
        limpaCampos();
    }

    private void salvar() {
            //salva na variael os valores do editTexts
            int inumero = Integer.parseInt(enumero.getText().toString());
            int ientrada = Integer.parseInt(eentrada.getText().toString());
            int isaida = Integer.parseInt(esaida.getText().toString());
            //salva no banco os dados guardados nas variaveis
            LeituraDao dao = new LeituraDao(ctx);
            dao.openDB();
            long salvou = dao.adiciona(inumero, ientrada, isaida);
            if (salvou != 0) {
                msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
            } else {
                msg.mensagenLonga(ctx, "ERRO Salvando os dados");
            }

    }

    private void limpaCampos(){
        enumero.setText("");
        enumero.requestFocus();
        eentrada.setText("");
        esaida.setText("");
    }
}
