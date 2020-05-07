package com.sda.botaonavergar.lei;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class LeituraHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView numero, entrada, saida;
    public ItemClickListener itemClickListener;


    public LeituraHolder(View itemView) {
        super(itemView);
        //ASSIGN
        numero = itemView.findViewById(R.id.modelo_empresa_nome);
        entrada = itemView.findViewById(R.id.modelo_empresa_endereco);
        saida = itemView.findViewById(R.id.modelo_empresa_telefone);
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
