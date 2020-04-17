package com.sda.botaonavergar.cargo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Mensagem;


/**
 * made by sda
 */
public class CargoActivity extends AppCompatActivity {

    private EditText enome;
    private Mensagem msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cargo_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome = findViewById(R.id.editText_cargo_nome);
        msg = new Mensagem();
        ctx = this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_cargo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onClickCargo(View view) {
        switch (view.getId()) {
            case R.id.button_cargo_grava:
                verificaCampos();
                break;
            case R.id.button_cargo_listar:
                startActivity(new Intent(ctx, CargoListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if(enome.getText().toString().equals("")){
             msg.mensagenCurta(ctx, getResources().getString(R.string.erro_campos_vazios));
        }else {
            salvarCargo();
        }
        limpaCampos();
    }

    private void salvarCargo() {
            //salva na variael os valores do editTexts
            String s_nome = enome.getText().toString();
            //salva no banco os dados guardados nas variaveis
            CargoDao dao = new CargoDao(ctx);
            dao.openDB();
            long salvou = dao.addCargo(s_nome);
            if (salvou != 0) {
                msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
            } else {
                msg.mensagenLonga(ctx, "ERRO Salvando os dados");
            }

    }

    private void limpaCampos(){
        enome.setText("");
        enome.requestFocus();
    }
}
