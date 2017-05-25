package edu.mum.coffee.webservice;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;
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
public class ProductWebservice {
    
    @Autowired
    private ProductService productService;
       
    @RequestMapping(value = "/product/save")
    public Product save(@RequestBody Product product) {
        
        System.out.println("******** SAVE PRODUCT WEBSERVICE *********");
        System.out.println("PRODUCT: " + product.getDescription());
                
        return productService.save(product);
    }

    @RequestMapping("/product/delete")
    public void delete(@RequestParam(value="id") int id) {
        Product product = getProduct(id);
        productService.delete(product);
    }

    @RequestMapping("/product/getProduct")
    public Product getProduct(@RequestParam(value="productId") int productId) {
        return  productService.getProduct(productId);
    }

    @RequestMapping("/product/getAllProduct")
    public List<Product> getAllProduct() {
        return productService.getAllProduct() ;
    }
    
    @RequestMapping("/product/findByTextSearch")
    public List<Product> findByTextSearch(@RequestParam(value="criteria") String criteria) {
        return  productService.findByTextSearch(criteria);
    }

    @RequestMapping("/product/findByPrice")
    public List<Product> findByPrice(@RequestParam(value="minPrice") double minPrice, 
                                     @RequestParam(value="maxPrice") double maxPrice) {
        return  productService.findByPrice(minPrice, maxPrice);
    }

    @RequestMapping("/product/findByProductType")
    public List<Product> findByProductType(@RequestParam(value="productType") ProductType productType) {
        return productService.findByProductType(productType);
    }
    
}