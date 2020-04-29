package com.sda.botaonavergar.valepago;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.turno.ValeFeitoHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;
import com.sda.botaonavergar.valefeito.ValeFeito;
import com.sda.botaonavergar.valefeito.ValeFeitoAtualizaActivity;

import java.util.ArrayList;

/**
 * made by sda
 */
public class ValePagoAdapter extends RecyclerView.Adapter<ValePagoHolder> {

    public Context ctx;
    public ArrayList<ValePago> vales;

    public ValePagoAdapter(Context ctx, ArrayList<ValePago> vales) {
        this.ctx = ctx;
        this.vales = vales;
    }


    @Override
    public ValePagoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.quatro_modelo, null);
        ValePagoHolder holder = new ValePagoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ValePagoHolder holder, int position) {
        holder.indentificador.setText(vales.get(position).getIdentificador());
        holder.nome.setText(vales.get(position).getNome());
        holder.relacao.setText(vales.get(position).getRelaco());
        holder.valor.setText(String.valueOf(vales.get(position).getValor()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, ValePagoAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, vales.get(pos).getId());
                i.putExtra(Constantes.IDENTIFICADOR, vales.get(pos).getIdentificador());
                i.putExtra(Constantes.NOME, vales.get(pos).getNome());
                i.putExtra(Constantes.RELACIONAMENTO, vales.get(pos).getRelaco());
                i.putExtra(Constantes.VALOR, vales.get(pos).getValor());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return vales.size();
    }

}