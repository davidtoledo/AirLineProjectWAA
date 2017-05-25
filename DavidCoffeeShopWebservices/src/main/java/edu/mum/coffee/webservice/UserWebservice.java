package edu.mum.coffee.webservice;

import edu.mum.coffee.domain.Address;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.User;
import edu.mum.coffee.service.UserService;
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
public class UserWebservice {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping("/user/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser() ;
    }    

    @RequestMapping("/user/getUser")
    public User getUser(@RequestParam(value="userId") int userId) {
        return userService.getUser(userId);
    }
    
    @RequestMapping("/user/findByEmail")
    public User findByEmail(@RequestParam(value="email") String email) {
        return userService.findByEmail(email);
    }
    
    @RequestMapping(value = "/user/save")
    public User save(@RequestBody User user) {
        
        System.out.println("******** SAVE USER WEBSERVICE *********");
        System.out.println("EMAIL: " + user.getEmail());
                
        Person person = new Person();
        person.setEmail( user.getEmail() );
        person.setEnable(true);

        Address addr = new Address();
        person.setAddress(addr);
        
        user.setUserrole("USER_ROLE");
        user.setPerson(person);
        
        return userService.save(user);
    }
    
    @RequestMapping(value = "/user/saveProfile")
    public User saveProfile(@RequestBody User user) {
        
        System.out.println("******** SAVE PROFILE WEBSERVICE *********");
        System.out.println("EMAIL: " + user.getEmail());
                        
        return userService.save(user);
    }
    
    @RequestMapping("/user/delete")
    public void delete(@RequestParam(value="id") int id) {
        User user = getUser(id);
        userService.delete(user);
    }    
    
}