package com.sda.botaonavergar.cartao;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.cargo.Cargo;
import com.sda.botaonavergar.cargo.CargoAtualizaActivity;
import com.sda.botaonavergar.cargo.CargoHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;


/**
 * made by sda
 */
public class CartaoAdapter extends RecyclerView.Adapter<CartaoHolder> {

    public Context ctx;
    public ArrayList<Cartao> cartoes;

    public CartaoAdapter(Context ctx, ArrayList<Cartao> cartoes) {
        this.ctx = ctx;
        this.cartoes = cartoes;
    }


    @Override
    public CartaoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.um_edittext_modelo, null);
        CartaoHolder holder = new CartaoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CartaoHolder holder, int position) {
        holder.nome.setText(cartoes.get(position).getNome());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, CartaoAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, cartoes.get(pos).getId());
                i.putExtra(Constantes.NOME, cartoes.get(pos).getNome());
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