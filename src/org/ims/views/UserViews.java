/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.ims.controller.UserController;
import org.ims.inventorysystem.ColValue;
import org.ims.inventorysystem.Display;
import static org.ims.inventorysystem.Display.mkWd;
import static org.ims.inventorysystem.Display.prnt;
import static org.ims.inventorysystem.Display.tkInt;
import org.ims.model.UserModel;


/**
 *
 * @author BENJIRO
 */
public class UserViews {
    public static void showUser(UserModel user){
        
        List<ColValue> colValues = new ArrayList();
        String Heading = "User Details";
        String id = String.valueOf(user.getId());
        String name = user.getFull_name();
        String userName = String.valueOf(user.getUsername());
      
        ColValue nvId = new ColValue("ID",id);
        colValues.add(nvId);
        
        
        ColValue nvName = new ColValue("NAME",name);
        colValues.add(nvName);
        
        ColValue nvUserName = new ColValue("USERNAME",userName);
        colValues.add(nvUserName);


        
        
        Display.displayColValue(colValues,Heading );
  
    }
    
    public static void showUser(List<UserModel> users){
        
        UserModel user;
        int No=0;
        
        String space = Display.mkWd(3, "");
        String borderX = Display.mkStar(62);
        String borderY = Display.mkStar(2);
        String NO = Display.mkWdCnt(8, "NO");
        String NAME = Display.mkWdCnt(23, "NAME");
        String USERNAME = Display.mkWdCnt(23, "USERNAME");
  
        Iterator<UserModel> iT = users.iterator();


        prnt(borderX);
        prnt(borderY+ NO  + borderY +  NAME + borderY + USERNAME + borderY  );
        prnt(borderX);

        while(iT.hasNext()){
            No++;
            user = iT.next();
            prnt(borderY+ space+  mkWd(5,String.valueOf(No))  + borderY+ space +  mkWd(20, String.valueOf(user.getFull_name()))+ borderY + space  + mkWd(20,String.valueOf(user.getUsername()))+ borderY);
        }
        prnt(borderX);
        prnt("\n");
        
    }
    
    public static UserModel getUserByNameView(){
        UserModel user = null;
        List<UserModel> users;
        int choice;
        
        String name;
        prnt("Enter  the name of the user.");
        name = Display.tkString();
        
        users = UserController.getUserByName(name);

        
        if(users.size() >= 1){
            
            showUser(users);
            
            prnt("Select One.");
            
            choice = tkInt(1,users.size());
            
            
            choice -= choice;
            user = users.get(choice);
            
            return user;
        } else {
            //            user(s) found
            prnt("Nothing was found.");
            prnt("\t1. Try Again.");
            prnt("\t0. Cancel");
            
            switch(Display.tkInt(0,1)){
                case 1:
                    getUserByNameView();
                    break;
                         
            }
            
        }
        return user;
    }

    public  static void addUserView(){

        UserModel user;
        String last_name, first_name, other_name;  
        
        String userName; 
        
        String pasword; 
        
        Display.prnt("Enter Last Name of the User: ");    
        last_name = Display.tkString();
        
        Display.prnt("Enter First Name of the User: ");    
        first_name = Display.tkString();
        
        Display.prnt("Enter Other Names of the User if any, or leave it blank if user has none: ");    
        other_name = Display.tkStringOptional();
        
        Display.prnt("Enter Userlname of the User: ");
        userName = Display.tkString();
            
        
        Display.prnt("Enter Password of the User: ");
        pasword = Display.tkString();
        
        user  = UserController.addUser(last_name, first_name, other_name, userName, pasword);
        
        Display.printSuccessMessage("User Created  Successfully");

        showUser(user);
        
      
    }

    public static void updateUserView() {
        UserModel user;
        user = getUserByNameView();
        int action;
        
        prnt("Do you want to update the name");
        prnt("1) YES");
        prnt("2) NO");
        action = Display.tkInt(1,2);
        switch(action){
            case 1:
               user.setLast_name(Display.tkString("[Old Name = "+user.getLast_name()+"] Enter the new Last name."));
               user.setFirst_name(Display.tkString("[Old Name = "+user.getFirst_name()+"] Enter the new first name."));
               user.setOther_name(Display.tkString("[Old Name = "+user.getOther_name()+"] Enter the new Other name."));
               break;
        }
        
        prnt("Do you want to update the username");
        prnt("1) YES");
        prnt("2) NO");
        action = Display.tkInt(1,2);
        switch(action){
            case 1:
               user.setUsername(Display.tkString("[Old Name = "+user.getUsername()+"] Enter the new username."));
               break;
            case 2:
                prnt("Action Cancelled");
                break;
        }
        
        prnt("Do you want to update the password?");
        prnt("1) YES");
        prnt("2) NO");
        action = Display.tkInt(1,2);
        switch(action){
            case 1:
               user.setPassword(Display.tkString("[Old Name = "+user.getPassword()+"] Enter the new Password."));
               break;
             case 2:
                    prnt("Action Cancelled");
                    break;    
        }
        
        UserController.updateUser(user);
        
        Display.printSuccessMessage("User updated successfully.");
        
        showUser(user);
        
        prnt("Do you want to update another user?");
        prnt("1) YES");
        prnt("2) NO");
        action = Display.tkInt(1,2);
        switch(action){
            case 1:
               updateUserView();
               break;
                 case 2:
                    prnt("Action Cancelled");
                    break;
        }
    }

    public static void deleteUserView() {
        UserModel user = getUserByNameView();
        int UserIn;
        if(user != null){
            prnt("Do you realy want to delete "+user.getFull_name()+"?");
            prnt("1) YES");
            prnt("2) NO");
            UserIn = tkInt(1,2);
            switch(UserIn){
                case 1:
                   UserController.deleteUser(user); 
                   Display.printSuccessMessage("User deleted Successfully");
                   break;
                case 2:
                    prnt("Action Cancelled");
                    break;
            }
        }
        
        
        
        
        prnt("Do you want to delete another user?");
        prnt("1) YES");
        prnt("2) NO");
        UserIn = tkInt(1,2);
        switch(UserIn){
            case 1:
               deleteUserView();
               break;
            case 2:
                
                break;
        }
    }

    public static void findUserView() {
        int UserIn;
        UserModel user = getUserByNameView();
        if(user != null){
           showUser(user); 
        }
        prnt("Do you want find another user?");
        prnt("1) YES");
        prnt("2) NO");
        UserIn = tkInt(1,2);
        switch(UserIn){
            case 1:
               findUserView();
               break;
            case 2:
                break;
        }
        
    }

    public static void showAllUsersView() {
        
        List<UserModel> users = UserController.getAllUsers();
        
        showUser(users);
        
    }
    
}
