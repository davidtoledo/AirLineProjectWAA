package net.tolledo.webservices;

import java.util.List;
import net.tolledo.domain.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserWSClient  {
    
    public Iterable <User> getAllUser() {
        String url = "http://localhost:8080/user/getAllUser";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity <List<User>> rateResponse = restTemplate.exchange(url,
            HttpMethod.GET, null, 
            new ParameterizedTypeReference <List <User>> () {}
        );
        List <User> ret = rateResponse.getBody();
        return ret;
    }
    
    public User getUserById(Integer id) {
        String url = "http://localhost:8080/user/getUser?userId=" + id;
        RestTemplate restTemplate = new RestTemplate();
        User u = restTemplate.getForObject(url, User.class);
        return u;
    }        
        
    public User findUserByEmail(String email) {
        String url = "http://localhost:8080/user/findByEmail?email=" + email;
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
    
    public User saveUser(User user) {
        String url = "http://localhost:8080/user/save";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, user, User.class);
        return user;
    }

    public User saveUserProfile(User user) {
        String url = "http://localhost:8080/user/saveProfile";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForLocation(url, user, User.class);
        return user;
    }
    
    public void deleteUser(Integer id) {
        String url = "http://localhost:8080/user/delete?id=" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(url, User.class);
    }     
    
}