package com.sda.botaonavergar.pes;

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
public class PessoaAdapter extends RecyclerView.Adapter<PessoaHolder> {

    public Context ctx;
    public ArrayList<Pessoa> pessoas;

    public PessoaAdapter(Context ctx, ArrayList<Pessoa> pessoas) {
        this.ctx = ctx;
        this.pessoas = pessoas;
    }


    @Override
    public PessoaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pessoa_modelo, null);
        PessoaHolder holder = new PessoaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(PessoaHolder holder, int position) {
        holder.nome.setText(pessoas.get(position).getNome());
        holder.alias.setText(pessoas.get(position).getAlias());
        holder.celular.setText(pessoas.get(position).getCelular());
        holder.relacionamento.setText(pessoas.get(position).getRelacionamento());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, PessoaDialogoActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, pessoas.get(pos).getId());
                i.putExtra(Constantes.NOME, pessoas.get(pos).getNome());
                i.putExtra(Constantes.ALIAS, pessoas.get(pos).getAlias());
                i.putExtra(Constantes.CELULAR, pessoas.get(pos).getCelular());
                i.putExtra(Constantes.RELACIONAMENTO, pessoas.get(pos).getRelacionamento());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pessoas.size();
    }

}