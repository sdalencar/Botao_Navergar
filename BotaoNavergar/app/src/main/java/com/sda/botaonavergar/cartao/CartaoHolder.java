package com.sda.botaonavergar.cartao;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class CartaoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nome;
    public ItemClickListener itemClickListener;


    public CartaoHolder(View itemView) {
        super(itemView);
        //ASSIGN
        nome = itemView.findViewById(R.id.modelo_edittext_nome);
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
