/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ims.views;

import java.util.Iterator;
import java.util.List;
import org.ims.inventorysystem.Display;
import org.ims.model.OrderModel;
import org.ims.model.SalesModel;

/**
 *
 * @author BENJIRO
 */
public class SalesViews {
    private static void prnt(String text){
          Display.prnt(text);
     }
    
    public static void sellItemView(){ 
        
        List<OrderModel> items;
     
    }

    static void showSale(SalesModel sale) {   
         int no=0,quantity;
         String  name;
         double unitPrice,grandTotal;
         OrderModel SI;
      
        int No=0;
        int UserIn; 
        String space = Display.mkWd(3, "");
        String borderX = Display.mkStar(82);
        String borderY = Display.mkStar(2);
        String NO = Display.mkWdCnt(8, "NO");
        String NAME = Display.mkWdCnt(23, "NAME");
        String PRICE = Display.mkWdCnt(13, "UNIT PRICE");
        String QUANTITY = Display.mkWdCnt(13, "QUANTITY");
        String SUBTOTAL = Display.mkWdCnt(13, "AMOUNT");
        String DATE = Display.mkWd(10, "DATE");
        String SALEID = Display.mkWd(10, "SALE ID");
       
        
        
        
        Iterator<OrderModel> iT = sale.getOrders().iterator();


        

        prnt(borderX);
        prnt(borderY + space  + SALEID  +  space  + Display.mkWd(62, String.valueOf(sale.getId()))    +   borderY);
        prnt(borderY + space  +  DATE    +  space  + Display.mkWd(62, String.valueOf(sale.getDate()))  +   borderY);
        prnt(borderX);
        prnt(borderY+ NO  + borderY +   NAME + borderY +  PRICE + borderY +  QUANTITY + borderY + SUBTOTAL + borderY );
        prnt(borderX);

        
        while(iT.hasNext()){
            No++;
            SI = iT.next();
            
            prnt(borderY+ space+  Display.mkWd(5,String.valueOf(No))  + borderY+ space +  Display.mkWd(20, String.valueOf(SI.getProduct().getProduct_name()))+ borderY + space  + Display.mkWd(10,"GHC "+ String.valueOf(SI.getUnit_price()))+ borderY+ space + Display.mkWd(10, String.valueOf(SI.getQuantity())) + borderY+ space + Display.mkWd(10, String.valueOf(SI.getAmount())) + borderY);
            
            if(No != sale.getOrders().size()){
               prnt(borderY+Display.mkSmb(78, "-")+borderY); 
            }
         
        }
        prnt(borderX);
        prnt(borderY  + Display.mkWdEnd(78, borderY  + space  +"TOTAL"+ space + "GHC " + String.valueOf(sale.getTotal()) + space)    +   borderY);
        prnt(borderX);

        
    }
    
}
