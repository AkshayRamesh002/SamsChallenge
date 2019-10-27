package com.example.sams_challenge;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CardAdapter.OnCardListener {

    private RecyclerView mRecyclerView;
    private ArrayList<CardItem> mCardItemList = new ArrayList<>();
    private CardAdapter mCardAdapter;
    private RequestQueue mRequestQueue;
    private LinearLayoutManager layoutManager;
    private int page = 1;
    private ProgressBar progressBarLoading;



    String main_url = "https://mobile-tha-server.firebaseapp.com";
    String url = "https://mobile-tha-server.firebaseapp.com/walmartproducts/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        progressBarLoading = findViewById(R.id.progressBarLoading);


        mCardAdapter = new CardAdapter(MainActivity.this,mCardItemList, this);

        progressBarLoading.setVisibility(View.INVISIBLE);

        mRecyclerView.setAdapter(mCardAdapter);

        mRecyclerView.addOnScrollListener(prOnScrollListener);




        mRequestQueue = Volley.newRequestQueue(this);
        getData();
    }


    private RecyclerView.OnScrollListener prOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if(islastItemDisplaying(recyclerView)){
                //so i would call the get data method here
                // show loading progress
                progressBarLoading.setVisibility(View.VISIBLE);
                getData();
                Log.i("ListActivity", "LoadMore");
            }
        }


    };
    private boolean islastItemDisplaying(RecyclerView recyclerView){
        //check if the adapter item count is greater than 0
        if(recyclerView.getAdapter().getItemCount() != 0){
            //get the last visible item on screen using the layoutmanager
            int lastVisibleItemPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
            //apply some logic here.
            if(lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }

        return  false;
    }

    private void getData(){

        mRequestQueue.add(getDataFromServer(page));

        page++;
    }

    private void parseData(JSONObject response){

        try {
            JSONArray jsonArray = response.getJSONArray("products");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject hit = jsonArray.getJSONObject(i);

                String productName = hit.getString("productName");
                String shortDescription = hit.optString("shortDescription");
                if (shortDescription.equals("")) {
                    shortDescription = "NONE";
                }
                Log.i("PRODUCT_NAME", productName);
                String price = hit.getString("price");
                String imageUrl = hit.getString("productImage");

                mCardItemList.add(new CardItem(productName, shortDescription, price, main_url + imageUrl));
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressBarLoading.setVisibility(View.INVISIBLE);

        mCardAdapter.notifyDataSetChanged();


    }




    private JsonObjectRequest getDataFromServer(final int page) {
        //good practice to put a loading progress here

        //Json request begins
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url + page + "/30", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //this is called when a response is gotten from the server


                        //after getting the data, I need to parse it the view
                        Log.i("URL_RESPONSE", response.toString());
                        Log.i("PAGE_NUMBER_CHANGE", url + page + "/30");


                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    //TODO
                    Toast.makeText(MainActivity.this, "time out", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    //TODO
                    Toast.makeText(MainActivity.this, "server error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof NetworkError) {
                    //TODO
                    Toast.makeText(MainActivity.this, "network error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    //TODO
                    Toast.makeText(MainActivity.this, "parse error", Toast.LENGTH_SHORT).show();
                }
                /* progressBar.setVisibility(View.GONE); */
                //If an error occurs that means end of the list has reached

                Toast.makeText(MainActivity.this, "No More Result Available", Toast.LENGTH_SHORT).show();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return jsonObjectRequest;

    }

    @Override
    public void onCardClick(int position) {

        Toast.makeText(this, "clicked" + position, Toast.LENGTH_SHORT).show();
    }
}

