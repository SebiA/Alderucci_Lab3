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
import androidx.navigation.fragment.NavHostFragment;
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
import com.example.lab3.display.DisplayFragment;
import com.example.lab3.model.Country;

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
    private TextView textView;
    Bundle myBundle;

    private String url = "https://restcountries.eu/rest/v1/all";

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        countryListViewModel = new ViewModelProvider(this).get(CountryListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_countries, container, false);

        queue = Volley.newRequestQueue(getContext());
        countryList = new ArrayList<>();
        adapter = new ItemRecycleView(R.layout.cardview_layout,countryList);

        addButton = root.findViewById(R.id.add_row_button);
        recyclerView = root.findViewById(R.id.recycler_view);
        itemRecycleView = new ItemRecycleView(R.layout.cardview_layout,countryList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(itemRecycleView);
        textView = root.findViewById(R.id.my_text_view);

        loadCountryData();

        return root;
    }

    private void loadCountryData() {
        StringRequest stringRequest = new
                StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonData = array.getJSONObject(i);

                                String name = jsonData.getString("name");
                                String capital = jsonData.getString("capital");
                                String region = jsonData.getString("region");
                                String subRegion = jsonData.getString("subregion");

                                Country country = new Country(name,capital,region,subRegion);
                                countryList.add(country);
                            }

                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Error:Request did not work");
            }
        });

        queue.add(stringRequest);

        myBundle = new Bundle();
        //int index;
        //String selectedCountry = countryList.get(index);
        //myBundle.putString("cval", selectedCountry);
        //NavHostFragment.findNavController(DisplayFragment.this).navigate(R.id.displayFragment, myBundle);

    }

}
