package com.sda.botaonavergar.addbon;

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
import com.sda.botaonavergar.util.Mensagem;

/**
 * made by sda
 */
public class BonusAtualizaActivity extends AppCompatActivity {

    private EditText txtnome;
    private AlertDialog alerta;
    private Button btdireito, btesquerdo;
    private int id = 0;
    private String snome = "";
    private Context ctx;
    private Mensagem msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bonus_atualiza_activity);

        ctx = this;
        msg = new Mensagem();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        snome = i.getExtras().getString(Constantes.NOME);
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

        txtnome = findViewById(R.id.et_tipo_bonus_atualiza_nome);
        txtnome.setText(snome);

        btdireito = findViewById(R.id.bts_direita);
        btesquerdo = findViewById(R.id.bts_esquerda);
        btdireito.setText(getResources().getString(R.string.atualizar));
        btesquerdo.setText(getResources().getString(R.string.apagar));

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
        BonusDao dao = new BonusDao(ctx);
        dao.openDB();
        long resultado = dao.deletaTipoBonus(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_deletar));
        } else {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_deletar));
        }
        dao.close();
        finish();
    }

    private void atualizar(int id, String nome) {
        BonusDao db = new BonusDao(ctx);
        db.openDB();
        long result = db.updateTipoBonus(id, nome);
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
                atualizar(id, txtnome.getText().toString());
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