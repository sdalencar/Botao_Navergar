package com.sda.botaonavergar.maq;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class MaquinaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView grupo, numero;
    public ItemClickListener itemClickListener;


    public MaquinaHolder(View itemView) {
        super(itemView);
        //ASSIGN
        grupo = itemView.findViewById(R.id.modelo_quatro_terceiro);
        numero = itemView.findViewById(R.id.modelo_quatro_quarto);
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
