package com.sda.botaonavergar.lei;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.empresa.EmpresaDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Utilidades;

public class LeituraAtualizaActivity extends AppCompatActivity {

    private EditText enumero, eentrada, esaida;
    private AlertDialog alerta;
    private Button btdireita, btesquerda;
    private int id = 0;
    private int inumero, ientrada, isaida;
    private Context ctx;
    private Utilidades msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresa_activity);

        ctx = this;
        msg = new Utilidades();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        inumero = i.getExtras().getInt(Constantes.NUMERO);
        ientrada = i.getExtras().getInt(Constantes.ENTRADA);
        isaida = i.getExtras().getInt(Constantes.SAIDA);


        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        iniciaComponentes();
    }

    private void iniciaComponentes() {

        enumero = findViewById(R.id.et_empresa_nome);
        enumero.setHint(getResources().getString(R.string.n_mero));
        enumero.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);

        eentrada = findViewById(R.id.et_empresa_telefone);
        eentrada.setHint(getResources().getString(R.string.entrada));
        eentrada.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);

        esaida = findViewById(R.id.et_empresa_endereco);
        esaida.setHint(getResources().getString(R.string.saida));
        esaida.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);

        enumero.setText(String.valueOf(inumero));
        esaida.setText(String.valueOf(ientrada));
        eentrada.setText(String.valueOf(isaida));

        btdireita = findViewById(R.id.bts_direita);
        btdireita.setText(getResources().getString(R.string.atualizar));
        btesquerda = findViewById(R.id.bts_esquerda);
        btesquerda.setText(getResources().getString(R.string.apagar));
    }


    private void deletar(int id) {
        LeituraDao dao = new LeituraDao(ctx);
        dao.openDB();
        long resultado = dao.apagar(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_deletar));
            //this.finish();
        } else {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_deletar));
        }
        dao.close();
        finish();
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

    private void atualizar(int id, int numero, int entrada, int saida) {
        LeituraDao db = new LeituraDao(ctx);
        db.openDB();
        long result = db.atualizar(id, numero, entrada, saida);
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
                atualizar(id, Integer.parseInt(enumero.getText().toString()), Integer.parseInt(esaida.getText().toString()), Integer.parseInt(eentrada.getText().toString()));
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
