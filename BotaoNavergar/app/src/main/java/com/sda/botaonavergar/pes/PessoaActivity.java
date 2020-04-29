package com.sda.botaonavergar.pes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.rel.RelacionamentoDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class PessoaActivity extends AppCompatActivity {

    private EditText enome, ealias, ecelular;
    private Spinner sprelacionamento;
    private Mensagem msg;
    private Context ctx;

    private ArrayList<String> relacoes = new ArrayList<>();
    private ArrayAdapter<String> adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome = findViewById(R.id.et_pessoa_nome);
        ealias = findViewById(R.id.et_pessoa_alias);
        ecelular = findViewById(R.id.et_pessoa_telefone);
        sprelacionamento = findViewById(R.id.sp_pessoa_relacao);
        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, relacoes);
        preenecherSpinner();

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, PessoaListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (enome.getText().toString().equals("") || ealias.getText().toString().equals("") || ecelular.getText().toString().equals("") || sprelacionamento.getSelectedItem().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
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
        String s_relacionamento = sprelacionamento.getSelectedItem().toString();
        //salva no banco os dados guardados nas variaveis
        PessoaDao dao = new PessoaDao(ctx);
        dao.openDB();
        long salvou = dao.adicionar(s_nome, s_alias, s_celular, s_relacionamento);
        if (salvou != 0) {
            msg.mensagenLonga(ctx, getResources().getString(R.string.msg_salvando_dados));
        } else {
            msg.mensagenLonga(ctx, getResources().getString(R.string.msg_erro_salvando_dados));
        }

    }

    private void limpaCampos() {
        enome.setText("");
        enome.requestFocus();
        ealias.setText("");
        ecelular.setText("");
        preenecherSpinner();
    }

    private void preenecherSpinner() {
        RelacionamentoDao dao = new RelacionamentoDao(ctx);
        relacoes.clear();
        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.busca();
        while (c.moveToNext()) {
            String name = c.getString(1);
            relacoes.add(name);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER
        sprelacionamento.setAdapter(adapterSpinner);
        sprelacionamento.setPrompt(getResources().getString(R.string.escolhoa_opcao));
    }

    @Override
    protected void onResume() {
        preenecherSpinner();
        super.onResume();
    }
}

