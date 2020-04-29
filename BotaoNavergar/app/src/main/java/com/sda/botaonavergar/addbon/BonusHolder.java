package com.sda.botaonavergar.addbon;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class BonusHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nome;
    public ItemClickListener itemClickListener;


    public BonusHolder(View itemView) {
        super(itemView);
        //ASSIGN
        nome = itemView.findViewById(R.id.modelo_tipo_bonus_nome);
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
