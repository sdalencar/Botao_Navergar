package com.sda.botaonavergar.gru;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.pes.PessoaListaActivity;
import com.sda.botaonavergar.util.Utilidades;

/**
 * made by sda
 */
public class GrupoActivity extends AppCompatActivity {

    private EditText eproprietario, enome, evalor;
    private Utilidades msg;
    private Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_edit_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        TextView tv = findViewById(R.id.titulo);
        tv.setText(getResources().getString(R.string.grupo));
        eproprietario = findViewById(R.id.tres_edit_um_tv);
        eproprietario.setHint(getResources().getString(R.string.propriet_rio));
        enome = findViewById(R.id.tres_edit_dois_tv);
        enome.setHint(getResources().getString(R.string.nome));
        evalor = findViewById(R.id.tres_edit_tres_tv);
        evalor.setHint(getResources().getString(R.string.valor));

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
                startActivity(new Intent(ctx, GrupoListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (enome.getText().toString().equals("") || eproprietario.getText().toString().equals("") || evalor.getText().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salvar();
        }
        limpaCampos();
    }

    private void salvar() {
        //salva na variael os valores do editTexts
        String sproprietario = eproprietario.getText().toString();
        String snome = enome.getText().toString();
        double dvalor = Double.parseDouble(evalor.getText().toString());
        //salva no banco os dados guardados nas variaveis
        GrupoDao dao = new GrupoDao(ctx);
        dao.openDB();
        long salvou = dao.adicionar(sproprietario, snome, dvalor);
        if (salvou != 0) {
            msg.mensagenLonga(ctx, getResources().getString(R.string.msg_salvando_dados));
        } else {
            msg.mensagenLonga(ctx, getResources().getString(R.string.msg_erro_salvando_dados));
        }

    }

    private void limpaCampos() {
        eproprietario.setText("");
        eproprietario.requestFocus();
        enome.setText("");
        evalor.setText("");
    }

}

