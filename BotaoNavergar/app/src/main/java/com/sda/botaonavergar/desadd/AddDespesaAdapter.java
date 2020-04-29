package com.sda.botaonavergar.desadd;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;


/**
 * made by sda
 */
public class AddDespesaAdapter extends RecyclerView.Adapter<AddDespesaHolder> {

    public Context ctx;
    public ArrayList<AddDespesa> despesas;

    public AddDespesaAdapter(Context ctx, ArrayList<AddDespesa> despesas) {
        this.ctx = ctx;
        this.despesas = despesas;
    }


    @Override
    public AddDespesaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tres_um_modelo, null);
        AddDespesaHolder holder = new AddDespesaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AddDespesaHolder holder, int position) {
        holder.iden.setText(despesas.get(position).getIdentificador());
        holder.data.setText(despesas.get(position).getData());
        holder.tipo.setText(despesas.get(position).getNome());
        holder.valor.setText(String.valueOf(despesas.get(position).getValor()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, AddDespesaAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, despesas.get(pos).getId());
                i.putExtra(Constantes.IDENTIFICADOR, despesas.get(pos).getIdentificador());
                i.putExtra(Constantes.DATA_ATUAL, despesas.get(pos).getData());
                i.putExtra(Constantes.NOME, despesas.get(pos).getNome());
                i.putExtra(Constantes.VALOR, despesas.get(pos).getValor());
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