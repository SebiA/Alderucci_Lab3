package com.example.lab3.ui.controllers;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab3.model.Country;
import com.example.lab3.ui.country_list.CountryListFragment;
import com.example.lab3.ui.country_list.CountryListViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetData {

    private MutableLiveData<ArrayList<Country>> countryList;
    private RequestQueue queue;
    private String url = "https://restcountries.eu/rest/v1/all";
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private CountryListViewModel countryListViewModel;
    private final String TAG = "NOW";
    public static ArrayList<Country> sCountryList = new ArrayList<Country>();

    public GetData(RecyclerView recyclerView, RecyclerView.Adapter adapter, RequestQueue queue, CountryListFragment countryListFragment) {
        countryListViewModel = new ViewModelProvider(countryListFragment).get(CountryListViewModel.class);
        countryList = countryListViewModel.getCountryList();
        this.queue = queue;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    public void loadCountryData() {
        int listSize = countryListViewModel.getCountryList().getValue().size();
        if(listSize == 0) {
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

                                    Country country = new Country(name, capital, region, subRegion);
                                    sCountryList.add(country);
                                }
                                recyclerView.setAdapter(adapter);
                                countryListViewModel.addCountryList(sCountryList);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("X","Error:Request did not work");
                }
            });
            queue.add(stringRequest);
        }
    }

}
