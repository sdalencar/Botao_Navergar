package com.sda.botaonavergar.out;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.rel.RelacionamentoDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Utilidades;

/**
 * made by sda
 */
public class OutroAtualizaActivity extends AppCompatActivity {

    private EditText enome, evalor;
    private AlertDialog alerta;
    private Button btdireito, btesquerdo;
    private int id = 0;
    private String snome, svalor;
    private Context ctx;
    private Utilidades msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dois_atualiza_layout);

        ctx = this;
        msg = new Utilidades();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        snome = i.getExtras().getString(Constantes.NOME);
        svalor = i.getExtras().getString(Constantes.VALOR);

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

        enome = findViewById(R.id.dois_atualiza_dois_tv);
        evalor = findViewById(R.id.dois_atualiza_um_tv);

        TextView tv = findViewById(R.id.um_titulo);
        tv.setText(getResources().getString(R.string.atualiza_outro));
        tv.setTextSize(40);

        enome.setText(snome);
        evalor.setText(svalor);

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
        OutroDao dao = new OutroDao(ctx);
        dao.openDB();
        long resultado = dao.apaga(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, "Dados Deletados com Sucesso ");
        } else {
            msg.mensagenCurta(ctx, "ERRO: Dados não deletados ");
        }
        dao.close();
        finish();
    }

    private void atualizar(int id, String nome, double valor) {
        OutroDao db = new OutroDao(ctx);
        db.openDB();
        long result = db.atualiza(id, nome, valor);
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
                atualizar(id, enome.getText().toString(), Double.parseDouble(evalor.getText().toString()));
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
                msg.mensagenCurta(ctx, getResources().getString(R.string.msg_acao_cancelada));
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
                msg.mensagenCurta(ctx, getResources().getString(R.string.msg_acao_cancelada));
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }


}
