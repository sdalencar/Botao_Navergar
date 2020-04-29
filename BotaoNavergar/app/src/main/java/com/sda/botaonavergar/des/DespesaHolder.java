package com.sda.botaonavergar.des;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class DespesaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView tipo;
    public ItemClickListener itemClickListener;


    public DespesaHolder(View itemView) {
        super(itemView);
        //ASSIGN
        tipo = itemView.findViewById(R.id.modelo_edittext_nome);
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
