/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.controller;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ims.inventorysystem.Connect;
import org.ims.model.UserModel;


/**
 *
 * @author BENJIRO
 */
public class UserController {
    //create instance of the session factory
     public static Session sessionFactory(){
        return Connect.getSessionFactory().openSession();
        
    }
     //mwthod to handle login request
    public static UserModel login(String username, String password){
        UserModel user = null;
        Session session =  sessionFactory();
        session.beginTransaction();
        user = (UserModel)session.createQuery("From UserModel u where u.username = '"+username+"' And u.password = '"+password+"'").uniqueResult();
        session.clear();
        session.close();
       
        return user;
        
       
    }
    
    //method to check if username exist
    public boolean usernameExist(String username){
        Session session = sessionFactory();
        List <UserModel> users;
        session.beginTransaction();
        users = session.createQuery("from UserModel u where u.username = '"+username+"'").list();
        session.clear();
        session.close();
        
        return users.size() == 1;
    }
    
    //method to add user records to the database
    public static UserModel addUser(String last_name, String first_name, String other_name, String username, String pasword){
        
        UserModel user;
        try (Session session = sessionFactory()) {
            
            session.beginTransaction();
            
            user = new UserModel(last_name, first_name, other_name, username, pasword);
            session.save(user);
            session.getTransaction().commit();
            session.clear();
            session.close();
        }
                
        
        return user;
    }
    
    public static List<UserModel> getUserByName(String name){
        List<UserModel> user;
        try( Session session = sessionFactory()){
            session.beginTransaction();
          user  =   session.createQuery("From UserModel u where u.first_name like '%"+name+"%' or u.other_name like '%"+name+"%' or u.last_name like '%"+name+"%'").list();
          session.clear();
          session.close();
        }
        
        return user;
                
    }
    
    public static UserModel getUserById(long id){
        UserModel user;
         try( Session session = sessionFactory()){
            session.beginTransaction();
            user  = (UserModel)  session.createQuery("From UserModel u where u.id = '"+id+"'");
            session.clear();
            session.close();
        }
         return user;
    }
    //List all users
    public static List<UserModel> getAllUsers(){
        
        List<UserModel> users;
        try(Session session = sessionFactory()){
            session.beginTransaction();
         users = session.createQuery("from UserModel").list();
         session.clear();
         session.close();
        }
        return users;
    }
    //update user info
    public static void updateUser(UserModel user){
        try(Session session = sessionFactory()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.clear();
            session.close();
            System.out.println("User Records Updated Successfully");
        }
    }
    
    public static void deleteUser(UserModel user){
        try(Session session = sessionFactory()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.clear();
            session.close();
            System.out.println("User Records Deleted Successfully");
        }
    }
    
    public static UserModel logIn(String username, String password){
        
        UserModel user;
        try (Session session = sessionFactory()) {
            user = (UserModel) session.createQuery("FROM UserModel u  WHERE u.username = :username And u.password = :password").setParameter("username", username).setParameter("password", password).uniqueResult();
            session.clear();
            session.close();
        }
        return user;
    }
}

