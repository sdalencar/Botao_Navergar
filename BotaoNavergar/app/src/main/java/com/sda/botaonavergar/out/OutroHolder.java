package com.sda.botaonavergar.out;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class OutroHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nome, valor;
    public ItemClickListener itemClickListener;


    public OutroHolder(View itemView) {
        super(itemView);
        //ASSIGN
        nome = itemView.findViewById(R.id.modelo_dois_dois);
        valor = itemView.findViewById(R.id.modelo_dois_um);
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
