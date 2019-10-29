package com.example.sams_challenge;

import java.io.Serializable;

public class Product implements Serializable {

    private String productName;
    private String shortDescription;
    private String price;
    private String productImageUrl;
    private String longDescription;
    private double reviewRating;
    private int reviewCount;
    private String inStock;

    public Product(String productName, String shortDescription, String price, String productImageUrl, String longDescription, double reviewRating, int reviewCount, String inStock) {
        this.productName = productName;
        this.shortDescription = shortDescription;
        this.price = price;
        this.productImageUrl = productImageUrl;
        this.longDescription = longDescription;
        this.reviewRating = reviewRating;
        this.reviewCount = reviewCount;
        this.inStock = inStock;
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

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(double reviewRating) {
        this.reviewRating = reviewRating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}
