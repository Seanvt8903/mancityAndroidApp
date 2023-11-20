package com.example.mancity.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("HIGHLIGHTS! Man United 0-3 Man City");
    }

    public LiveData<String> getText() {
        return mText;
    }
}