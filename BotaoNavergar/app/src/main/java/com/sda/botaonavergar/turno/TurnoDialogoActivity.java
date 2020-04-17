package com.sda.botaonavergar.turno;

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
public class TurnoDialogoActivity extends AppCompatActivity {

    private EditText txtnome, txtnumero;
    private AlertDialog alerta;
    private Button btAtualizar, btDeletar, btVoltar;
    private int id =  0;
    private String snome = "", snumero = "";
    private Context ctx;
    private Mensagem msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turno_dialogo_layout);

        ctx = this;
        msg = new Mensagem();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        snumero = i.getExtras().getString(Constantes.NUMERO);
        snome = i.getExtras().getString(Constantes.NOME);
        exibirDialogo();

        FloatingActionButton fab = findViewById(R.id.fab_turno_dialogo);
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
        d.setContentView(R.layout.turno_dialogo);
        txtnumero = d.findViewById(R.id.dia_turno_numero);
        txtnome = d.findViewById(R.id.dia_turno_nome);

        txtnumero.setText(snumero);
        txtnome.setText(snome);

        btAtualizar = d.findViewById(R.id.dia_turno_btatualizar);
        btDeletar = d.findViewById(R.id.dia_turno_btdeletar);
        btVoltar = d.findViewById(R.id.dia_turno_btvoltar);


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
        TurnoDao dao = new TurnoDao(ctx);
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

    private void atualizar(int id, int numero, String nome) {
        TurnoDao db = new TurnoDao(ctx);
        db.openDB();
        long result = db.atualiza(id, numero, nome);
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
                atualizar(id, Integer.parseInt(txtnumero.getText().toString()), txtnome.getText().toString());
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
