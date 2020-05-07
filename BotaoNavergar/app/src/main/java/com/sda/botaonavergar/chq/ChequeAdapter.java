package com.sda.botaonavergar.chq;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.des.Despesa;
import com.sda.botaonavergar.des.DespesaAtualizaActivity;
import com.sda.botaonavergar.des.DespesaHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;


/**
 * made by sda
 */
public class ChequeAdapter extends RecyclerView.Adapter<ChequeHolder> {

    public Context ctx;
    public ArrayList<Cheque> cheques;

    public ChequeAdapter(Context ctx, ArrayList<Cheque> cheques) {
        this.ctx = ctx;
        this.cheques = cheques;
    }


    @Override
    public ChequeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quatro_modelo, null);
        ChequeHolder holder = new ChequeHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ChequeHolder holder, int position) {
        holder.nome.setText(cheques.get(position).getNome_chq());
        holder.valor.setText("R$ " + cheques.get(position).getValor());
        holder.data.setText("Data: " + cheques.get(position).getData_atual());
        holder.deposito.setText("Depositar: " + cheques.get(position).getDepositar());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, ChequeAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, cheques.get(pos).getId());
                i.putExtra(Constantes.NOME_CHQ, cheques.get(pos).getNome_chq());
                i.putExtra(Constantes.NOME_CLI, cheques.get(pos).getNome_cli());
                i.putExtra(Constantes.BANCO, cheques.get(pos).getBanco());
                i.putExtra(Constantes.NUMERO, cheques.get(pos).getNumero());
                i.putExtra(Constantes.VALOR, cheques.get(pos).getValor());
                i.putExtra(Constantes.DATA_ATUAL, cheques.get(pos).getData_atual());
                i.putExtra(Constantes.DATA_DEPOSITO, cheques.get(pos).getDepositar());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cheques.size();
    }

}