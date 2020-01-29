/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.ims.controller.ProductController;
import org.ims.inventorysystem.ColValue;
import org.ims.inventorysystem.Display;
import org.ims.model.ProductModel;
import static org.ims.inventorysystem.Display.mkWd;

/**
 *
 * @author BENJIRO
 */
public class ProductViews {

    private static void prnt(String text){
        Display.prnt(text);
    }
    private static void prnterr(String text){
        Display.prnterr(text);
    }
    
    public static void showProduct(ProductModel product){
        
        List<ColValue> colomnAndValue = new ArrayList();
        String Heading = "Product Details";
        
        String name = product.getProduct_name();
        String price = String.valueOf(product.getPrice());
        String inStock = String.valueOf(product.getQty_in_stock());
        String minStock = String.valueOf(product.getMin_stock());
        String id = String.valueOf(product.getId());
        
        ColValue nvId = new ColValue("ID",id);
        colomnAndValue.add(nvId);
        
        ColValue nvName = new ColValue("NAME",name);
        colomnAndValue.add(nvName);
        
        
        ColValue nvPrice = new ColValue("PRICE",price);
        colomnAndValue.add(nvPrice);
        
        ColValue nvInStock = new ColValue("IN STOCK",inStock);
        colomnAndValue.add(nvInStock);

        ColValue nvMinStock = new ColValue("MIN STOCK",minStock);
        colomnAndValue.add(nvMinStock);

        
        Display.displayColValue(colomnAndValue, Heading );
      
    }
    
    public static void showProducts(List<ProductModel> products){
        ProductModel product;
        int No=0;
        
        String space = Display.mkWd(3, "");
        String borderX = Display.mkStar(82);
        String borderY = Display.mkStar(2);
        String NO = Display.mkWdCnt(8, "NO");
        String NAME = Display.mkWdCnt(23, "NAME");
        String PRICE = Display.mkWdCnt(13, "PRICE");
        String INSTOCK = Display.mkWdCnt(13, "IN STOCK");
        String MINSTOCK = Display.mkWdCnt(13, "MIN STOCK");
       
        
        
        
        Iterator<ProductModel> iT = products.iterator();


        prnt(borderX);
        prnt(borderY+ NO  + borderY +  NAME + borderY + PRICE + borderY + INSTOCK + borderY  + MINSTOCK + borderY );
        prnt(borderX);

        while(iT.hasNext()){
            No++;
            product = iT.next();
            prnt(borderY+ space+  mkWd(5,String.valueOf(No))  + borderY+ space +  mkWd(20, String.valueOf(product.getProduct_name()))+ borderY + space  + mkWd(10, "GHC "+ String.valueOf(product.getPrice()))+ borderY+ space + mkWd(10, String.valueOf(product.getQty_in_stock())) + borderY+ space + mkWd(10, String.valueOf(product.getMin_stock())) + borderY);

        }
        prnt(borderX);
        prnt("\n");
    }
    
    public static void findProductByNameView(){
        
        List products;
        ProductModel product;
        String name;
        long id;
        prnt("Enter  the name of the product.");
        name = Display.tkString();
        products = ProductController.getProductByName(name);
        
        if(products.size() >= 1){
            id = Display.gtPId(products);
            product = ProductController.getProductById(id);


            showProduct(product);
        
        }else{
            prnt("Nothing was found.");
            prnt("\t1. Try Again.");
            prnt("\t0. Cancel");
            
            switch(Display.tkInt(0,1)){
                case 1:
                    findProductByNameView();
                    break;
                         
            }
           
        }
        
        
    }
    
    
    public static ProductModel getFindProductByNameView(){
        List products;
        ProductModel product = null;
        String name;
        long id;
        prnt("Enter  the name of the product.");
        name = Display.tkString();
        products = ProductController.getProductByName(name);
        
        if(products.size() >= 1){
            
            id = Display.gtPId(products);
           
            product = ProductController.getProductById(id);
               
        }else{
            prnt("Nothing was found.");
            prnt("   1. Try Again.");
            prnt("   0. Cancel");
            
            switch(Display.tkInt(0,1)){
                case 1:
                    getFindProductByNameView();
                    break;
                         
            }
           
        }
        return product;
        
    }
    
