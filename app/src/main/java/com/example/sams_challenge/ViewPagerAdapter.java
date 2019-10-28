package com.example.sams_challenge;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<CardItem> cardItemList;
    LayoutInflater inflater;

    ViewPagerAdapter(Context context, ArrayList<CardItem> cardItemList) {
        this.context = context;
        this.cardItemList = cardItemList;
    }

    @Override
    public int getCount() {
        return cardItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        // Declare Variables
        ImageView product_detail_productimageView;
        TextView product_detail_textViewproductName;
        TextView product_detail_textViewPrice;

        TextView product_detail_long_description;
        TextView product_detail_review_rating;
        TextView product_detail_review_count;
        TextView product_detail_in_stock;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_product_detail, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        product_detail_productimageView = itemView.findViewById(R.id.product_detail_image_view);
        product_detail_textViewproductName = itemView.findViewById(R.id.product_detail_text_view_product_name);
        product_detail_textViewPrice = itemView.findViewById(R.id.product_detail_text_view_price);
        product_detail_long_description = itemView.findViewById(R.id.product_detail_long_description);
        product_detail_review_rating = itemView.findViewById(R.id.product_detail_text_view_review_rating);
        product_detail_review_count = itemView.findViewById(R.id.product_detail_text_view_review_count);
        product_detail_in_stock = itemView.findViewById(R.id.product_detail_text_view_in_stock);

        String productNameSource = "<b>" + "Product Name: " + "</b> " + cardItemList.get(position).getProductName();
        String productPriceSource = "<b>" + "Price: " + "</b> " + cardItemList.get(position).getPrice();
        String productLongDescriptionSource = "<b>" + "Long Description: " + "</b> " + cardItemList.get(position).getLongDescription();
        String productReviewRatingSource = "<b>" + "Review Rating: " + "</b> " + cardItemList.get(position).getReviewRating();
        String productReviewCountSource = "<b>" + "Review Count: " + "</b> " + cardItemList.get(position).getReviewCount();
        String productInStockSource = "<b>" + "In Stock: " + "</b> " + cardItemList.get(position).getInStock();


        product_detail_textViewproductName.setText(Html.fromHtml(productNameSource));
        product_detail_textViewPrice.setText(Html.fromHtml(productPriceSource));

        product_detail_long_description.setText(Html.fromHtml(productLongDescriptionSource));
        product_detail_review_rating.setText(Html.fromHtml(productReviewRatingSource));
        product_detail_review_count.setText(Html.fromHtml(productReviewCountSource));
        product_detail_in_stock.setText(Html.fromHtml(productInStockSource));


        Picasso.get().load(cardItemList.get(position).getProductImageUrl()).fit().centerInside().into(product_detail_productimageView);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
