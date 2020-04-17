package com.sda.sobcontrole.ui.ui_04;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sda.sobcontrole.R;
import com.sda.sobcontrole.ui.ui_01.HomeViewModel;

/* made by sda */

public class QuartoFragment extends Fragment {

    public EditText nome, endereco, telefone, obd;
    public Button btDescartar, btSalvar;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_quarto, container, false);

        nome = root.findViewById(R.id.et_qua_nome);
        endereco = root.findViewById(R.id.et_qua_endereco);
        telefone = root.findViewById(R.id.et_qua_telefone);
        obd = root.findViewById(R.id.et_qua_obs);
        btDescartar = root.findViewById(R.id.bt_fr_qua_limpar);
        btSalvar = root.findViewById(R.id.bt_fr_qua_salvar);

        return root;
    }
}