    public static void setProductView(){
        
        
       String product_name,message ;
       double price;
       int inStock,minStock;
       int state;
    
       
       message = "Enter product Name: ";
       
       prnt(message);
      
       product_name = Display.tkString();
       
       
       message = "Enter product Price: ";
//       prnt(message);
//       while(!input.hasNextDouble()){
//           Display.wrgInput(message);
//           input.next();
//       }
//       price = input.nextDouble();
          price = Display.tkDouble(message);

           
       message = "Enter quantity in stock: ";
//       prnt(message);
//       while(!input.hasNextInt()){
//           Display.wrgInput(message);
//           input.next();
//       }
//       inStock = input.nextInt();
//       
        inStock = Display.tkInt(message);
       
       
       message = "Enter minimum quantity: ";
//       prnt(message);
//       while(!input.hasNextInt()){
//           Display.wrgInput(message);
//           input.next();
//       }
//       minStock = input.nextInt();
       minStock = Display.tkInt(message);
       
      ProductModel product = (ProductModel) ProductController.setNewProduct(product_name, price, inStock, minStock);
        
        prnt("Product Created successfully.");
      
        showProduct(product);
        prnt("Do you want to add another product?");
        prnt("1) YES");
        prnt("2) NO");
        state = Display.tkInt(1,2);
        switch(state){
            case 1:
                setProductView();
               break;
        }
      
    }
    
    public static void updateProductView(){
        
        ProductModel product;
        int choice;
        
        product = getFindProductByNameView();
        
        prnt("Do you want to update the name");
        prnt("1) YES");
        prnt("2) NO");
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
               product.setProduct_name(Display.tkString("[Old Name = "+product.getProduct_name()+"] Enter the new name."));
               
               break;
        }
        
        prnt("Do you want to update the price");
        prnt("1) YES");
        prnt("2) NO");
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
               product.setPrice(Display.tkDouble("[Old Price = GHC "+product.getPrice()  +"] Enter the new price."));
              
               break;
        }
        
        prnt("Do you want to update Min Stock");
        prnt("1) YES");
        prnt("2) NO");
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
               product.setMin_stock(Display.tkInt("[Old Min Stock = " + product.getMin_stock()+"] Enter the new Min Stock."));
              
               break;
        }
        
        
        prnt("Do you want to update In Stock");
        prnt("1) YES");
        prnt("2) NO");
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
               product.setQty_in_stock(Display.tkInt("[Old In Stock = " + product.getQty_in_stock()+"] Enter the new In Stock."));
               break;
        }
       
        ProductController.updateProduct(product);
        
        
        Display.printSuccessMessage("Product Updated Successfully");
        
        showProduct(product);
        
        prnt("Do you want to update another Product?");
        prnt("1) YES");
        prnt("2) NO");
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
                updateProductView();
                break;
            case 2:
                break;
        }
        
    }

    public static void deleteProductView() {
        ProductModel product;
        int choice;
        
        product = getFindProductByNameView();
        
        
        
        prnt("Do you realy want to delete this product. [ name = " + product.getProduct_name()+ " ]" );
        prnt("1) YES");
        prnt("2) NO");
        
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
                try{
                    ProductController.deleteProduct(product);
                    Display.printSuccessMessage("Product Deleted Successfully.");
                }catch(Exception  ex){
                    Display.printErrorMessage("Sorry this Product can not be deleted.");

                }
                
                

                break;
            case 2:
                break;
        }
        
        
        prnt("Do you want to delete another Product?");
        prnt("1) YES");
        prnt("2) NO");
        choice = Display.tkInt(1,2);
        switch(choice){
            case 1:
                deleteProductView();
                break;
            case 2:
                break;
        }
        
//        Display.ntSupp();
    }

    public static void findLowStockProductsView() {
        
        List<ProductModel>  products = ProductController.lowStock();
        
        if(products.isEmpty()){
            Display.printSuccessMessage("No product found.");
        }else{
           Display.printErrorMessage(products.size() + " product found.");
           showProducts(products); 
        }
    }

    public static void findOutOfStockProductsView() {
        
        List<ProductModel>  products = ProductController.outOfStock();
        
        if(products.isEmpty())
        {
            Display.printSuccessMessage("No product found.");
        }else
        {
           Display.printErrorMessage(products.size() + " product found.");
           showProducts(products); 
        }
        
      
    }
}
