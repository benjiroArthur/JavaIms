/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.controller;

import org.hibernate.Session;
import org.ims.inventorysystem.Connect;
import org.ims.model.SalesModel;

/**
 *
 * @author BENJIRO
 */
public class SalesController {
    public static Session sessionFactory(){
        return Connect.getSessionFactory().openSession();
    }
    
    public static SalesModel addSales(SalesModel sales){
        try(Session session = sessionFactory()) {
           session.beginTransaction();
           session.save(sales);
           session.getTransaction().commit();
           session.clear();
        } 
        return sales;
    }
}
