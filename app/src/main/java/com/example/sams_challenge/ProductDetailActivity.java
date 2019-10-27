package com.example.sams_challenge;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    public ImageView product_detail_productimageView;
    public TextView product_detail_textViewproductName;
    public TextView product_detail_textViewPrice;

    public TextView product_detail_long_description;
    public TextView product_detail_review_rating;
    public TextView product_detail_review_count;
    public TextView product_detail_in_stock;


    String productNameIntent, productShortDescriptionIntent, productImageIntent, productPriceIntent, productLongDescriptionIntent, productInStockIntent;
    double productReviewRatingIntent;
    int productReviewCountIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        product_detail_productimageView = findViewById(R.id.product_detail_image_view);
        product_detail_textViewproductName = findViewById(R.id.product_detail_text_view_product_name);
        product_detail_textViewPrice = findViewById(R.id.product_detail_text_view_price);
        product_detail_long_description = findViewById(R.id.product_detail_long_description);
        product_detail_review_rating = findViewById(R.id.product_detail_text_view_review_rating);
        product_detail_review_count = findViewById(R.id.product_detail_text_view_review_count);
        product_detail_in_stock = findViewById(R.id.product_detail_text_view_in_stock);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            productNameIntent = extras.getString("product_name");
            productImageIntent = extras.getString("product_image");
            productPriceIntent = extras.getString("product_price");
            productLongDescriptionIntent = extras.getString("product_longDescription");
            productReviewRatingIntent = extras.getDouble("product_review_rating");
            productReviewCountIntent = extras.getInt("product_review_count");
            productInStockIntent = extras.getString("product_in_stock");



        }

        String productNameSource = "<b>" + "Product Name: " + "</b> " + productNameIntent;
        String productPriceSource = "<b>" + "Price: " + "</b> " + productPriceIntent;
        String productLongDescriptionSource = "<b>" + "Long Description: " + "</b> " + productLongDescriptionIntent;
        String productReviewRatingSource = "<b>" + "Review Rating: " + "</b> " + productReviewRatingIntent;
        String productReviewCountSource = "<b>" + "Review Count: " + "</b> " + productReviewCountIntent;
        String productInStockSource = "<b>" + "In Stock: " + "</b> " + productInStockIntent;


        product_detail_textViewproductName.setText(Html.fromHtml(productNameSource));
        product_detail_textViewPrice.setText(Html.fromHtml(productPriceSource));

        product_detail_long_description.setText(Html.fromHtml(productLongDescriptionSource));
        product_detail_review_rating.setText(Html.fromHtml(productReviewRatingSource));
        product_detail_review_count.setText(Html.fromHtml(productReviewCountSource));
        product_detail_in_stock.setText(Html.fromHtml(productInStockSource));


        Picasso.get().load(productImageIntent).fit().centerInside().into(product_detail_productimageView);

    }
}
