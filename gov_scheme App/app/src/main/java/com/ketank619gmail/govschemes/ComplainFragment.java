package com.ketank619gmail.govschemes;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Set;


public class ComplainFragment extends Fragment {
   RecyclerView recyclerView;
 Complain_list_adapter adapter;
    View view;
    ArrayList<String> heading,description,Confirmed;
    ArrayList<Integer>count,problem_id;

    SharedPreferences preferences ;


    public ComplainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view =inflater.inflate(R.layout.fragment_complain, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);

        preferences= getActivity().getSharedPreferences("mypref", Context.MODE_PRIVATE);



        Gson gson = new Gson();

        String problem = preferences.getString("Problem",null);
        String desc = preferences.getString("complain_desc",null);
        String counting = preferences.getString("complain_count",null);
        String id = preferences.getString("Problem_id",null);
        String confirm = preferences.getString("confirmed",null);


        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Type type2 = new TypeToken<ArrayList<Integer>>() {}.getType();


        if(problem!=null && desc!=null && counting!=null && id!=null && confirm!=null){
            heading = gson.fromJson(problem,type);
            description = gson.fromJson(desc,type);
            Confirmed = gson.fromJson(confirm,type);
            count = gson.fromJson(counting,type2);
            problem_id = gson.fromJson(id,type2);


            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            adapter= new Complain_list_adapter(problem_id,heading,description,count,Confirmed,getActivity());

            recyclerView.setAdapter(adapter);

        }




        return view;
    }



}
