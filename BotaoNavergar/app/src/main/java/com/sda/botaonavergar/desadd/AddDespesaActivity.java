package com.sda.botaonavergar.desadd;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.addbon.BonusDao;
import com.sda.botaonavergar.cartao.CartaoDao;
import com.sda.botaonavergar.cartao.CartaoListaActivity;
import com.sda.botaonavergar.des.Despesa;
import com.sda.botaonavergar.des.DespesaDao;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;


/**
 * made by sda
 */
public class AddDespesaActivity extends AppCompatActivity {

    private EditText evalor;
    private TextView tiden, tdata;
    private Spinner sp_tipo;
    private Mensagem msg;
    private Context ctx;
    private ArrayList<String> tipos = new ArrayList<>();
    private ArrayAdapter<String> adapterTipoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tres_um_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        tiden = findViewById(R.id.tres_um_primeiro_tv);
        tdata = findViewById(R.id.tres_um_segundo_tv);
        sp_tipo = findViewById(R.id.tres_um_terceiro_sp);
        evalor = findViewById(R.id.tres_um_quarto_et);
        TextView tv = findViewById(R.id.tres_um_titulo);
        tv.setText(getResources().getString(R.string.despesas));
        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        preencherSpinners();

        adapterTipoSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipos);

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, CartaoListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (sp_tipo.getSelectedItem().toString().equals("-") || evalor.getText().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salvar();
        }
        limpaCampos();
    }

    private void salvar() {
        //salva na variael os valores do editTexts
        String siden = tiden.getText().toString();
        String sdata = tdata.getText().toString();
        String stipo = sp_tipo.getSelectedItem().toString();
        double dvlr = Double.parseDouble(evalor.getText().toString());
        //salva no banco os dados guardados nas variaveis
        AddDespesaDao dao = new AddDespesaDao(ctx);
        dao.openDB();
        long salvou = dao.adicionar(siden,sdata,stipo,dvlr);
        if (salvou != 0) {
            msg.mensagenLonga(ctx, getResources().getString(R.string.msg_salvando_dados));
        } else {
            msg.mensagenLonga(ctx, getResources().getString(R.string.msg_erro_salvando_dados));
        }

    }

    private void limpaCampos() {
        evalor.setText("");
        evalor.requestFocus();
        preencherSpinners();

    }

    private void preencherSpinners() {
        DespesaDao dao = new DespesaDao(ctx);
        tipos.clear();
        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscar();
        while (c.moveToNext()) {
            String nome = c.getString(1);
            tipos.add(nome);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER
        sp_tipo.setAdapter(adapterTipoSpinner);
        sp_tipo.setPrompt(getResources().getString(R.string.escolhoa_opcao));

    }

    @Override
    protected void onResume() {
        preencherSpinners();
        super.onResume();
    }
}
