package com.example.sams_challenge;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    public ImageView product_detail_productimageView;
    public TextView product_detail_textViewproductName;
    public TextView product_detail_textViewPrice;

    public TextView product_detail_long_description;
    public TextView product_detail_review_rating;
    public TextView product_detail_review_count;
    public TextView product_detail_in_stock;


    String productNameIntent, productImageIntent, productPriceIntent, productLongDescriptionIntent, productInStockIntent;
    double productReviewRatingIntent;
    int productReviewCountIntent, getPositionIntent;
    ArrayList<Product> cardItemListIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        product_detail_productimageView = findViewById(R.id.product_detail_image_view);
        product_detail_textViewproductName = findViewById(R.id.product_detail_text_view_product_name);
        product_detail_textViewPrice = findViewById(R.id.product_detail_text_view_price);
        product_detail_long_description = findViewById(R.id.product_detail_long_description);
        product_detail_review_rating = findViewById(R.id.product_detail_text_view_review_rating);
        product_detail_review_count = findViewById(R.id.product_detail_text_view_review_count);
        product_detail_in_stock = findViewById(R.id.product_detail_text_view_in_stock);
        cardItemListIntent = new ArrayList<>();


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            productNameIntent = extras.getString("product_name");
            productImageIntent = extras.getString("product_image");
            productPriceIntent = extras.getString("product_price");
            productLongDescriptionIntent = extras.getString("product_longDescription");
            productReviewRatingIntent = extras.getDouble("product_review_rating");
            productReviewCountIntent = extras.getInt("product_review_count");
            productInStockIntent = extras.getString("product_in_stock");
            cardItemListIntent = (ArrayList<Product>) extras.getSerializable("card_item_list");
            getPositionIntent = extras.getInt("position_clicked");


        }

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this, cardItemListIntent);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(getPositionIntent);

    }
}
