package com.example.sams_challenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements CardAdapter.OnCardListener {


    private RecyclerView mRecyclerView;
    private ArrayList<Product> mCardItemList = new ArrayList<>();
    private CardAdapter mCardAdapter;
    private RequestQueue mRequestQueue;
    private LinearLayoutManager layoutManager;
    private int page = 1;
    private ProgressBar progressBarLoading;
    private RecyclerView.OnScrollListener prOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (islastItemDisplaying(recyclerView)) {

                progressBarLoading.setVisibility(View.VISIBLE);
                getData();
            }
        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        progressBarLoading = findViewById(R.id.progressBarLoading);


        mCardAdapter = new CardAdapter(MainActivity.this, mCardItemList, this);

        progressBarLoading.setVisibility(View.INVISIBLE);

        mRecyclerView.setAdapter(mCardAdapter);

        mRecyclerView.addOnScrollListener(prOnScrollListener);


        mRequestQueue = Volley.newRequestQueue(this);
        getData();
    }

    private boolean islastItemDisplaying(RecyclerView recyclerView) {
        if (recyclerView.getAdapter().getItemCount() != 0) {
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1)
                return true;
        }

        return false;
    }

    private void getData() {

        mRequestQueue.add(getDataFromServer(page));

        page++;
    }

    private void parseData(JSONObject response) {

        try {
            JSONArray jsonArray = response.getJSONArray("products");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject hit = jsonArray.getJSONObject(i);

                String productName = hit.optString("productName");
                if (productName.equals("")) {
                    productName = ConfigValues.DEFAULT_DETAIL;
                }

                String shortDescription = hit.optString("shortDescription");
                if (shortDescription.equals("")) {
                    shortDescription = ConfigValues.DEFAULT_DETAIL;
                }

                String price = hit.optString("price");
                if (price.equals("")) {
                    price = ConfigValues.DEFAULT_PRICE;
                }

                String imageUrl = hit.optString("productImage");
                if (imageUrl.equals("")) {
                    imageUrl = ConfigValues.DEFAULT_IMAGE;
                }

                String longDescription = hit.optString("longDescription");
                if (longDescription.equals("")) {
                    longDescription = ConfigValues.DEFAULT_DETAIL;
                }

                double reviewRating = hit.optDouble("reviewRating");
                if (Double.isNaN(reviewRating)) {
                    reviewRating = ConfigValues.DEFAULT_REVIEW_RATING;
                }


                int reviewCount = hit.optInt("reviewCount");

                boolean inStock = hit.optBoolean("inStock");
                String inStockStr = "";
                if (inStock == true) {
                    inStockStr = ConfigValues.DEFAULT_IN_STOCK;
                } else {
                    inStockStr = ConfigValues.DEFAULT_NOT_IN_STOCK;
                }

                mCardItemList.add(new Product(productName, shortDescription, price, ConfigValues.MAIN_URL + imageUrl, longDescription, reviewRating, reviewCount, inStockStr));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        progressBarLoading.setVisibility(View.INVISIBLE);

        mCardAdapter.notifyDataSetChanged();


    }


    private JsonObjectRequest getDataFromServer(final int page) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ConfigValues.MAIN_URL + ConfigValues.WALMART_PRODUCTS + page + ConfigValues.PAGE_SIZE, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                showAlertBox();

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return jsonObjectRequest;

    }

    @Override
    public void onCardClick(int position) {

        Intent i = new Intent(MainActivity.this, ProductDetailActivity.class);
        i.putExtra("product_name", mCardItemList.get(position).getProductName());
        i.putExtra("product_image", mCardItemList.get(position).getProductImageUrl());
        i.putExtra("product_price", mCardItemList.get(position).getPrice());
        i.putExtra("product_longDescription", mCardItemList.get(position).getLongDescription());
        i.putExtra("product_review_rating", mCardItemList.get(position).getReviewRating());
        i.putExtra("product_review_count", mCardItemList.get(position).getReviewCount());
        i.putExtra("product_in_stock", mCardItemList.get(position).getInStock());
        i.putExtra("card_item_list", mCardItemList);
        i.putExtra("position_clicked", position);

        startActivity(i);
    }

    public void showAlertBox() {

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(ConfigValues.ALERT_TITLE);
        alertDialog.setMessage(ConfigValues.ALERT_MESSAGE);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, ConfigValues.OK,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                });
        alertDialog.show();
    }
}

