package com.sda.botaonavergar.chq;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;

public class ChequeAtualizaActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText enome_chq, ebanco, enumero, evalor;
    private TextView edata_atual, edepositar;
    private Spinner sp_nome_cli;
    private Utilidades util;
    private AlertDialog alerta;
    private Context ctx;
    private int id;
    private String snome_chq, snome_cli, sbanco, snumero, svalor, sdata_atual, sdeposito;
    private String dataRetorno;

    private ArrayList<String> pessoas = new ArrayList<>();
    private ArrayAdapter<String> adapterSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheque_activity);

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();

        id = i.getExtras().getInt(Constantes.ID);
        snome_chq = i.getExtras().getString(Constantes.NOME_CHQ);
        snome_cli = i.getExtras().getString(Constantes.NOME_CLI);
        sbanco = i.getExtras().getString(Constantes.BANCO);
        snumero = i.getExtras().getString(Constantes.NUMERO);
        svalor = i.getExtras().getString(Constantes.VALOR);
        sdata_atual = i.getExtras().getString(Constantes.DATA_ATUAL);
        sdeposito = i.getExtras().getString(Constantes.DATA_DEPOSITO);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome_chq = findViewById(R.id.chq_primeiro_tv);
        enome_chq.setText(snome_chq);

        sp_nome_cli = findViewById(R.id.chq_segundo_tv);

        ebanco = findViewById(R.id.chq_terceiro_tv);
        ebanco.setText(sbanco);

        enumero = findViewById(R.id.chq_quarto_tv);
        enumero.setText(snumero);

        evalor = findViewById(R.id.chq_quinto_tv);
        evalor.setText(svalor);

        edata_atual = findViewById(R.id.chq_sexto_tv);
        edata_atual.setText(sdata_atual);

        edepositar = findViewById(R.id.chq_setimo_tv);
        edepositar.setText(sdeposito);

        TextView tv = findViewById(R.id.titulo);
        tv.setText(getResources().getString(R.string.atualizar_cheques));
        tv.setTextSize(40);

        Button direito = findViewById(R.id.bts_direita);
        direito.setText(getResources().getString(R.string.atualizar));
        Button esquerdo = findViewById(R.id.bts_esquerda);
        esquerdo.setText(getResources().getString(R.string.apagar));

        ctx = getApplicationContext();
        util = new Utilidades();

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        edepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
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
        ChequeDao dao = new ChequeDao(ctx);
        dao.openDB();
        long resultado = dao.apaga(id);
        if (resultado != 0) {
            util.mensagenCurta(ctx, getResources().getString(R.string.msg_deletar));
        } else {
            util.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_deletar));
        }
        dao.close();
        finish();
    }

    private void atualizar(int id, String nome_chq, String nome_cli, String banco, String numero, double valor, String data, String depositar) {
        ChequeDao db = new ChequeDao(ctx);
        db.openDB();
        long result = db.atualiza(id, nome_chq, nome_cli, banco, numero, valor, data, depositar);
        if (result > 0) {
            util.mensagenCurta(ctx, getResources().getString(R.string.msg_dados_atualizados));
        } else {
            util.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_dados_atualizados));
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
                atualizar(id, enome_chq.getText().toString(), sp_nome_cli.getSelectedItem().toString(), ebanco.getText().toString(), enumero.getText().toString(), Double.parseDouble(evalor.getText().toString()), edata_atual.getText().toString(), edepositar.getText().toString());
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
                util.mensagenCurta(ctx, String.valueOf(R.string.msg_acao_cancelada));
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
                util.mensagenCurta(ctx, getResources().getString(R.string.msg_acao_cancelada));
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int ano, int mes, int dia) {
        dataRetorno = dia + " / " + (mes + 1) + " / " + ano;
        edepositar.setText(dataRetorno);
    }

    private void preenecherSpinner() {
        PessoaDao dao = new PessoaDao(ctx);
        pessoas.clear();
        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscar();
        while (c.moveToNext()) {
            String name = c.getString(1);
            pessoas.add(name);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER
        sp_nome_cli.setAdapter(adapterSpinner);
        sp_nome_cli.setPrompt(getResources().getString(R.string.escolhoa_opcao));
    }

    @Override
    protected void onResume() {
        preenecherSpinner();
        super.onResume();
    }

}
