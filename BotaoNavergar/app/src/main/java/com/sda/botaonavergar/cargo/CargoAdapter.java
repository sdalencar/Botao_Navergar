package com.sda.botaonavergar.cargo;

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
public class CargoAdapter extends RecyclerView.Adapter<CargoHolder> {

    public Context ctx;
    public ArrayList<Cargo> cargos;

    public CargoAdapter(Context ctx, ArrayList<Cargo> cargos) {
        this.ctx = ctx;
        this.cargos = cargos;
    }


    @Override
    public CargoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cargo_modelo, null);
        CargoHolder holder = new CargoHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(CargoHolder holder, int position) {
        holder.txtnome.setText(cargos.get(position).getNome());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, CargoDialogoActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, cargos.get(pos).getId());
                i.putExtra(Constantes.NOME, cargos.get(pos).getNome());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return cargos.size();
    }

}