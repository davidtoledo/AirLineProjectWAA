package edu.mum.coffee.webservice;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Orderline;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.OrderlineService;
import edu.mum.coffee.service.PersonService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Costa (david.oracle@gmail.com)
 */
@RestController
public class OrderWebservice {
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderlineService orderlineService;

    @Autowired
    private PersonService personService;

    @RequestMapping("/order/save")
    public Order save(@RequestBody Order order){
        
        System.out.println("\n******** SAVE ORDER WEBSERVICE *********");
        System.out.println("ORDER DATE: " + order.getOrderDate());
        System.out.println("ORDER LINE SIZE: " + order.getOrderLines().size());
        
        order = orderService.save(order);
        
        return order;
    }

    @RequestMapping("/order/saveOrderline")
    public void saveOrderline(@RequestParam("personId") long personId,
                              @RequestParam("orderId") int orderId,
                              @RequestBody Orderline orderline){
        
        System.out.println("\n******** saveOrderlines WEBSERVICE *********");
        System.out.println("PERSON ID: " + personId);
        System.out.println("ORDER ID: " + orderId);
        System.out.println("ORDER LINE PRICE: " + orderline.getPrice());
        
        Person person = personService.findById(personId);
        
        Order order = orderService.findById(orderId);
        if (order == null) {        
            order = new Order();
            order.setOrderDate(new Date());
            order.setPerson(person);
        } else {
            orderline.setOrder(order);
        }
        
        orderService.save(order);
        
        System.out.println("*** WEBSERVICE saving Orderline PRICE: " + orderline.getPrice());
        orderlineService.save(orderline);
        
    }
    
    @RequestMapping("/order/delete")
    public void delete(@RequestParam(value="id") int id) {
        Order order = findById(id);
        orderService.delete(order);
    }    
    

    @RequestMapping("/order/findByProduct")
    public List<Order> findByProduct(@RequestParam(value="product") Product product) {
        return orderService.findByProduct(product);
    }

    @RequestMapping("/order/findByPerson")
    public List<Order> findByPerson(@RequestParam(value="person") Person person) {
        return orderService.findByPerson(person);
    }

    @RequestMapping("/order/findByDate")
    public List<Order> findByDate(@RequestParam(value="minDate") Date minDate, 
                                  @RequestParam(value="maxDate") Date maxDate) {
        return orderService.findByDate(minDate, maxDate);
    }

    @RequestMapping("/order/findById")
    public Order findById(@RequestParam(value="id") int id){
        return orderService.findById(id);
    }
    
    @RequestMapping("/order/findAll")
    public List<Order> findAll(){
        return orderService.findAll();
    }
    
}