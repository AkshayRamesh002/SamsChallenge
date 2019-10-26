package com.example.sams_challenge;

public class CardItem {

    private String productName;
    private String shortDescription;
    private String price;
    private String productImageUrl;

    public CardItem(String productName, String shortDescription, String price, String productImageUrl) {
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.price = price;
        this.productImageUrl = productImageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }
}
