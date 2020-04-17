package com.sda.botaonavergar.cargo;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class CargoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtnome;
    public ItemClickListener itemClickListener;


    public CargoHolder(View itemView) {
        super(itemView);
        //ASSIGN
        txtnome = itemView.findViewById(R.id.modelo_cargo_nome);
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
