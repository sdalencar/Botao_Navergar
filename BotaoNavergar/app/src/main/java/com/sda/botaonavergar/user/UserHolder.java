package com.sda.botaonavergar.user;


import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView iduser, inivel;
    public ItemClickListener itemClickListener;


    public UserHolder(View itemView) {
        super(itemView);
        //ASSIGN
        iduser = itemView.findViewById(R.id.modelo_dois_um);
        inivel = itemView.findViewById(R.id.modelo_dois_dois);
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
