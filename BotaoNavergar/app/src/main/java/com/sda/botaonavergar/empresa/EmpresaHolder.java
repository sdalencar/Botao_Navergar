package com.sda.botaonavergar.empresa;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class EmpresaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtnome, txtendereco, txttelefone;
    public ItemClickListener itemClickListener;


    public EmpresaHolder(View itemView) {
        super(itemView);
        //ASSIGN
        txtnome = itemView.findViewById(R.id.modelo_empresa_nome);
        txtendereco = itemView.findViewById(R.id.modelo_empresa_endereco);
        txttelefone = itemView.findViewById(R.id.modelo_empresa_telefone);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(v, getLayoutPosition());
    }

    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
