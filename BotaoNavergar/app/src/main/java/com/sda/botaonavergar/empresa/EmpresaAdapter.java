package com.sda.botaonavergar.empresa;

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

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaHolder> {

    public Context ctx;
    public ArrayList<Empresa> empresas;

    public EmpresaAdapter(Context ctx, ArrayList<Empresa> empresas) {
        this.ctx = ctx;
        this.empresas = empresas;
    }


    @Override
    public EmpresaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.empresa_modelo, null);
        EmpresaHolder holder = new EmpresaHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(EmpresaHolder holder, int position) {
        holder.txtnome.setText(empresas.get(position).getNome());
        holder.txtendereco.setText(empresas.get(position).getEndereco());
        holder.txttelefone.setText(empresas.get(position).getTelefone());


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //OPEN DETAIL ACTIVITY
                //PASS DATA

                //CREATE INTENT
                Intent i = new Intent(ctx, EmpresaDialogoActivity.class);

                //LOAD DATA
                i.putExtra(Constantes.ID, empresas.get(pos).getId());
                i.putExtra(Constantes.NOME, empresas.get(pos).getNome());
                i.putExtra(Constantes.ENDERECO, empresas.get(pos).getEndereco());
                i.putExtra(Constantes.TELEFONE, empresas.get(pos).getTelefone());

                //START ACTIVITY
                ctx.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return empresas.size();
    }

}