package com.example.lab3.display;

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
import com.example.lab3.ui.country_list.CountryListFragment;

import java.util.ArrayList;

public class DisplayFragment extends Fragment {

    private DisplayViewModel mViewModel;

    public static DisplayFragment newInstance() {
        return new DisplayFragment();
    }

    public TextView nameDisplay;
    public TextView capitalDisplay;
    public TextView regionDisplay;
    public TextView subRegionDisplay;
    private ArrayList<Country> myCountryList;
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
        String myName = bundle.getString("name");
        String myCapital = bundle.getString("capital");
        String myRegion = bundle.getString("region");
        String mySubRegion = bundle.getString("subregion");
        nameDisplay.setText(myName);
        capitalDisplay.setText(myCapital);
        regionDisplay.setText(myRegion);
        subRegionDisplay.setText(mySubRegion);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DisplayViewModel.class);
    }

}