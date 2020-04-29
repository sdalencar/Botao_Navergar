package com.sda.botaonavergar.des;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.cartao.Cartao;
import com.sda.botaonavergar.cartao.CartaoAtualizaActivity;
import com.sda.botaonavergar.cartao.CartaoHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;


/**
 * made by sda
 */
public class DespesaAdapter extends RecyclerView.Adapter<DespesaHolder> {

    public Context ctx;
    public ArrayList<Despesa> despesas;

    public DespesaAdapter(Context ctx, ArrayList<Despesa> despesas) {
        this.ctx = ctx;
        this.despesas = despesas;
    }


    @Override
    public DespesaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.um_edittext_modelo, null);
        DespesaHolder holder = new DespesaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DespesaHolder holder, int position) {
        holder.tipo.setText(despesas.get(position).getNome());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, DespesaAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, despesas.get(pos).getId());
                i.putExtra(Constantes.NOME, despesas.get(pos).getNome());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return despesas.size();
    }

}