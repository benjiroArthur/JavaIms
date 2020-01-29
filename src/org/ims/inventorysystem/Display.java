/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.inventorysystem;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.ims.model.ProductModel;
import org.ims.views.ProductViews;

/**
 *
 * @author BENJIRO
 */
public class Display {
    private static final  Scanner Input = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    
    
    public void pr(String msg){
        //takes string ans display it on the same line
        System.out.print(msg);
    }
     public void prln(String msg){
         //takes string ans display it on the next line
        System.out.println(msg);
    }
     
     //takes a string and verify if the user actually entered something
     public static String mkWd(int length,String word){
        int numberOfSpacess;
        String  space = "";
        
        //check if string is greater than length
        if(word.length() > length){
            word  =  word.substring(0, length-2);
        }
        
        numberOfSpacess = length - word.length();
        
        for (int i = 0; i < numberOfSpacess; i++) {
            space +=  " ";
        }
        return  word + space;
    }
     
     public static String mkWdCnt(int length,String word){
        int numberOfSpacess;
        String  space = "";
        if(word.length() > length){
            word  =  word.substring(0, length-2);
        }
        
        numberOfSpacess = length  - word.length();
        
        if(numberOfSpacess % 2 == 0)
        {
            
            for (int i = 0; i < numberOfSpacess/2; i++) {
            space +=  " ";
            }
            return  space + word + space;
        }
        
        numberOfSpacess-=1;
        numberOfSpacess/=2;
        
        for (int i = 0; i < numberOfSpacess; i++)
        {
            space +=  " ";
        }
        
        
        return  space + word + space + " ";
    }
    
    public static String mkWdEnd(int length,String word){
        int numberOfSpacess;
        String  space = "";
        
        if(word.length() > length){
            word  =  word.substring(0, length-2);
        }
        numberOfSpacess = length  - word.length();
        
        if(numberOfSpacess % 2 == 0)
        {
            
            for (int i = 0; i < numberOfSpacess/2; i++) {
            space +=  " ";
            }
            return  space  + space + word;
        }
        
        numberOfSpacess-=1;
        numberOfSpacess/=2;
        
        for (int i = 0; i < numberOfSpacess; i++)
        {
            space +=  " ";
        }
        
        
        return  space  + space + word + " ";
    }
    
    //display * around the text as borders
    public static String mkStar(int numberOfStar){
        
        String star = "";
        
        for (int i = 0; i < numberOfStar; i++) {
            star+="*";
        }
        
        return star;
        
    }
    
    public static String mkSmb(int numberOfStar,String symbol){
        
        String star = "";
        
        for (int i = 0; i < numberOfStar; i++) {
            star+=symbol;
        }
        
        return star;
        
    }
    
    
    //get product id without displaying it to the user
    public static long gtPId(List<ProductModel> products){
          
        ProductModel product;
        int No=0;
        int UserIn;
        
        ProductViews.showProducts(products);
        prnt("Select Product from the list.");
        
        UserIn = tkInt(1,products.size());
        UserIn-=1;
       return  products.get(UserIn).getId();
       
    }
    
    
    public static  void  prnt(String text){
        System.out.println("\t"+text);
    }
    
     
    public static  void  prnterr(String text){
        System.out.println("\t"+ANSI_RED+text+ANSI_RESET);
    }
    
    public static  void  prntsuc(String text){
        System.out.println("\t"+ANSI_GREEN+text+ANSI_RESET);
    }
    
    public static String tkString(String prompt){
       
        String option;
        Display.prnt(prompt);
        int pass =0;
        
       
        do{
            option = Input.nextLine();
            if(option.length()  > 2){
                pass=1;
            }
        }while(pass == 0);
            
        return option;
    }
    
    public static String tkString(){
        
        String input;
        int  result  = 0;
        do{
            input = Input.nextLine();

            
            if(input.length()  > 2){
                result=1;
            }
        }while(result == 0 );
            

        
        return input;
    }
    
