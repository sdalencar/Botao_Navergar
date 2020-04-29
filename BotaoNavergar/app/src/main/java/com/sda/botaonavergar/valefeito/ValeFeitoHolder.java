package com.sda.botaonavergar.turno;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class ValeFeitoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView indentificador, nome, relacao, valor;
    public ItemClickListener itemClickListener;


    public ValeFeitoHolder(View itemView) {
        super(itemView);
        //ASSIGN
        indentificador = itemView.findViewById(R.id.modelo_quatro_primeiro);
        nome = itemView.findViewById(R.id.modelo_quatro_segundo);
        relacao = itemView.findViewById(R.id.modelo_quatro_terceiro);
        valor = itemView.findViewById(R.id.modelo_quatro_quarto);
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
