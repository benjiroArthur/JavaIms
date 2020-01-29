/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author BENJIRO
 */
@Entity
@Table
public class ProductModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    private String product_name;
    
    @Column
    private double price;
    
    @Column
    private int qty_in_stock;
    
    @Column
    private int min_stock;
    
    @Column
    private boolean below_min_stock;

    public ProductModel() {
    }
    
    public ProductModel(String product_name, double price, int qty_in_stock, int min_stock) {
        this.product_name = product_name;
        this.price = price;
        this.qty_in_stock = qty_in_stock;
        this.min_stock = min_stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty_in_stock() {
        return qty_in_stock;
    }

    public void setQty_in_stock(int qty_in_stock) {
        this.qty_in_stock = qty_in_stock;
    }

    public int getMin_stock() {
        return min_stock;
    }

    public void setMin_stock(int min_stock) {
        this.min_stock = min_stock;
    }
    
     public boolean isBelowMinStock() {
        return below_min_stock;
    }

    public void setBelowMinStock(boolean below_min_stock) {
        this.below_min_stock = below_min_stock;
    }
    
    
}
