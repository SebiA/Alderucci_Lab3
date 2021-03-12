package com.example.lab3.ui.country_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab3.model.Country;
import java.util.ArrayList;

public class CountryListViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Country>> countryList;

    public CountryListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
        countryList = new MutableLiveData<>();
        countryList.setValue(new ArrayList<>());
    }

    public MutableLiveData<ArrayList<Country>> getCountryList() {
        return countryList;
    }

    public void addCountryList(ArrayList<Country> myCountryList) {
        countryList.postValue(myCountryList);
    }

    public LiveData<String> getText() {
        return mText;
    }

}