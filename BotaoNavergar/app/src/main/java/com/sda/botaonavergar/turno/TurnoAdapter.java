package com.sda.botaonavergar.turno;


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
public class TurnoAdapter extends RecyclerView.Adapter<TurnoHolder> {

    public Context ctx;
    public ArrayList<Turno> turnos;

    public TurnoAdapter(Context ctx, ArrayList<Turno> turnos) {
        this.ctx = ctx;
        this.turnos = turnos;
    }


    @Override
    public TurnoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.turno_modelo, null);
        TurnoHolder holder = new TurnoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(TurnoHolder holder, int position) {
        holder.txtnumero.setText(turnos.get(position).getNumero());
        holder.txtnome.setText(turnos.get(position).getNome());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, TurnoAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, turnos.get(pos).getId());
                i.putExtra(Constantes.NUMERO, turnos.get(pos).getNumero());
                i.putExtra(Constantes.NOME, turnos.get(pos).getNome());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }

}