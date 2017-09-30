package com.ketank619gmail.govschemes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class User_Verify extends AppCompatActivity {

    String accountKitId;
  SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__verify);

        preferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);

        if (preferences.getString("user_id",null)!=null){
            Intent intent = new Intent(User_Verify.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                 accountKitId = account.getId();
                Log.d("accountKitId",accountKitId);

            }
            @Override
            public void onError(final AccountKitError error) {
                // display error
                Toast.makeText(User_Verify.this,""+error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void sign(View view){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("User", accountKitId);


       final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setTitle("Checking Everything ");
        dialog.show();



        JsonObjectRequest req = new JsonObjectRequest(All_Links.values.add_user, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject json) {

                        try {
                            dialog.dismiss();

                            Boolean error = json.getBoolean("error");


                            if (!error) {
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("user_id",accountKitId);
                                editor.apply();
                                Intent intent = new Intent(User_Verify.this, MainActivity.class);
                                startActivity(intent);
                                finish();


                            }else {

                                Toast.makeText(getApplicationContext(), "Problem Adding User", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            dialog.dismiss();
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.e("Error: ", error.getMessage());
                dialog.dismiss();

                Toast.makeText(getApplicationContext(),"Error Connecting....",Toast.LENGTH_LONG).show();
            }
        });

        AppController.getInstance().addToRequestQueue(req);

    }


}
