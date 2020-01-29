/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.ims.controller.OrderController;
import org.ims.controller.ProductController;
import org.ims.controller.SalesController;
import org.ims.inventorysystem.Display;
import org.ims.model.OrderModel;
import org.ims.model.ProductModel;
import org.ims.model.SalesModel;

/**
 *
 * @author BENJIRO
 */
public class OrderViews {
    private static Object SalesViewsView;
    private static void prnt(String text){
        Display.prnt(text);
    }
    private static void prnte(String text){
        Display.prnterr(text);
    }
    public static OrderModel getOrderView(){
        OrderModel order = null;
        
        ProductModel product = ProductViews.getFindProductByNameView();
        
        if (product != null){
            
            double unitPrice = product.getPrice();

            if(product.getQty_in_stock()== 0){
    
                Display.printErrorMessage("Sorry Out of Stock.");

            }else{
                order = new OrderModel();
                prnt("Enter Quantity");

            int quantity = Display.tkInt(1,product.getQty_in_stock(),"Sorry there is only " + product.getQty_in_stock() + " in stock. Enter Quantity.");

                   order.setUnit_price(unitPrice);
                   order.setProduct(product);
                   order.setQuantity(quantity);
                   order.setAmount(unitPrice * quantity);
                   

                   product.setQty_in_stock(product.getQty_in_stock()- quantity);
                  ProductController.updateProduct(product);


            OrderController.makeOrder(order);

            return order;

            }
            
        }
        
        
        return order;
    }
    
    

    public static void getOrderModelsView(){
         SalesModel sale = new SalesModel();
        OrderModel sItem;
        List<OrderModel> items = new ArrayList() ;
        String  input;
        Date date= new Date();
        double total=0;
        double unitPrice;
        int quantity;
       
        ProductModel product;
        int check;
        
        do{
            sItem = getOrderView();
            if(sItem != null){
                items.add(sItem);
            }
         
        Display.prnt("1) Add another");
        Display.prnt("2) Continue");
        check = Display.tkInt();
        }
        while(check == 1);
      
        if(items.isEmpty()){
            
            Display.printErrorMessage("Transaction Canceled.");
        
        }else{
            
            sale.setOrders(items);
            sale.setDate(date);
            sale.setProduct(items.size());
        
            Iterator<OrderModel> it = items.iterator();
        
            while(it.hasNext()){
                total += it.next().getAmount();
            }

            sale.setTotal(total);

            sale = SalesController.addSales(sale);
            Display.printSuccessMessage("Transaction Successfull.");

            SalesViews.showSale(sale);
        }
      
    }
    
}
