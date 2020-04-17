package com.sda.sobcontrole.ui.second;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/* made by sda */

public class HomeSecondViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeSecondViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}