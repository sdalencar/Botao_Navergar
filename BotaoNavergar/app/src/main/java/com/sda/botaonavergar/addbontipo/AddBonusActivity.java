package com.sda.botaonavergar.addbontipo;


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
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.addbon.BonusDao;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class AddBonusActivity extends AppCompatActivity {
    private EditText evalor;
    private Spinner sp_nome, sp_tipo;
    private TextView identificador, titulo;
    private Mensagem msg;
    private Context ctx;

    private ArrayList<String> tipos = new ArrayList<>();
    private ArrayAdapter<String> adapterTipoSpinner;

    private ArrayList<String> nomes = new ArrayList<>();
    private ArrayAdapter<String> adapterNomeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_activity);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        titulo = findViewById(R.id.textView_quatro);
        titulo.setText(getResources().getString(R.string.add_bonus));
        titulo.setTextSize(40);
        identificador = findViewById(R.id.quatro_primeiro_tv);
        identificador.setTextSize(25);
        sp_nome = findViewById(R.id.quatro_segundo_sp);
        sp_tipo = findViewById(R.id.quatro_terceiro_sp);
        evalor = findViewById(R.id.quatro_quarto_et);

        identificador.setText("turno / data / caixa");

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

        adapterNomeSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
        adapterTipoSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tipos);

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, AddBonusListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (sp_nome.getSelectedItem().toString().equals("-") || sp_tipo.getSelectedItem().toString().equals("-") || evalor.getText().toString().equals("")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salva();
        }
        limpaCampos();
    }

    private void salva() {
        AddBonusDao dao = new AddBonusDao(ctx);
        dao.openDB();
        long salvou = dao.adiciona(identificador.getText().toString(), sp_nome.getSelectedItem().toString(), sp_tipo.getSelectedItem().toString(), Double.parseDouble(evalor.getText().toString()));
        if (salvou != 0) {
            msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
        } else {
            msg.mensagenLonga(ctx, "ERRO Salvando os dados");
        }

    }

    private void limpaCampos() {
        evalor.setText("");
        evalor.requestFocus();
        preencherSpinners();

    }

    private void preencherSpinners() {
        PessoaDao pdao = new PessoaDao(ctx);
        BonusDao tdao = new BonusDao(ctx);

        nomes.clear();
        tipos.clear();
        //abre bd
        pdao.openDB();
        tdao.openDB();
        //cursor percorre metodo busca
        Cursor c = pdao.buscar();
        while (c.moveToNext()) {
            String nome = c.getString(1);
            nomes.add(nome);
        }
        c = tdao.buscaTipoBonus();
        while (c.moveToNext()) {
            String tipo = c.getString(1);
            tipos.add(tipo);
        }

        //fecha bd
        pdao.close();
        tdao.close();

        //SET IT TO SPINNER
        sp_nome.setAdapter(adapterNomeSpinner);
        sp_nome.setPrompt(getResources().getString(R.string.escolhoa_opcao));

        sp_tipo.setAdapter(adapterTipoSpinner);
        sp_tipo.setPrompt(getResources().getString(R.string.escolhoa_opcao));

    }

    @Override
    protected void onResume() {
        preencherSpinners();
        super.onResume();
    }
}
