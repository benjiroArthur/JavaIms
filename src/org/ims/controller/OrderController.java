/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.controller;

import java.util.List;
import org.hibernate.Session;
import org.ims.inventorysystem.Connect;
import org.ims.model.OrderModel;

/**
 *
 * @author BENJIRO
 */
public class OrderController {
    public static Session sessionFactory(){
        return Connect.getSessionFactory().openSession();
    }
    public static OrderModel makeOrder(OrderModel order){
        
        try(Session session = sessionFactory()){
            session.beginTransaction();
          session.save(order);
          session.getTransaction().commit();
          session.clear();
          session.close();
        }
       return order;
    }
    public static List<OrderModel> getAllOrders(){
        List<OrderModel> orders;
        try(Session session = sessionFactory()){
            session.beginTransaction();
            orders = session.createQuery("from OrderModel").list();
            session.clear();
            session.close();
        }
        return orders;
    }
}
