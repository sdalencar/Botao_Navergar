package com.sda.botaonavergar.chq;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;
import java.util.Calendar;

public class ChequeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText enome_chq, ebanco, enumero, evalor;
    private TextView edata_atual, edepositar;
    private Spinner spnome_cli;
    private Utilidades util;
    private Context ctx;
    private String dataRetorno;

    private ArrayList<String> pessoas = new ArrayList<>();
    private ArrayAdapter<String> adapterSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheque_activity);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        ctx = getApplicationContext();
        util = new Utilidades();

        enome_chq = findViewById(R.id.chq_primeiro_tv);
        spnome_cli = findViewById(R.id.chq_segundo_tv);
        ebanco = findViewById(R.id.chq_terceiro_tv);
        enumero = findViewById(R.id.chq_quarto_tv);
        evalor = findViewById(R.id.chq_quinto_tv);
        edata_atual = findViewById(R.id.chq_sexto_tv);
        edepositar = findViewById(R.id.chq_setimo_tv);

        edata_atual.setText(util.getData());

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pessoas);
        preenecherSpinner();

        edepositar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, ChequeListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (enome_chq.getText().toString().equals("") || spnome_cli.getSelectedItem().toString().equals("-") || ebanco.getText().toString().equals("") || enumero.getText().toString().equals("") || evalor.getText().toString().equals("") || edepositar.getText().toString().equals("Depositar")) {
            util.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salvar();
        }
    }

    private void salvar() {

        //salva na variael os valores do editTexts
        String snome_chq = enome_chq.getText().toString();
        String snome_cli = spnome_cli.getSelectedItem().toString();
        String sbanco = ebanco.getText().toString();
        String snumero = enumero.getText().toString();
        double svalor = Double.parseDouble(evalor.getText().toString());
        String sdata = edata_atual.getText().toString();
        String sdeposito = edepositar.getText().toString();
        //salva no banco os dados guardados nas variaveis
        ChequeDao dao = new ChequeDao(ctx);
        dao.openDB();
        long salvou = dao.adicionar(snome_chq, snome_cli, sbanco, snumero, svalor, sdata, sdeposito);
        if (salvou != 0) {
            util.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
            limpaCampos();
        } else {
            util.mensagenLonga(ctx, "ERRO Salvando os dados");
        }

    }

    private void limpaCampos() {
        enome_chq.setText("");
        enome_chq.requestFocus();
        ebanco.setText("");
        enumero.setText("");
        evalor.setText("");
        edata_atual.setText("");
        edepositar.setText("");

    }


    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this,
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
        spnome_cli.setAdapter(adapterSpinner);
        spnome_cli.setPrompt(getResources().getString(R.string.escolhoa_opcao));
    }

    @Override
    protected void onResume() {
        preenecherSpinner();
        super.onResume();
    }

}
