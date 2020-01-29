/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.inventorysystem;

import org.ims.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author BENJIRO
 */
public class Connect {
    private static final SessionFactory sessionFactory;
    
    static {
        try{
            
             sessionFactory = new Configuration()
                                        .configure("hibernate.cfg.xml")
                                        .addAnnotatedClass(ProductModel.class)
                                        .addAnnotatedClass(OrderModel.class)
                                        .addAnnotatedClass(UserModel.class)
                                        .addAnnotatedClass(SalesModel.class)
                                        .buildSessionFactory();
  
        }catch(Throwable t){
            System.err.println("SessionFactory creation failed." + t);
            throw new ExceptionInInitializerError(t);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    public static void shutdown(){
        getSessionFactory().close();
    }
}
