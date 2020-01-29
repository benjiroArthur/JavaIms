/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.controller;

import java.util.List;
import org.hibernate.Session;
import org.ims.inventorysystem.Connect;
import org.ims.model.ProductModel;

/**
 *
 * @author BENJIRO
 */
public class ProductController {
    public static Session sessionFactory(){
        return Connect.getSessionFactory().openSession();
        
    }
    
    //add new product to database
    public static ProductModel setNewProduct(String product_name, double price, int qty_in_stock, int min_stock){
        ProductModel product;
        try(Session session = sessionFactory()){
            System.out.println("Creating Product Object.....");
            session.beginTransaction();
            product = new ProductModel(product_name, price, qty_in_stock, min_stock);
            
            if(product.getQty_in_stock() <= product.getMin_stock()){
              product.setBelowMinStock(true);
            }
            else{
                product.setBelowMinStock(false);
            }
            System.out.println("Saving Product Information");
            session.save(product);
            
            System.out.println("Committing changes to Databse");
            session.getTransaction().commit();
            session.clear();
            System.out.println("Product saved Successfully");
            
        }
        return product;
    }
    
    //get all products
    public static List<ProductModel> getAllProduct(){
        List<ProductModel> products;
        try(Session session = sessionFactory()){
            session.beginTransaction();
            products = session.createQuery("from ProductModel").list();
            session.clear();
        }
        return products;
    }
    
    public static List<ProductModel> getProductByName(String pName){
        List<ProductModel> products;
        try(Session session = sessionFactory()){
            session.beginTransaction();
            products = session.createQuery("from ProductModel p where p.product_name like '%"+pName+"%'").list();
            session.clear();
        }
        return products;
    }
    
    public static ProductModel getProductById(long id){
        ProductModel product;
        try(Session session = sessionFactory()){
            session.beginTransaction();
            product = session.find(ProductModel.class, id);
            session.clear();
        }
        return product;
    }
    
    public static void updateProduct(ProductModel product){
        
        try (Session session = sessionFactory()) {
            session.beginTransaction();
            if(product.getQty_in_stock() <= product.getMin_stock()){
                product.setBelowMinStock(true);
            }else{
                product.setBelowMinStock(false);
            }
             System.out.println("Updating Product.....");
            session.update(product);
             System.out.println("Committing changes to database.....");
            session.getTransaction().commit();
            System.out.println("Product Updated successfully");
            session.clear();
        }
        
       
        
    }
    
    public static void deleteProduct(ProductModel product){
        try (Session session = sessionFactory()) {
            session.beginTransaction();
            System.out.println("Deleting Product.....");
            session.delete(product);
            System.out.println("Committing changes to database.....");
            session.getTransaction().commit();
            System.out.println("Product Deleted successfully");
            session.clear();
            
           
        }
        
    }
    
    public static List<ProductModel> outOfStock(){
       List<ProductModel> products;
        
        try (Session session = sessionFactory()) {
            session.beginTransaction();
            
            products = session.createQuery("FROM Product P Where P.InStock = 0").list();
            
            session.clear();
        }
       
       return products;
    }
    
    public static List<ProductModel> lowStock(){
        
        List<ProductModel> products;
        try (Session session = sessionFactory()) {
            session.beginTransaction();
            products =  session.createQuery("FROM Product P Where P.BelowMinStock = :value").setParameter("value", true).list();
            
            session.clear();
        }
        return products;
        
    }
    
}
