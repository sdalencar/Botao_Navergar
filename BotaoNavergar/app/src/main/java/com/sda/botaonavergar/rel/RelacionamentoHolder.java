package com.sda.botaonavergar.rel;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class RelacionamentoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtnome;
    public ItemClickListener itemClickListener;


    public RelacionamentoHolder(View itemView) {
        super(itemView);
        //ASSIGN
        txtnome = itemView.findViewById(R.id.modelo_relacionamento_nome);
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
