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
    public TextView product_detail_textViewshortDescription;
    public TextView product_detail_textViewPrice;

    String productNameIntent, productShortDescriptionIntent, productImageIntent, productPriceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        product_detail_productimageView = findViewById(R.id.product_detail_image_view);
        product_detail_textViewproductName = findViewById(R.id.product_detail_text_view_product_name);
        product_detail_textViewshortDescription = findViewById(R.id.product_detail_text_view_short_description);
        product_detail_textViewPrice = findViewById(R.id.product_detail_text_view_price);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            productNameIntent = extras.getString("product_name");
            productShortDescriptionIntent = extras.getString("product_short_description");
            productImageIntent = extras.getString("product_image");
            productPriceIntent = extras.getString("product_price");

        }

        product_detail_textViewproductName.setText("Product Name: " + productNameIntent);
        product_detail_textViewshortDescription.setText("Short Description: " + Html.fromHtml(productShortDescriptionIntent));
        product_detail_textViewPrice.setText("Price: " + productPriceIntent);
        Picasso.get().load(productImageIntent).fit().centerInside().into(product_detail_productimageView);

    }
}
