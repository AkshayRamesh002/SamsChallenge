package com.example.sams_challenge;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {


    private Context mcontext;
    private ArrayList<CardItem> mcardItemList;

    public CardAdapter(Context context, ArrayList<CardItem> cardItemList) {

        mcontext = context;
        mcardItemList = cardItemList;

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        CardItem cardItem = mcardItemList.get(position);

        String productImageURL = cardItem.getProductImageUrl();
        String productName = cardItem.getProductName();
        String productShortDescription = cardItem.getShortDescription();
        String productPrice = cardItem.getPrice();

        holder.textViewproductName.setText("Product Name: " + productName);
        holder.textViewshortDescription.setText("Short Description: " + Html.fromHtml(productShortDescription));
        holder.textViewPrice.setText("Price: " + productPrice);
        Picasso.get().load(productImageURL).fit().centerInside().into(holder.productimageView);

    }

    @Override
    public int getItemCount() {
        return mcardItemList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        public ImageView productimageView;
        public TextView textViewproductName;
        public TextView textViewshortDescription;
        public TextView textViewPrice;


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            productimageView = itemView.findViewById(R.id.product_image_view);
            textViewproductName = itemView.findViewById(R.id.text_view_product_name);
            textViewshortDescription = itemView.findViewById(R.id.text_view_short_description);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
        }
    }


}
