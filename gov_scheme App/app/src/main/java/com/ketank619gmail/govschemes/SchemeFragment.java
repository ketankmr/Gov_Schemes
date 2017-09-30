package com.ketank619gmail.govschemes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;

import static android.content.ContentValues.TAG;


public class SchemeFragment extends Fragment {
    VerticalViewPager verticalViewPager;
    VerticlePagerAdapter adapter;
    View view;
    SharedPreferences preferences;
    ArrayList<String> Scheme,Description,Url,Category;


    public SchemeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view= inflater.inflate(R.layout.fragment_scheme, container, false);
        preferences = getActivity().getSharedPreferences("mypref",Context.MODE_PRIVATE);
        verticalViewPager = (VerticalViewPager) view.findViewById(R.id.verticleViewPager);

        Gson gson = new Gson();
        String schme = preferences.getString("Scheme", null);
        String desc= preferences.getString("Desc", null);
        String url= preferences.getString("Url", null);
        String categories= preferences.getString("categories", null);
        String category_select= preferences.getString("cat_selected", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        if(schme!=null && desc!=null && url!=null && categories!=null) {


            Scheme = gson.fromJson(schme, type);
            Description = gson.fromJson(desc,type);
            Url = gson.fromJson(url,type);
            Category = gson.fromJson(categories,type);

            if(category_select!=null){
                ArrayList<String> Scheme_c=new ArrayList<>();
                ArrayList<String> Description_c=new ArrayList<>();
                ArrayList<String> Url_c=new ArrayList<>();
                for(int i=0;i<Category.size();i++){
                    if (Category.get(i).equalsIgnoreCase(category_select)) {
                        Scheme_c.add(Scheme.get(i));
                        Description_c.add(Description.get(i));
                        Url_c.add(Url.get(i));
                    }
                }

                adapter = new VerticlePagerAdapter(getActivity(), Scheme_c, Description_c, Url_c);
                verticalViewPager.setAdapter(adapter);
            }
           else {
                adapter = new VerticlePagerAdapter(getActivity(), Scheme, Description, Url);
                verticalViewPager.setAdapter(adapter);
            }
        }


        return view;
    }




}
