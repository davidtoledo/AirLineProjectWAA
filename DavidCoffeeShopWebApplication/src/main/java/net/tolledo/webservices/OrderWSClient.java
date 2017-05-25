package net.tolledo.webservices;

import java.util.List;
import net.tolledo.domain.Order;
import net.tolledo.domain.Orderline;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderWSClient  {
    
    public Iterable <Order> getAllOrder() {
        String url = "http://localhost:8080/order/findAll";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <List<Order>> rateResponse = restTemplate.exchange(url,
            HttpMethod.GET, null, 
            new ParameterizedTypeReference <List <Order>> () {}
        );
        List <Order> ret = rateResponse.getBody();
        return ret;
    }
    
    public Order saveOrder(Order order) {
        String url = "http://localhost:8080/order/save";
        RestTemplate restTemplate = new RestTemplate();
        order = restTemplate.postForObject(url, order, Order.class);
        return order;
    }

    public Orderline saveOrderline(long personId, int orderId, Orderline orderline) {
        String url = "http://localhost:8080/order/saveOrderline?orderId=" + orderId + "&personId=" + personId;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, orderline, Orderline.class);
        return orderline;
    }
    
    public void deleteOrder(Integer id) {
        String url = "http://localhost:8080/order/delete?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, Order.class);
    }         
    
}