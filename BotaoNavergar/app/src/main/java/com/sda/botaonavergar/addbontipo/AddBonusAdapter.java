package com.sda.botaonavergar.addbontipo;


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
public class AddBonusAdapter extends RecyclerView.Adapter<AddBonusHolder> {

    public Context ctx;
    public ArrayList<AddBonus> bonus;

    public AddBonusAdapter(Context ctx, ArrayList<AddBonus> bonus) {
        this.ctx = ctx;
        this.bonus = bonus;
    }


    @Override
    public AddBonusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quatro_modelo, null);
        AddBonusHolder holder = new AddBonusHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(AddBonusHolder holder, int position) {
        holder.indentificador.setText(bonus.get(position).getIdentificador());
        holder.nome.setText(bonus.get(position).getNome());
        holder.relacao.setText(bonus.get(position).getTipo());
        holder.valor.setText(String.valueOf(bonus.get(position).getValor()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, AddBonusAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, bonus.get(pos).getId());
                i.putExtra(Constantes.IDENTIFICADOR, bonus.get(pos).getIdentificador());
                i.putExtra(Constantes.NOME, bonus.get(pos).getNome());
                i.putExtra(Constantes.RELACIONAMENTO, bonus.get(pos).getTipo());
                i.putExtra(Constantes.VALOR, bonus.get(pos).getValor());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return bonus.size();
    }

}