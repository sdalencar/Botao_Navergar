package com.sda.botaonavergar.cartoes;


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
public class CartoesAdapter extends RecyclerView.Adapter<CartoesHolder> {

    public Context ctx;
    public ArrayList<Cartoes> cartoes;

    public CartoesAdapter(Context ctx, ArrayList<Cartoes> cartoes) {
        this.ctx = ctx;
        this.cartoes = cartoes;
    }


    @Override
    public CartoesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tres_modelo, null);
        CartoesHolder holder = new CartoesHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartoesHolder holder, int position) {
        holder.doc.setText(cartoes.get(position).getDoc());
        holder.tipo.setText(cartoes.get(position).getTipo());
        holder.valor.setText(String.valueOf(cartoes.get(position).getValor()));

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, CartoesAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, cartoes.get(pos).getId());
                i.putExtra(Constantes.IDENTIFICADOR, cartoes.get(pos).getIdentificador());
                i.putExtra(Constantes.DOC, cartoes.get(pos).getDoc());
                i.putExtra(Constantes.TIPO, cartoes.get(pos).getTipo());
                i.putExtra(Constantes.VALOR, cartoes.get(pos).getValor());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cartoes.size();
    }

}