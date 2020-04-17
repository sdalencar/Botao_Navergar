package com.sda.sobcontrole.ui.ui_04;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* made by sda */

public class QuartoViewModel extends ViewModel {

    private MutableLiveData<String> mNome;
    private MutableLiveData<String> mEndereco;
    private MutableLiveData<String> mTelefone;
    private MutableLiveData<String> mObs;

    public QuartoViewModel() {
        mNome = new MutableLiveData<>();
        mNome.setValue("This is quarto fragment");
    }

    public LiveData<String> getText() {
        return mNome;
    }
}