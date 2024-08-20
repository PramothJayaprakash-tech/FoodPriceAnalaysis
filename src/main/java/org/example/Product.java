package org.example;

public class Product {

    String productName;
    Long productPrice;

    public Product(String productName, Long productPrice){

        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }
}
