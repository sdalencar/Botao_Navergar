package com.sda.botaonavergar.valefeito;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.turno.TurnoDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Mensagem;

import java.util.ArrayList;

/**
 * made by sda
 */
public class ValeFeitoAtualizaActivity extends AppCompatActivity {

    private TextView tidentificador, titulo;
    private Spinner sp_nome, sp_relacao;
    private EditText evalor;
    private AlertDialog alerta;
    private Button btdireito, btesquerdo;
    private int id = 0;
    private String sidentificador, snome, srelacao;
    private Double dvalor;
    private Context ctx;
    private Mensagem msg;

    private ArrayList<String> relacoes = new ArrayList<>();
    private ArrayAdapter<String> adapterRelacaoSpinner;

    private ArrayList<String> nomes = new ArrayList<>();
    private ArrayAdapter<String> adapterNomeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_atualiza_activity);

        ctx = this;
        msg = new Mensagem();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();
        id = i.getExtras().getInt(Constantes.ID);
        sidentificador = i.getExtras().getString(Constantes.IDENTIFICADOR);
        snome = i.getExtras().getString(Constantes.NOME);
        srelacao = i.getExtras().getString(Constantes.RELACIONAMENTO);
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
        titulo = findViewById(R.id.textView_atualiza);
        titulo.setText(getResources().getString(R.string.add_vale_feito));
        tidentificador = findViewById(R.id.quatro_atualiza_primeiro_tv);
        sp_nome = findViewById(R.id.quatro_atualiza_segundo_sp);
        sp_relacao = findViewById(R.id.quatro_atualiza_terceiro_sp);
        evalor = findViewById(R.id.quatro_atualiza_quarto_et);

        tidentificador.setText(sidentificador);
        evalor.setText(String.valueOf(dvalor));

        btdireito = findViewById(R.id.bts_direita);
        btdireito.setText(getResources().getString(R.string.atualizar));
        btesquerdo = findViewById(R.id.bts_esquerda);
        btesquerdo.setText(getResources().getString(R.string.apagar));

        preencherSpinners();

        adapterNomeSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nomes);
        adapterRelacaoSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, relacoes);

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
        ValeFeitoDao dao = new ValeFeitoDao(ctx);
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

    private void atualizar(int id, String iden, String nome, String rel, double valor) {
        ValeFeitoDao db = new ValeFeitoDao(ctx);
        db.openDB();
        long result = db.atualiza(id, iden, nome, rel, valor);
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
                atualizar(id, tidentificador.getText().toString(), sp_nome.getSelectedItem().toString(), sp_relacao.getSelectedItem().toString(), Double.parseDouble(evalor.getText().toString()));
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
                msg.mensagenCurta(ctx, getResources().getString(R.string.msg_acao_cancelada));
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void preencherSpinners() {
        PessoaDao dao = new PessoaDao(ctx);
        nomes.clear();
        relacoes.clear();
        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscar();
        while (c.moveToNext()) {
            String nome = c.getString(1);
            String relacao = c.getString(4);
            nomes.add(nome);
            relacoes.add(relacao);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER
        sp_nome.setAdapter(adapterNomeSpinner);
        sp_nome.setPrompt(getResources().getString(R.string.escolhoa_opcao));

        sp_relacao.setAdapter(adapterRelacaoSpinner);
        sp_relacao.setPrompt(getResources().getString(R.string.escolhoa_opcao));

    }

    @Override
    protected void onResume() {
        preencherSpinners();
        super.onResume();
    }

}
