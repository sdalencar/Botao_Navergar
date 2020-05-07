package com.sda.botaonavergar.user;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;
import com.sda.botaonavergar.cargo.CargoDao;
import com.sda.botaonavergar.pes.PessoaDao;
import com.sda.botaonavergar.util.Utilidades;

import java.util.ArrayList;

/**
 * made by sda
 */
public class UserActivity extends AppCompatActivity {

    private Spinner sp_user,sp_nivel;
    private Utilidades msg;
    private Context ctx;

    private ArrayList<String> funcionarios = new ArrayList<>();
    private ArrayAdapter<String> adapterFuncSpinner;

    private ArrayList<String> cargos = new ArrayList<>();
    private ArrayAdapter<String> adapterCargosSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dois_spinner_activity);

        iniciaComponentes();

    }

    private void iniciaComponentes() {
        sp_user = findViewById(R.id.spn_primeiro_tv);
        sp_nivel = findViewById(R.id.spn_segundo_tv);

        TextView tv = findViewById(R.id.titulo);
        tv.setText(getResources().getString(R.string.usu_rio));
        tv.setTextSize(40);


        msg = new Utilidades();
        ctx = this;

        FloatingActionButton fab = findViewById(R.id.fab_buttons_rodape);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        adapterFuncSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, funcionarios);
        adapterCargosSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cargos);
        preencheSpiner();

    }

    public void onClickRodape(View view) {
        switch (view.getId()) {
            case R.id.bts_direita:
                verificaCampos();
                break;
            case R.id.bts_esquerda:
                startActivity(new Intent(ctx, UserListaActivity.class));
                break;
        }

    }

    private void verificaCampos() {
        if (sp_nivel.getSelectedItem().toString().equals("-") || sp_user.getSelectedItem().toString().equals("-")) {
            msg.mensagenCurta(ctx, getResources().getString(R.string.msg_erro_campos_vazios));
        } else {
            salvaTurno();
        }
    }

    private void salvaTurno() {
        //salva na variael os valores do editTexts
        String iuser = sp_user.getSelectedItem().toString();
        String inivel = sp_nivel.getSelectedItem().toString();
        //salva no banco os dados guardados nas variaveis
        UserDao dao = new UserDao(ctx);
        dao.openDB();
        long salvou = dao.adiciona(iuser, inivel);
        if (salvou != 0) {
            msg.mensagenLonga(ctx, "Dados Salvos com Sucesso.");
        } else {
            msg.mensagenLonga(ctx, "ERRO Salvando os dados");
        }

    }



    private void preencheSpiner() {
        PessoaDao dao = new PessoaDao(ctx);
        CargoDao cdao = new CargoDao(ctx);
        funcionarios.clear();
        cargos.clear();
        //abre bd
        dao.openDB();
        cdao.openDB();
        //cursor percorre metodo busca
        Cursor c = dao.buscarPorRelacionamento("FUNCIONÁRIO");
        while (c.moveToNext()) {
            String name = c.getString(1);
            funcionarios.add(name);
        }
        c = cdao.getAllCargo();
        while (c.moveToNext()) {
            String cargo = c.getString(1);
            cargos.add(cargo);
        }
        //fecha bd
        dao.close();
        //SET IT TO SPINNER
        sp_user.setAdapter(adapterFuncSpinner);
        sp_user.setPrompt(getResources().getString(R.string.escolhoa_opcao));

        sp_nivel.setAdapter(adapterCargosSpinner);
        sp_nivel.setPrompt(getResources().getString(R.string.escolhoa_opcao));
    }

}
