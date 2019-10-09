package com.example.demo;

import javax.persistence.*;
import java.util.Set;

//create table Book(
//        id INTEGER PRIMARY KEY,
//        sku VARCHAR(50),
//        title VARCHAR(50),
//        author VARCHAR(50),
//        description VARCHAR(50),
//        price NUMERIC(4,2),
//        is_in_stock int(11) DEFAULT NULL
//        );

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookID;
    private String sku = "";
    private String title = "";
    private String description = "";
    private double price = 0.0;
    private int isInStock = 0;
    private Author author;

    public Book() {
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIsInStock() {
        return isInStock;
    }

    public void setIsInStock(int isInStock) {
        this.isInStock = isInStock;
    }
}
