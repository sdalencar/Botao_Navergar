package com.sda.botaonavergar.user;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.turno.Turno;
import com.sda.botaonavergar.turno.TurnoAtualizaActivity;
import com.sda.botaonavergar.turno.TurnoHolder;
import com.sda.botaonavergar.util.Constantes;
import com.sda.botaonavergar.util.ItemClickListener;

import java.util.ArrayList;

/**
 * made by sda
 */
public class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    public Context ctx;
    public ArrayList<User> users;

    public UserAdapter(Context ctx, ArrayList<User> users) {
        this.ctx = ctx;
        this.users = users;
    }


    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dois_modelo, null);
        UserHolder holder = new UserHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.iduser.setText("Funcionário.: \n" + users.get(position).getIdUser());
        holder.inivel.setText("Cargo: \n" + users.get(position).getNivel());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, UserAtualizaActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, users.get(pos).getId());
                i.putExtra(Constantes.USER, users.get(pos).getIdUser());
                i.putExtra(Constantes.NIVEL, users.get(pos).getNivel());
                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

}