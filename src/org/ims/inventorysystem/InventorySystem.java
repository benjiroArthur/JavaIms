/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.inventorysystem;

/**
 *
 * @author BENJIRO
 */
public class InventorySystem {
    private  static boolean runProgram = true;
    public static void main(String[] args) {
        System.out.println("-----------WELCOME-----------");
        Ims ims = new Ims();
        Connect.getSessionFactory();
        
        int selected;
        boolean logedIn;

            
        do{
            logedIn = ims.loginView();
            
        }while(!logedIn);


        do{

            ims.mainView();

           selected = Display.tkInt(0, 3);
           switch(selected){
               
               case 0:
                   closeApp();
                   break;
               default:
                   ims.mainViewRun(selected);  
           }
           
            }while(runProgram);
         
         
       
    }
    
    public static void closeApp(){
        runProgram = false;
        System.exit(0);
    }
    
    }
    
    

