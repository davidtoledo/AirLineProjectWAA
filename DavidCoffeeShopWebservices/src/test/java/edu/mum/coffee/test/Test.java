package edu.mum.coffee.test;
 
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;
 

public class Test {
 
    public static final String url = "http://localhost:8080/product";
     
    /* GET */
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(url + "/getAllProduct", List.class);
         
        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Desc="+map.get("description"));
            }
        }else{
            System.out.println("No user exist----------");
        }
    }
     
    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        Product product = new Product(1, "teste DVD", "DVD Desc", 1.2, ProductType.BREAKFAST);
        URI uri = restTemplate.postForLocation(url+"/save/", product, Product.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
 
    /* PUT 
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user  = new User(1,"Tomy",33, 70000);
        restTemplate.put(REST_SERVICE_URI+"/user/1", user);
        System.out.println(user);
    }*/
 
 
    public static void main(String args[]){
        listAllUsers();
        createUser();
        listAllUsers();

        //updateUser();
    }
}