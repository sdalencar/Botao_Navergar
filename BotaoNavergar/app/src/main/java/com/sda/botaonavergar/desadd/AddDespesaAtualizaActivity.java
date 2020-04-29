package com.sda.botaonavergar.desadd;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.cartao.CartaoDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Mensagem;

/**
 * made by sda
 */
public class AddDespesaAtualizaActivity extends AppCompatActivity {
    private EditText evalor;
    private TextView tiden, tdata, titulo;
    private Spinner sp_tipo;
    private AlertDialog alerta;
    private Button btdireita, btesquerda;
    private int id = 0;
    private String siden, sdata, stipo ;
    private double dvalor;
    private Context ctx;
    private Mensagem msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_um_atualiza_activity);
        ctx = this;
        msg = new Mensagem();
        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();
        id = i.getExtras().getInt(Constantes.ID);
        siden = i.getExtras().getString(Constantes.IDENTIFICADOR);
        sdata = i.getExtras().getString(Constantes.DATA_ATUAL);
        stipo = i.getExtras().getString(Constantes.TIPO);
        dvalor = i.getExtras().getDouble(Constantes.VALOR);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        tiden = findViewById(R.id.tres_um_atualiza_primeiro);
        tdata = findViewById(R.id.tres_um_atualiza_segundo);
        sp_tipo = findViewById(R.id.tres_um_atualiza_terceiro);
        evalor = findViewById(R.id.tres_um_atualiza_quarto);

        btdireita = findViewById(R.id.bts_direita);
        btesquerda = findViewById(R.id.bts_esquerda);
        TextView tv = findViewById(R.id.tres_um_atualiza_titulo);
        tv.setText(getResources().getString(R.string.despesas));

        tiden.setText(siden);
        tdata.setText(sdata);
        evalor.setText(String.valueOf(dvalor));
        btdireita.setText(getResources().getString(R.string.atualizar));
        btesquerda.setText(getResources().getString(R.string.apagar));

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
                avisoAcaoAtualizar();
                break;
            case R.id.bts_esquerda:
                avisoAcaoDeletar();
                break;
        }
    }
    private void deletar(int id) {
        AddDespesaDao dao = new AddDespesaDao(ctx);
        dao.openDB();
        long resultado = dao.apaga(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_deletar));
        } else {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_deletar));
        }
        dao.close();
        finish();
    }
    private void atualizar(int id, String iden, String data, String tipo, double valor) {
        AddDespesaDao db = new AddDespesaDao(ctx);
        db.openDB();
        long result = db.atualiza(id, iden, data, tipo, valor);
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
                atualizar(id, tiden.getText().toString(), tdata.getText().toString(), sp_tipo.getSelectedItem().toString(), Double.parseDouble(evalor.getText().toString()));
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
