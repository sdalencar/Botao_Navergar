package com.sda.botaonavergar.turno;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class TurnoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtnumero, txtnome;
    public ItemClickListener itemClickListener;


    public TurnoHolder(View itemView) {
        super(itemView);
        //ASSIGN
        txtnumero = itemView.findViewById(R.id.modelo_turno_numero);
        txtnome = itemView.findViewById(R.id.modelo_turno_nome);
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
