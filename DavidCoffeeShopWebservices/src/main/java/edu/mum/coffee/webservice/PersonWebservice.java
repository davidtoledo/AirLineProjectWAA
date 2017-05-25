package edu.mum.coffee.webservice;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author David Costa (david.oracle@gmail.com)
 */
@RestController
public class PersonWebservice {
    
    @Autowired
    private PersonService personService;
        
    @RequestMapping("/person/savePerson")
    public String savePerson(@RequestParam(value="person") Person person) {
        personService.savePerson(person);
        return "OK";
    }
    
    @RequestMapping("/person/findByEmail")
    public List<Person> findByEmail(@RequestParam(value="email") String email){
        return personService.findByEmail(email);
    }
    
    @RequestMapping("/person/findById")
    public Person findById(@RequestParam(value="id") Long id){
        return personService.findById(id);
    }
    
    @RequestMapping("/person/removePerson")
    public void removePerson(Person person) {
        personService.removePerson(person);
    }
    
}