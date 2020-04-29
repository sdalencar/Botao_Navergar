package com.sda.botaonavergar.addbon;

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
public class BonusActivity extends AppCompatActivity {

    private EditText enome;
    private Mensagem msg;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bonus_activity);
        iniciaComponentes();
    }

    private void iniciaComponentes() {
        enome = findViewById(R.id.et_tipo_bonus_nome);
        msg = new Mensagem();
        ctx = this;

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
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, BonusListaActivity.class));
                break;
        }

    }
    private void verificaCampos() {
        if(enome.getText().toString().equals("")){
             msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        }else {
            salvarTipoBonus();
        }
        limpaCampos();
    }

    private void salvarTipoBonus() {
            //salva na variael os valores do editTexts
            String s_nome = enome.getText().toString();
            //salva no banco os dados guardados nas variaveis
            BonusDao dao = new BonusDao(ctx);
            dao.openDB();
            long salvou = dao.addTipoBonus(s_nome);
            if (salvou != 0) {
                msg.mensagenLonga(ctx, getResources().getString(R.string.msg_salvando_dados));
            } else {
                msg.mensagenLonga(ctx, getResources().getString(R.string.msg_erro_salvando_dados));
            }

    }

    private void limpaCampos(){
        enome.setText("");
        enome.requestFocus();
    }
}
