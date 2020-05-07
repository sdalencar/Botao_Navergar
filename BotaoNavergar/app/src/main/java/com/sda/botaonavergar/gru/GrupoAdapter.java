package com.sda.botaonavergar.gru;

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
public class GrupoAdapter extends RecyclerView.Adapter<GrupoHolder> {

    public Context ctx;
    public ArrayList<Grupo> grupos;

    public GrupoAdapter(Context ctx, ArrayList<Grupo> grupos) {
        this.ctx = ctx;
        this.grupos = grupos;
    }


    @Override
    public GrupoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tres_edit_modelo, null);
        GrupoHolder holder = new GrupoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(GrupoHolder holder, int position) {
        holder.ppt.setText(grupos.get(position).getProprietario());
        holder.nome.setText(grupos.get(position).getNome());
        holder.valor.setText("R$ " + grupos.get(position).getValor());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, GrupoAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, grupos.get(pos).getId());
                i.putExtra(Constantes.PROPRIETARIO, grupos.get(pos).getProprietario());
                i.putExtra(Constantes.NOME, grupos.get(pos).getNome());
                i.putExtra(Constantes.VALOR, grupos.get(pos).getValor());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

}