package com.sda.botaonavergar.pes;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.rel.RelacionamentoDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class PessoaAtualizaActivity extends AppCompatActivity {

    private EditText e_nome, e_alias, e_celular;
    private Spinner sp_relacionamento;
    private AlertDialog alerta;
    private Button btdireito, btesquerdo;
    private int id = 0;
    private String snome = "", salias = "", scelular = "", srelacionamento = "";
    private Context ctx;
    private Mensagem msg;

    private ArrayList<String> relacoes = new ArrayList<>();
    private ArrayAdapter<String> adapterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa_atualiza_activity);

        ctx = this;
        msg = new Mensagem();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        snome = i.getExtras().getString(Constantes.NOME);
        salias = i.getExtras().getString(Constantes.ALIAS);
        scelular = i.getExtras().getString(Constantes.CELULAR);
        srelacionamento = i.getExtras().getString(Constantes.RELACIONAMENTO);
        iniciaComponentes();

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void iniciaComponentes() {

        e_nome = findViewById(R.id.et_pessoa_atualiza_nome);
        e_alias = findViewById(R.id.et_pessoa_atualiza_alias);
        e_celular = findViewById(R.id.et_pessoa_atualiza_telefone);
        sp_relacionamento = findViewById(R.id.sp_pessoa_atualiza_relacao);

        e_nome.setText(snome);
        e_alias.setText(salias);
        e_celular.setText(scelular);

        btdireito = findViewById(R.id.bts_direita);
        btesquerdo = findViewById(R.id.bts_esquerda);

        adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, relacoes);
        preenecherSpinner();

    }
    private void limpaCampos() {
        e_nome.setText("");
        e_nome.requestFocus();
        e_alias.setText("");
        e_celular.setText("");
        preenecherSpinner();
    }
    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                avisoAcaoAtualizar();
                break;
            case R.id.bts_esquerda:
                avisoAcaoDeletar();
                break;
        }
    }
    private void deletar(int id) {
        PessoaDao dao = new PessoaDao(ctx);
        dao.openDB();
        long resultado = dao.apagar(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, "Dados Deletados com Sucesso ");
        } else {
            msg.mensagenCurta(ctx, "ERRO: Dados não deletados ");
        }
        dao.close();
        finish();
    }
    private void atualizar(int id, String nome, String alias, String celular, String relacionamento) {
        PessoaDao db = new PessoaDao(ctx);
        db.openDB();
        long result = db.atualizar(id, nome, alias, celular, relacionamento);
        if (result > 0) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_dados_atualizados));
        } else {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_dados_atualizados));
        }
        db.close();
        finish();
    }
    private void avisoAcaoAtualizar() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(R.string.atencao);
        //define a mensagem
        builder.setMessage(R.string.msg_atencao_atualizar);
        //define um botão como positivo
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                atualizar(id, e_nome.getText().toString(), e_alias.getText().toString(), e_celular.getText().toString(), sp_relacionamento.getSelectedItem().toString());
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
                msg.mensagenCurta(ctx, String.valueOf(R.string.msg_acao_cancelada));
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
    private void avisoAcaoDeletar() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle(R.string.atencao);
        //define a mensagem
        builder.setMessage(R.string.msg_atencao_apagar);
        //define um botão como positivo
        builder.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                deletar(id);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
                msg.mensagenCurta(ctx, String.valueOf(R.string.msg_acao_cancelada));
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
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
        sp_relacionamento.setAdapter(adapterSpinner);
        sp_relacionamento.setPrompt(getResources().getString(R.string.escolhoa_opcao));
    }

    @Override
    protected void onResume() {
        preenecherSpinner();
        super.onResume();
    }

}
