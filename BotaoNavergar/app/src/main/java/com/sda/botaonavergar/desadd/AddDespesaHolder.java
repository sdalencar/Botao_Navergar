package com.sda.botaonavergar.desadd;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class AddDespesaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView iden, data, tipo, valor;
    public ItemClickListener itemClickListener;


    public AddDespesaHolder(View itemView) {
        super(itemView);
        //ASSIGN
        iden = itemView.findViewById(R.id.modelo_tres__um_um);
        data = itemView.findViewById(R.id.modelo_tres__um_dois);
        tipo = itemView.findViewById(R.id.modelo_tres__um_tres);
        valor = itemView.findViewById(R.id.modelo_tres__um_quatro);
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
