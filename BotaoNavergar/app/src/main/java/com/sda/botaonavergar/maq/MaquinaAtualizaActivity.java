package com.sda.botaonavergar.maq;

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
import com.sda.botaonavergar.addbon.BonusDao;
import com.sda.botaonavergar.addbontipo.AddBonusDao;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class MaquinaAtualizaActivity extends AppCompatActivity {

    private TextView titulo;
    private Spinner sp_grupo;
    private EditText evalor;
    private AlertDialog alerta;
    private Button btdireito, btesquerdo;
    private int id = 0;
    private String sgrupo;
    private int ivalor;
    private Context ctx;
    private Utilidades msg;

    private ArrayList<String> grupo = new ArrayList<>();
    private ArrayAdapter<String> adapterGrupoSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quatro_activity);

        ctx = this;
        msg = new Utilidades();

        //RECEIVE DATA FROM MAIN ACTIVITY
        Intent i = getIntent();
        id = i.getExtras().getInt(Constantes.ID);
        sgrupo = i.getExtras().getString(Constantes.GRUPO);
        ivalor = i.getExtras().getInt(Constantes.NUMERO);

        iniciaComponentes();

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        preencherSpinners();


    }

    private void iniciaComponentes() {
        titulo = findViewById(R.id.titulo);
        titulo.setText(getResources().getString(R.string.atualiza_maquina));
        titulo.setTextSize(40);

        sp_grupo = findViewById(R.id.quatro_segundo_sp);
        evalor = findViewById(R.id.quatro_quarto_et);
        evalor.setHint(getResources().getString(R.string.n_mero));
        evalor.setText(String.valueOf(ivalor));

        btdireito = findViewById(R.id.bts_direita);
        btdireito.setText(getResources().getString(R.string.atualizar));
        btesquerdo = findViewById(R.id.bts_esquerda);
        btesquerdo.setText(getResources().getString(R.string.apagar));

        adapterGrupoSpinner = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, grupo);

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
        MaquinaDao dao = new MaquinaDao(ctx);
        dao.openDB();
        long resultado = dao.apagar(id);
        if (resultado != 0) {
            msg.mensagenCurta(ctx, "Dados Deletados com Sucesso ");
            limpaCampos();
            finish();
        } else {
            msg.mensagenCurta(ctx, "ERRO: Dados não deletados ");
        }
        dao.close();

    }

    private void atualizar(int id, String grupo, int valor) {
        MaquinaDao db = new MaquinaDao(ctx);
        db.openDB();
        long result = db.atualiza(id, grupo, valor);
        if (result > 0) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_dados_atualizados));
            limpaCampos();
            finish();
        } else {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_dados_atualizados));
        }
        db.close();

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
                atualizar(id, sp_grupo.getSelectedItem().toString(), Integer.parseInt(evalor.getText().toString()));
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

    private void limpaCampos() {
        evalor.setText("");
        evalor.requestFocus();
        preencherSpinners();

    }

    private void preencherSpinners() {
        MaquinaDao dao = new MaquinaDao(ctx);
        grupo.clear();

        //abre bd
        dao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscar();
        while (c.moveToNext()) {
            String nome = c.getString(1);
            grupo.add(nome);
        }
        //fecha bd
        dao.close();

        //SET IT TO SPINNER
        sp_grupo.setAdapter(adapterGrupoSpinner);
        sp_grupo.setPrompt(getResources().getString(R.string.escolhoa_opcao));

    }

    @Override
    protected void onResume() {
        preencherSpinners();
        super.onResume();
    }

}