    public static String tkStringOptional(){
        String option;
         option = Input.nextLine();
         return option;
    }
    
    public static int tkInt(String prompt){
        int option;
        Display.prnt(prompt);
        while(!Input.hasNextInt()){
             wrgInput();
//            prnte("************** Wrong Input **************");
            Input.next();
        }
        option = Input.nextInt();
        return option;
    }
    
    public static int tkInt(int min,int max){
        int wrn = 0;
        int UserIn;
        do{
            wrn++;
            if(wrn > 1){
               wrgInput();
            }
          UserIn = tkInt();  
        }while(UserIn < min || UserIn > max);
        return UserIn;
    }
    
    public static int tkInt(int min,int max,String message){
        int wrn = 0;
        int UserIn;
        do{
            wrn++;
            if(wrn > 1){
               wrgInput(message);
            }
          UserIn = tkInt();  
        }while(UserIn < min || UserIn > max);
        return UserIn;
    }
    
    public static int tkInt(){
        int option;
        while(!Input.hasNextInt()){
             wrgInput();
//            prnte("************** Wrong Input **************");
            Input.next();
        }
        option = Input.nextInt();
        return option;
    }
    
    public static double tkDouble(String prompt){
        double option;
        Display.prnt(prompt);
        while(!Input.hasNextDouble()){
             wrgInput();
//            prnte("************** Wrong Input **************");
            Input.next();
        }
        option = Input.nextDouble();
        return option;
    }
    
    public static double tkDouble(){
        double option;
        while(!Input.hasNextDouble()){
            wrgInput();
            Input.next();
        }
        option = Input.nextDouble();
        return option;
    }
    
    public  static void  wrgInput(){
        Display.prnterr("*****************  Wrong Input *****************");
    }
    
    public  static void  wrgInput(String message){
       prnterr("********* Wrong Input [" + message + "] *********");
    }

    public static void ntSupp() {
        prnt("Not supported yet.");
    }
    
    public static void displayColValue(List<ColValue> namesAndValues,String Heading){
        
        ColValue nameAndValue;
        
        String space = Display.mkWd(3, "");
        String borderX = Display.mkStar(52);
        String borderY = Display.mkStar(2);
        
        Iterator<ColValue> iT = namesAndValues.iterator();
        
        prnt("\n");
        prnt(borderX);
        prnt(borderY + Display.mkWdCnt(48,Heading) + borderY);
        prnt(borderX);
        
            while(iT.hasNext()){
                nameAndValue = iT.next();
                prnt(borderY    +   space   +   Display.mkWd(20, nameAndValue.getName())          +   borderY  + Display.mkWdCnt(23, nameAndValue.getValue())          + borderY);  
            }
        
        prnt(borderX);
        prnt("\n");
    }
     
    
    public static void printMessage(String message){
        
        String borderX = Display.mkStar(60);
        String borderY = Display.mkStar(2);
        message = Display.mkWdCnt(56, message);
        
       
        prnt("\n");
        prnt(borderX);
        prnt(borderY + message + borderY);
        prnt(borderX);
        prnt("\n");
        
        
    }

    public static void printErrorMessage(String message) {
        
        String borderX = Display.mkStar(60);
        String borderY = Display.mkStar(2);
        message = Display.mkWdCnt(56, message);
        
       
        prnterr("\n");
        prnterr(borderX);
        prnterr(borderY + message + borderY);
        prnterr(borderX);
        prnterr("\n");
        
    }
    
    public static void printSuccessMessage(String message) {
        
        String borderX = Display.mkStar(60);
        String borderY = Display.mkStar(2);
		//make word center
        message = Display.mkWdCnt(56, message);
        
       
        prntsuc("\n");
        prntsuc(borderX);
        prntsuc(borderY + message + borderY);
        prntsuc(borderX);
        prntsuc("\n");
        
    }
     
}
