package com.springc.spring.ProductEntity;

public class Product {
    private String code;
    private String name;
    private String originCountry;
    private double price;
    private double cost;
    private String imageUrl;
    private String description;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOriginCountry() {
        return originCountry;
    }
    public void setOriginCountry(String orginCountry) {
        this.originCountry = originCountry;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getCost() {
        return cost;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Product(String code, String name, String orginCountry, double price, double cost, String imageUrl,
            String description) {
        this.code = code;
        this.name = name;
        this.originCountry = orginCountry;
        this.price = price;
        this.cost = cost;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Product(){
        
    }
    @Override
    public String toString() {
        return "Product [code=" + code + ", name=" + name + ", originCountry=" + originCountry + ", price=" + price
                + ", cost=" + cost + ", imageUrl=" + imageUrl + ", description=" + description + "]";
    }


    
    
}
