package com.sda.botaonavergar.pes;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.sda.botaonavergar.R;
import com.sda.botaonavergar.util.ItemClickListener;

/**
 * made by sda
 */
public class PessoaHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView nome, alias, celular, relacionamento;
    public ItemClickListener itemClickListener;


    public PessoaHolder(View itemView) {
        super(itemView);
        //ASSIGN
        nome = itemView.findViewById(R.id.modelo_pes_nome);
        alias = itemView.findViewById(R.id.modelo_pes_alias);
        celular = itemView.findViewById(R.id.modelo_pes_celular);
        relacionamento = itemView.findViewById(R.id.modelo_pes_relacionamento);
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
