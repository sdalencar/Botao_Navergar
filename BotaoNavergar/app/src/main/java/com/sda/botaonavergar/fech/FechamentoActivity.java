package com.sda.botaonavergar.fech;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sda.botaonavergar.R;

public class FechamentoActivity extends AppCompatActivity {
    private TextView tuser, tdata, tturno, tentrada, tsaida, tsangria, tpagamentoManual, tpgtFunc, tdespesas, toutros, tvaleFeito, tvalePago, tbonus, tcartao, tcheque, tdinheiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fechamento_activity);

        iniciaComponentes();
    }

    private void iniciaComponentes() {
        tuser = findViewById(R.id.fc_usu_rio);
        tdata = findViewById(R.id.fc_data);
        tturno = findViewById(R.id.fc_turno);
        tentrada = findViewById(R.id.fc_entrada);
        tsaida = findViewById(R.id.fc_saida);
        tsangria = findViewById(R.id.fc_sangria);
        tpagamentoManual = findViewById(R.id.fc_pr_mios);
        tpgtFunc = findViewById(R.id.fc_salario);
        tdespesas = findViewById(R.id.fc_despesas);
        toutros = findViewById(R.id.fc_outros);
        tvaleFeito = findViewById(R.id.fc_v_feito);
        tvalePago = findViewById(R.id.fc_v_pago);
        tbonus = findViewById(R.id.fc_b_nus);
        tcartao = findViewById(R.id.fc_cartoes);
        tcheque = findViewById(R.id.fc_cheque);
        tdinheiro = findViewById(R.id.fc_dinheiro);

        FloatingActionButton fab = findViewById(R.id.fab_rodape_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpaCampos();
                finish();
            }
        });

    }

    private void limpaCampos(){
        tuser.setText("");
        tuser.requestFocus();
        tdata.setText("");
        tturno.setText("");
        tentrada.setText("");
        tsaida.setText("");
        tsangria.setText("");
        tpagamentoManual.setText("");
        tpgtFunc.setText("");
        tdespesas.setText("");
        toutros.setText("");
        tvaleFeito.setText("");
        tvalePago.setText("");
        tbonus.setText("");
        tcartao.setText("");
        tcheque.setText("");
        tdinheiro.setText("");
    }

}
