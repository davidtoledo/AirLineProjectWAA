package net.tolledo.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.tolledo.domain.Order;
import net.tolledo.domain.Orderline;
import net.tolledo.domain.Product;
import net.tolledo.domain.User;
import net.tolledo.util.AppUtil;
import net.tolledo.webservices.OrderWSClient;
import net.tolledo.webservices.ProductWSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    @Autowired
    private OrderWSClient orderService;
    
    @Autowired
    private ProductWSClient productService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("orders", orderService.getAllOrder());
        return "orders";
    }
    
    @RequestMapping("order/delete/{id}")
    public String delete(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.POST)
    public String placeOrder(@RequestParam int [] orderPrd, 
                             @RequestParam int [] orderQty,
                             Model model) {
        
        System.out.println("*******************************");
        System.out.println(" ==   CREATING A NEW ORDER   ==");
        System.out.println("*******************************");
        
        // Retreiving the logged user data
        User user = AppUtil.getUserInSpringSecuritySession();
        
        // Creating a new Order
        Order order = new Order();
        order.setOrderDate(new Date());
        order.setPerson(user.getPerson());
        
        // Creating Order Lines
        List <Orderline> orderLines = new ArrayList <> ();
        Orderline ol;
        Product product;
        boolean saveThisOrder = false;
        for (int i=0; i < orderQty.length; i++) {

            System.out.println("READING PRODUCT = " + orderPrd[i]);
            System.out.println("READING QTY = " + orderQty[i]);
            
            if (orderQty[i] > 0) {
                product = productService.getProductById(orderPrd[i]);
            
                ol = new Orderline();
                ol.setPrice(product.getPrice());
                ol.setOrder(order);
                ol.setProduct(product);
                ol.setQuantity(orderQty[i]);
                
                orderLines.add(ol);
                saveThisOrder = true;
            }
        }
            
        if (saveThisOrder) {
            
            // Adding Order Lines to the Order
            for (int i=0; i < orderLines.size(); i++) {
                System.out.println("*** Adding order line: PRICE: " + orderLines.get(i).getPrice() + ", QTY: " + orderLines.get(i).getQuantity());
                order.addOrderLine(orderLines.get(i));
            }
            
            // Saving the order
            order = orderService.saveOrder(order);
            System.out.println("***** ORDER ID RETURNED FROM WS: " + order.getId());
            
            // Saving Order lines (due to the Association bug Order x Orderline)
            for (int i=0; i < orderLines.size(); i++) {
                orderService.saveOrderline(user.getPerson().getId(), order.getId(), orderLines.get(i));
            }
            
        } else {    
            System.out.println("*** SKIPPING THIS ORDER. NO ITEMS FOUND!");
            return "redirect:/products";            
        }
        
        model.addAttribute("msg", "Order Successfully Placed!");
        return "index";
    }
    
}