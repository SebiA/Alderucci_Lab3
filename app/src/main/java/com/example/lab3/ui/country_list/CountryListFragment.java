package com.example.lab3.ui.country_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab3.R;
import com.example.lab3.model.Country;
import com.example.lab3.ui.controllers.GetData;
import com.example.lab3.ui.controllers.ItemRecycleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryListFragment extends Fragment {

    private CountryListViewModel countryListViewModel;
    private RecyclerView recyclerView;
    private ItemRecycleView itemRecycleView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Country> countryList;
    private Button addButton;
    private RequestQueue queue;
    private GetData getData;

    //private String url = "https://restcountries.eu/rest/v1/all";

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        countryListViewModel = new ViewModelProvider(this).get(CountryListViewModel.class);
        queue = Volley.newRequestQueue(getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_countries, container, false);
        countryList = new ArrayList<>();
        adapter = new ItemRecycleView(R.layout.cardview_layout,countryListViewModel.getCountryList());
        addButton = root.findViewById(R.id.add_row_button);
        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        getData = new GetData(recyclerView, adapter, queue, this);
        getData.loadCountryData();
        adapter.notifyDataSetChanged();

        return root;
    }
}
