package com.sda.botaonavergar.cartoes;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class CartoesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView doc, tipo, valor;
    public ItemClickListener itemClickListener;


    public CartoesHolder(View itemView) {
        super(itemView);
        //ASSIGN
        doc = itemView.findViewById(R.id.modelo_tres_primeiro);
        tipo = itemView.findViewById(R.id.modelo_tres_segundo);
        valor = itemView.findViewById(R.id.modelo_tres_terceiro);
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
