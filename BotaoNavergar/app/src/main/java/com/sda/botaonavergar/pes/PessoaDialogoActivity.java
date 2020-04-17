package com.sda.botaonavergar.pes;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Mensagem;

/**
 * made by sda
 */
public class PessoaDialogoActivity extends AppCompatActivity {

    private EditText e_nome, e_alias, e_celular, e_relecionamento;
    private AlertDialog alerta;
    private Button btAtualizar, btDeletar, btVoltar;
    private int id = 0;
    private String snome = "", salias = "", scelular = "", srelacionamento = "";
    private Context ctx;
    private Mensagem msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pessoa_dialogo_layout);

        ctx = this;
        msg = new Mensagem();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        snome = i.getExtras().getString(Constantes.NOME);
        salias = i.getExtras().getString(Constantes.ALIAS);
        scelular = i.getExtras().getString(Constantes.CELULAR);
        srelacionamento = i.getExtras().getString(Constantes.RELACIONAMENTO);
        exibirDialogo();

        FloatingActionButton fab = findViewById(R.id.fab_pessoa_dialogo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void exibirDialogo() {
        final Dialog d = new Dialog(this);

        //NO TITLE
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //layout of dialog
        d.setContentView(R.layout.pessoa_dialogo);
        e_nome = d.findViewById(R.id.dia_pes_nome);
        e_alias = d.findViewById(R.id.dia_pes_alias);
        e_celular = d.findViewById(R.id.dia_pes_celular);
        e_relecionamento = d.findViewById(R.id.dia_pes_relacionamento);

        e_nome.setText(snome);
        e_alias.setText(salias);
        e_celular.setText(scelular);
        e_relecionamento.setText(srelacionamento);

        btAtualizar = d.findViewById(R.id.dia_pes_btatualizar);
        btDeletar = d.findViewById(R.id.dia_pes_btdeletar);
        btVoltar = d.findViewById(R.id.dia_pes_btvoltar);


        //ONCLICK LISTENERS
        btAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avisoAcaoAtualizar();
            }
        });

        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avisoAcaoDeletar();
            }
        });

        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //SHOW DIALOG
        d.show();
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
            msg.mensagenCurta(ctx, getResources().getString(R.string.dados_atualizados));
        } else {
            msg.mensagenCurta(ctx, getResources().getString(R.string.erro_dados_atualizados));
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
                atualizar(id, e_nome.getText().toString(), e_alias.getText().toString(), e_celular.getText().toString(), e_relecionamento.getText().toString());
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


}
