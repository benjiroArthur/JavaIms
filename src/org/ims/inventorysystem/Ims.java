/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.inventorysystem;


//import java.math.RoundingMode;
//import java.text.DecimalFormat;
import org.ims.controller.UserController;
import org.ims.model.UserModel;
import org.ims.views.OrderViews;
import org.ims.views.ProductViews;
import org.ims.views.UserViews;




/**
 *
 * @author BENJIRO
 */
public class Ims {
     private static void prnt(String text){
        Display.prnt(text);
    }
 
    public static boolean loginView(){
        String  username;
        String password;
        UserModel user;
        Display.printMessage("*********** LOGIN FORM **********");


        prnt("Enter Username: ");
        username = Display.tkString();
        password = Display.tkString("Enter your password: ");
        
        user = UserController.login(username, password);
        
        if(user != null){
            Display.printSuccessMessage("Wellcome " + user.getFull_name());
        }else{
            Display.printErrorMessage("Wrong Username or Password.");
        }
        
        return user != null;
    }
    
    //main view for user to select what to do
    public static void mainView(){
        
        prnt("Select One of these options: ");
                prnt("\t1) Users");
                prnt("\t2) Perform Transactions");
                prnt("\t3) Products");
                prnt("\t0) Close  Application.");
        }
    
    public static void mainViewRun(int option){
        
        switch(option){
            
            case 1:
                usersView();
                break;
            case 2:
                OrderViews.getOrderModelsView();
                break;
            case  3:
                productView();
                break;
            case 0:
                System.out.println("****************GOOD BYE**************");
        }
        
    }
    
    public static void usersView(){
        
        prnt("Choose one of the following.");
                prnt("1) Add User.");
                prnt("2) Update a User.");
                prnt("3) Delete a User.");
                prnt("4) Find a User.");
                prnt("5) Show all Users.");
                prnt("0) Back");
        
        userViewRun(Display.tkInt(0,5));
       
    }
    
    public static void userViewRun(int option){
        
        switch(option){
            
            case 1:
                UserViews.addUserView();
                break;
            case 2:
                UserViews.updateUserView();
                break;
            case 3:
                UserViews.deleteUserView();
                break;
            case  4:
                UserViews.findUserView();
                break;
            case  5:
                UserViews.showAllUsersView();
                break;
            case 0:
                mainView();
                break;
          
        }
        
    }
    
    public static void productView(){
        
        prnt("Choose one of the following.");
                prnt("1) Add Product.");
                prnt("2) Update a Product.");
                prnt("3) Delete a Product.");
                prnt("4) Find a Product.");
                prnt("5) List of Low Stock Products.");
                prnt("6) List of Product out of stock.");
                prnt("0) Back");
       
        productViewRun(Display.tkInt(0,6));
    }
    
    public static void productViewRun(int  option){
        switch (option){
            
            case 1:
                ProductViews.setProductView();
                break;
            case  2:
                ProductViews.updateProductView();
                break;
            case 3:
                ProductViews.deleteProductView();
                break;
            case 4:
                ProductViews.findProductByNameView();
                break;
            case 5:
                ProductViews.findLowStockProductsView();
                break;
                
            case 6:
                ProductViews.findOutOfStockProductsView();
                break;
                
          
            case 0:
                mainView();
                break;
        }
        
    }
    
}
