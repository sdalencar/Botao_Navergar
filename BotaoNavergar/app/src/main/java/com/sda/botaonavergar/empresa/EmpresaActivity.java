package com.sda.botaonavergar.empresa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.act.MainActivity;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Mensagem;

public class EmpresaActivity extends AppCompatActivity {

    private EditText enome, eendereco, etelefone;
    private Mensagem msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresa_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome = findViewById(R.id.et_empresa_nome);
        eendereco = findViewById(R.id.et_empresa_endereco);
        etelefone = findViewById(R.id.et_empresa_telefone);
        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_buttons_rodape);
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
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, EmpresaListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if(enome.getText().toString().equals("") ||eendereco.getText().toString().equals("")||etelefone.getText().toString().equals("")){
             msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        }else {
            salvarEmpresa();
        }
        limpaCampos();
    }

    private void salvarEmpresa() {
            //salva na variael os valores do editTexts
            String s_nome = enome.getText().toString();
            String s_endereco = eendereco.getText().toString();
            String s_telefone = etelefone.getText().toString();
            //salva no banco os dados guardados nas variaveis
            EmpresaDao dao = new EmpresaDao(ctx);
            dao.openDB();
            long salvou = dao.addEmpresa(s_nome, s_endereco, s_telefone);
            if (salvou != 0) {
                msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
            } else {
                msg.mensagenLonga(ctx, "ERRO Salvando os dados");
            }

    }

    private void limpaCampos(){
        enome.setText("");
        enome.requestFocus();
        eendereco.setText("");
        etelefone.setText("");
    }
}
