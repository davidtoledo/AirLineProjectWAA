package net.tolledo.webservices;

import java.util.List;
import net.tolledo.domain.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductWSClient  {
    
    public Iterable <Product> getAllProduct() {
        String url = "http://localhost:8080/product/getAllProduct";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <List<Product>> rateResponse = restTemplate.exchange(url,
            HttpMethod.GET, null, 
            new ParameterizedTypeReference <List <Product>> () {}
        );
        List<Product> ret = rateResponse.getBody();
        return ret;
    }
    
    public Product getProductById(Integer id) {
        String url = "http://localhost:8080/product/getProduct?productId=" + id;
        RestTemplate restTemplate = new RestTemplate();
        Product p = restTemplate.getForObject(url, Product.class);
        return p;
    }    

    public Product saveProduct(Product product) {
        
        String url = "http://localhost:8080/product/save";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, product, Product.class);
        return product;
    }

    public void deleteProduct(Integer id) {
        String url = "http://localhost:8080/product/delete?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, Product.class);
    } 
}
