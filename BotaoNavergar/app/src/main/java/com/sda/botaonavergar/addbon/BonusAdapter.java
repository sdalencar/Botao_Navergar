package com.sda.botaonavergar.addbon;

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
public class BonusAdapter extends RecyclerView.Adapter<BonusHolder> {

    public Context ctx;
    public ArrayList<Bonus> tiposBonus;

    public BonusAdapter(Context ctx, ArrayList<Bonus> tiposBonus) {
        this.ctx = ctx;
        this.tiposBonus = tiposBonus;
    }


    @Override
    public BonusHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bonus_modelo, null);
        BonusHolder holder = new BonusHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BonusHolder holder, int position) {
        holder.nome.setText(tiposBonus.get(position).getNome());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, BonusAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, tiposBonus.get(pos).getId());
                i.putExtra(Constantes.NOME, tiposBonus.get(pos).getNome());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return tiposBonus.size();
    }

}