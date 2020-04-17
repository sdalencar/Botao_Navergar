package com.sda.botaonavergar.pes;

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
public class PessoaActivity extends AppCompatActivity {

    private EditText enome, ealias, ecelular, erelacionamento;
    private Mensagem msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome = findViewById(R.id.editText_pes_nome);
        ealias = findViewById(R.id.editText_pes_alias);
        ecelular = findViewById(R.id.editText_pes_celular);
        erelacionamento = findViewById(R.id.editText_pes_relacionamento);

        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab_pessoa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onClickPessoa(View view) {
        switch (view.getId()) {
            case R.id.button_pes_grava:
                verificaCampos();
                break;
            case R.id.button_pes_listar:
                startActivity(new Intent(ctx, PessoaListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (enome.getText().toString().equals("") || ealias.getText().toString().equals("") || ecelular.getText().toString().equals("") || erelacionamento.getText().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.erro_campos_vazios));
        } else {
            salvar();
        }
        limpaCampos();
    }

    private void salvar() {
        //salva na variael os valores do editTexts
        String s_nome = enome.getText().toString();
        String s_alias = ealias.getText().toString();
        String s_celular = ecelular.getText().toString();
        String s_relacionamento = erelacionamento.getText().toString();
        //salva no banco os dados guardados nas variaveis
        PessoaDao dao = new PessoaDao(ctx);
        dao.openDB();
        long salvou = dao.adicionar(s_nome, s_alias,s_celular,s_relacionamento);
        if (salvou != 0) {
            msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
        } else {
            msg.mensagenLonga(ctx, "ERRO Salvando os dados");
        }

    }

    private void limpaCampos() {
        enome.setText("");
        enome.requestFocus();
        ealias.setText("");
        ecelular.setText("");
        erelacionamento.setText("");
    }
}
