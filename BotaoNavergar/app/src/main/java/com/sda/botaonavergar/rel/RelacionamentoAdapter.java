package com.sda.botaonavergar.rel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.cargo.CargoDialogoActivity;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;


/**
 * made by sda
 */
public class RelacionamentoAdapter extends RecyclerView.Adapter<RelacionamentoHolder> {

    public Context ctx;
    public ArrayList<Relacionamento> relacionamentos;

    public RelacionamentoAdapter(Context ctx, ArrayList<Relacionamento> relacionamentos) {
        this.ctx = ctx;
        this.relacionamentos = relacionamentos;
    }


    @Override
    public RelacionamentoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.relacionamento_modelo, null);
        RelacionamentoHolder holder = new RelacionamentoHolder(v);
        return holder;
    }



    @Override
    public void onBindViewHolder(RelacionamentoHolder holder, int position) {
        holder.txtnome.setText(relacionamentos.get(position).getRelacao());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, RelacionamentoAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, relacionamentos.get(pos).getId());
                i.putExtra(Constantes.NOME, relacionamentos.get(pos).getRelacao());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return relacionamentos.size();
    }

}