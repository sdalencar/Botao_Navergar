package com.sda.botaonavergar.lei;

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

public class LeituraAdapter extends RecyclerView.Adapter<LeituraHolder> {

    public Context ctx;
    public ArrayList<Leitura> leituras;

    public LeituraAdapter(Context ctx, ArrayList<Leitura> leituras) {
        this.ctx = ctx;
        this.leituras = leituras;
    }


    @Override
    public LeituraHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empresa_modelo, null);
        LeituraHolder holder = new LeituraHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(LeituraHolder holder, int position) {
        holder.numero.setText("Máquina Nº: " + leituras.get(position).getNumero());
        holder.entrada.setText("Entrada: " + leituras.get(position).getEntrada());
        holder.saida.setText("Saída: " + leituras.get(position).getSaida());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, LeituraAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, leituras.get(pos).getId());
                i.putExtra(Constantes.NUMERO, leituras.get(pos).getNumero());
                i.putExtra(Constantes.ENTRADA, leituras.get(pos).getEntrada());
                i.putExtra(Constantes.SAIDA, leituras.get(pos).getSaida());

                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return leituras.size();
    }

}