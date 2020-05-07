package com.sda.botaonavergar.gru;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Utilidades;

/**
 * made by sda
 */
public class GrupoAtualizaActivity extends AppCompatActivity {

    private EditText eproprietario, enome, evalor;

    private AlertDialog alerta;
    private Button btdireito, btesquerdo;
    private int id = 0;
    private String sporprietario, snome;
    private Double dvalor;
    private Context ctx;
    private Utilidades msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_edit_activity);

        ctx = this;
        msg = new Utilidades();
        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        sporprietario = i.getExtras().getString(Constantes.PROPRIETARIO);
        snome = i.getExtras().getString(Constantes.NOME);
        dvalor = i.getExtras().getDouble(Constantes.VALOR);


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

        eproprietario = findViewById(R.id.tres_edit_um_tv);
        enome = findViewById(R.id.tres_edit_dois_tv);
        evalor = findViewById(R.id.tres_edit_tres_tv);

        eproprietario.setText(sporprietario);
        enome.setText(snome);
        evalor.setText(String.valueOf(dvalor));

        btdireito = findViewById(R.id.bts_direita);
        btesquerdo = findViewById(R.id.bts_esquerda);


    }

    private void limpaCampos() {
        eproprietario.setText("");
        eproprietario.requestFocus();
        enome.setText("");
        evalor.setText("");
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
        GrupoDao dao = new GrupoDao(ctx);
        dao.openDB();
        long resultado = dao.apagar(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, "Dados Deletados com Sucesso ");
            limpaCampos();
        } else {
            msg.mensagenCurta(ctx, "ERRO: Dados não deletados ");
        }
        dao.close();
        finish();
    }

    private void atualizar(int id, String ppt, String nome, double valor) {
        GrupoDao db = new GrupoDao(ctx);
        db.openDB();
        long result = db.atualizar(id, ppt, nome, valor);
        if (result > 0) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_dados_atualizados));
            limpaCampos();
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
                atualizar(id, eproprietario.getText().toString(), enome.getText().toString(), Double.parseDouble(evalor.getText().toString()));
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
