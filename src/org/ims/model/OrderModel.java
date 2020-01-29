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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author BENJIRO
 */
@Entity
@Table
public class OrderModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    
    @ManyToOne
    private ProductModel product;
    
    @Column
    private double unit_price;
    
    @Column
    private int quantity;
    
    @Column
    private double amount;
   
    
    @ManyToOne
    private SalesModel sales;
    
    //default constructor
    public OrderModel() {
    }
    
    //constructor with parameters
    public OrderModel(ProductModel product, double unit_price, int qty, double amount) {
        this.product = product;
        this.unit_price = unit_price;
        this.quantity = qty;
        this.amount = amount;
    }

    
    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public SalesModel getSale() {
        return sales;
    }

    public void setSale(SalesModel sales) {
        this.sales = sales;
    }
    
    
}
