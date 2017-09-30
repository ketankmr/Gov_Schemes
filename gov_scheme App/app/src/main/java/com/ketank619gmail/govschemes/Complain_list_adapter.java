package com.ketank619gmail.govschemes;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ketan on 03-Sep-17.
 */

public class Complain_list_adapter extends RecyclerView.Adapter<Complain_list_adapter.ViewHolder> {
    ArrayList<String> heading ,description,Confirmed;
    ArrayList<Integer> count,id;
    Context context;
    SharedPreferences preferences;
    ArrayList<String> Confirmed_User;

    public Complain_list_adapter(ArrayList<Integer>id,ArrayList<String> heading, ArrayList<String> description,ArrayList<Integer>count,ArrayList<String> Confirmed, Context context) {
        this.id=id;
        this.heading = heading;
        this.description = description;
        this.count = count;
        this.Confirmed= Confirmed;
        this.context = context;

        preferences = context.getSharedPreferences("mypref",Context.MODE_PRIVATE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.complain_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        if(!Confirmed.get(position).equalsIgnoreCase("null")){
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            Confirmed_User= gson.fromJson(Confirmed.get(position),type);
             String user=preferences.getString("user_id",null);
            if(Confirmed_User.contains(user)){

                holder.add_count.setVisibility(View.GONE);
            }

        }

        else{
            Confirmed_User = new ArrayList<>();
        }

        holder.heading.setText(heading.get(position));
        holder.desc.setVisibility(View.GONE);
        holder.count.setText(""+count.get(position));
        holder.progress.setVisibility(View.GONE);
        holder.add_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.progress.setVisibility(View.VISIBLE);
                holder.count.setText(""+(count.get(position)+1));
                holder.add_count.setVisibility(View.GONE);
                add_complain(id.get(position),holder);
            }
        });

    }

    @Override
    public int getItemCount() {
        return heading.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView heading ,desc,count;
        ImageButton add_count;
        ProgressBar progress;
        public ViewHolder(View itemView) {
            super(itemView);
            heading = (TextView)itemView.findViewById(R.id.title);
            desc = (TextView)itemView.findViewById(R.id.desc);
            count = (TextView)itemView.findViewById(R.id.count);
            add_count = (ImageButton) itemView.findViewById(R.id.imageButton);
            progress = (ProgressBar) itemView.findViewById(R.id.progress);

        }

    }

    public void add_complain(final Integer problem_id , final ViewHolder holder){


        Confirmed_User.add(preferences.getString("user_id",null));
        final Gson gson = new Gson();
        String url = All_Links.values.add_count;

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("", "Login Response: " + response.toString());
                holder.progress.setVisibility(View.GONE);

                try {
                    JSONObject jObj = new JSONObject(response);

                    Boolean error = jObj.getBoolean("error");




                    if (error) {

                        Toast.makeText(context.getApplicationContext(),"Error Occured...Try Again",Toast.LENGTH_LONG).show();

                        holder.add_count.setVisibility(View.VISIBLE);
                    } else {

                        Toast.makeText(context.getApplicationContext(),"Count Added....",Toast.LENGTH_LONG).show();



                    }




                } catch (Exception e) {
                    // JSON error
                    e.printStackTrace();

                    holder.add_count.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                holder.progress.setVisibility(View.GONE);
                holder.add_count.setVisibility(View.VISIBLE);

                Toast.makeText(context.getApplicationContext(),"Login Error: " + error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<>();


                params.put("ID",""+problem_id);
                params.put("Confirmed",gson.toJson(Confirmed_User));


                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, "");

    }


}
