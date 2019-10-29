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
    private ArrayList<Product> mcardItemList;
    private OnCardListener mOnCardListener;

    public CardAdapter(Context context, ArrayList<Product> cardItemList, OnCardListener onCardListener) {

        mcontext = context;
        mcardItemList = cardItemList;
        mOnCardListener = onCardListener;

    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mcontext).inflate(R.layout.card_item, parent, false);
        return new CardViewHolder(v, mOnCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        Product product = mcardItemList.get(position);

        String productImageURL = product.getProductImageUrl();
        String productName = product.getProductName();
        String productShortDescription = product.getShortDescription();
        String productPrice = product.getPrice();

        String productNameSource = ConfigValues.PRODUCT_NAME_TITLE + productName;
        String productShortDescriptionSource = ConfigValues.PRODUCT_SHORT_DESCRIPTION_TITLE + productShortDescription;
        String productPRiceSource = ConfigValues.PRODUCT_PRICE_TITLE + productPrice;

        holder.textViewproductName.setText(Html.fromHtml(productNameSource));
        holder.textViewshortDescription.setText(Html.fromHtml(productShortDescriptionSource));
        holder.textViewPrice.setText(Html.fromHtml(productPRiceSource));
        Picasso.get().load(productImageURL).fit().centerInside().into(holder.productimageView);

    }

    @Override
    public int getItemCount() {
        return mcardItemList.size();
    }

    public interface OnCardListener {

        void onCardClick(int position);
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView productimageView;
        public TextView textViewproductName;
        public TextView textViewshortDescription;
        public TextView textViewPrice;
        OnCardListener onCardListener;


        public CardViewHolder(@NonNull View itemView, OnCardListener onCardListener) {
            super(itemView);

            productimageView = itemView.findViewById(R.id.product_image_view);
            textViewproductName = itemView.findViewById(R.id.text_view_product_name);
            textViewshortDescription = itemView.findViewById(R.id.text_view_short_description);
            textViewPrice = itemView.findViewById(R.id.text_view_price);
            this.onCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onCardListener.onCardClick(getAdapterPosition());


        }
    }


}
