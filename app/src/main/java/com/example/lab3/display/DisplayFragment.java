package com.example.lab3.display;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab3.R;
import com.example.lab3.model.Country;
import com.example.lab3.ui.controllers.GetData;
import com.example.lab3.ui.country_list.CountryListFragment;
import com.example.lab3.ui.country_list.CountryListViewModel;

import java.util.ArrayList;

public class DisplayFragment extends Fragment {

    private DisplayViewModel mViewModel;
    private CountryListViewModel countriesViewModel;

    public static DisplayFragment newInstance() {
        return new DisplayFragment();
    }

    public TextView nameDisplay;
    public TextView capitalDisplay;
    public TextView regionDisplay;
    public TextView subRegionDisplay;
    private MutableLiveData<ArrayList<Country>> myCountryList;
    public Bundle bundle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.display_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        nameDisplay = view.findViewById(R.id.display_country);
        capitalDisplay = view.findViewById(R.id.display_capital);
        regionDisplay = view.findViewById(R.id.display_region);
        subRegionDisplay = view.findViewById(R.id.display_subRegion);
        bundle = getArguments();
        int position = bundle.getInt("card");
        Country country = GetData.sCountryList.get(position);
        nameDisplay.setText(country.name);
        capitalDisplay.setText(country.capital);
        regionDisplay.setText(country.region);
        subRegionDisplay.setText(country.subRegion);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DisplayViewModel.class);
    }

}