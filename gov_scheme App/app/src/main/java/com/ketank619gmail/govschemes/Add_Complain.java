package com.ketank619gmail.govschemes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Add_Complain extends AppCompatActivity {
    EditText problem, description;
    AutoCompleteTextView state;
    SharedPreferences preferences;
    public ArrayList<String> STATE;
    ArrayAdapter stateadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__complain);

        preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);


        problem = (EditText) findViewById(R.id.problem);
        description = (EditText) findViewById(R.id.Description);
        state = (AutoCompleteTextView) findViewById(R.id.state);

        Set<String> states= preferences.getStringSet("states",null);

        if(states!=null){
            STATE  = new ArrayList<>(states);
            stateadapter  = new ArrayAdapter<String>(Add_Complain.this, android.R.layout.select_dialog_item, STATE);

            state.setThreshold(1);

            state.setAdapter(stateadapter);}




    }
    public void save_details(View view){
      final String problems = problem.getText().toString().trim();
       final String states = state.getText().toString().trim();
        final String desc = description.getText().toString().trim();

        add_complain(problems,desc,states);

    }



    public void add_complain(final String problem, final String description, final String states){
        final ProgressDialog dialog;
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please Wait...");
        dialog.setMessage("Adding Complaint");
        dialog.show();


        String url = All_Links.values.add_complain;

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("", "Login Response: " + response.toString());


                try {
                    JSONObject jObj = new JSONObject(response);

                   Boolean error = jObj.getBoolean("error");

                    dialog.dismiss();


                    if (error) {

                        Toast.makeText(getApplicationContext(),"Error Occured...Try Again",Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getApplicationContext(),"Complain Added Successfully",Toast.LENGTH_LONG).show();

                        finish();
                    }




                } catch (Exception e) {
                    // JSON error
                    e.printStackTrace();
                    dialog.dismiss();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

              dialog.dismiss();

                Toast.makeText(getApplicationContext(),"Login Error: " + error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();


                params.put("Problem", problem);
                params.put("Description", description);
                params.put("Location", states);


                return params;
            }

        };
        AppController.getInstance().addToRequestQueue(strReq, "");

    }


}
