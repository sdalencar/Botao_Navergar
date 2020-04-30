package com.sda.botaonavergar.out;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.rel.Relacionamento;
import com.sda.botaonavergar.rel.RelacionamentoAtualizaActivity;
import com.sda.botaonavergar.rel.RelacionamentoHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;


/**
 * made by sda
 */
public class OutroAdapter extends RecyclerView.Adapter<OutroHolder> {

    public Context ctx;
    public ArrayList<Outro> outros;

    public OutroAdapter(Context ctx, ArrayList<Outro> outros) {
        this.ctx = ctx;
        this.outros = outros;
    }


    @Override
    public OutroHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dois_modelo, null);
        OutroHolder holder = new OutroHolder(v);
        return holder;
    }



    @Override
    public void onBindViewHolder(OutroHolder holder, int position) {
        holder.nome.setText(outros.get(position).getOutro());
        holder.valor.setText(String.valueOf(outros.get(position).getValor()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, OutroAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, outros.get(pos).getId());
                i.putExtra(Constantes.NOME, outros.get(pos).getOutro());
                i.putExtra(Constantes.VALOR, outros.get(pos).getValor());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return outros.size();
    }

}