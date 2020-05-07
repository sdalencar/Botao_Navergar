package com.sda.botaonavergar.chq;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class ChequeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nome, valor, data, deposito;
    public ItemClickListener itemClickListener;


    public ChequeHolder(View itemView) {
        super(itemView);
        //ASSIGN
        nome = itemView.findViewById(R.id.modelo_quatro_primeiro);
        valor = itemView.findViewById(R.id.modelo_quatro_segundo);
        data = itemView.findViewById(R.id.modelo_quatro_terceiro);
        deposito = itemView.findViewById(R.id.modelo_quatro_quarto);
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
