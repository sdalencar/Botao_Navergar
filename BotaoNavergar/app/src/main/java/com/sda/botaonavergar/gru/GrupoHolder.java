package com.sda.botaonavergar.gru;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class GrupoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView ppt, nome, valor;
    public ItemClickListener itemClickListener;


    public GrupoHolder(View itemView) {
        super(itemView);
        //ASSIGN
        ppt = itemView.findViewById(R.id.modelo_tres_edit_primeiro);
        nome = itemView.findViewById(R.id.modelo_tres_edit_segundo);
        valor = itemView.findViewById(R.id.modelo_tres_edit_terceiro);

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
