package com.sda.botaonavergar.maq;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.addbontipo.AddBonus;
import com.sda.botaonavergar.addbontipo.AddBonusAtualizaActivity;
import com.sda.botaonavergar.addbontipo.AddBonusHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;

/**
 * made by sda
 */
public class MaquinaAdapter extends RecyclerView.Adapter<MaquinaHolder> {

    public Context ctx;
    public ArrayList<Maquina> maquinas;

    public MaquinaAdapter(Context ctx, ArrayList<Maquina> maquinas) {
        this.ctx = ctx;
        this.maquinas = maquinas;
    }


    @Override
    public MaquinaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quatro_modelo, null);
        MaquinaHolder holder = new MaquinaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MaquinaHolder holder, int position) {

        holder.grupo.setText(maquinas.get(position).getGrupo());
        holder.numero.setText("Nº " + maquinas.get(position).getNumero());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, MaquinaAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, maquinas.get(pos).getId());
                i.putExtra(Constantes.GRUPO, maquinas.get(pos).getGrupo());
                i.putExtra(Constantes.NUMERO, maquinas.get(pos).getNumero());

                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return maquinas.size();
    }

}