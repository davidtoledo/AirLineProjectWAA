package net.tolledo.controllers;

import net.tolledo.domain.User;
import net.tolledo.util.AppUtil;
import net.tolledo.webservices.UserWSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserWSClient userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("users", userService.getAllUser());
        return "users";
    }
    
    @RequestMapping("user/{id}")
    public String showProduct(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "usershow";
    }
    
    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("user", userService.getUserById(id));
        return "profileform";
    }    
    
    @RequestMapping("user/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "userform";
    }
    
    @RequestMapping("user/newProfile")
    public String newUserProfile(Model model){
        model.addAttribute("user", new User());
        return "profileform";
    }    
    
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user, Model model){
        
        if (user.getEmail().equals("") || user.getPassword().equals("")) {
            model.addAttribute("error", "Empty data not allowed!");
            return "index";    
        }
        
        try {
            userService.saveUser(user);
        } catch (Exception e) {
            model.addAttribute("error", "Check if your account is already created!");
            return "index";
        }
        
        model.addAttribute("msg", "User Successfully Created!");
        return "index";
    }

    @RequestMapping(value = "userProfile", method = RequestMethod.POST)
    public String saveUserProfile(User user, Model model){
        
        if (user.getEmail().equals("") || user.getPassword().equals("")) {
            model.addAttribute("error", "Empty data not allowed!");
            return "userProfile";    
        }
        
        if (user.getUserrole() == null) {
            user.setUserrole(AppUtil.ROLE_USER);
        }
        
        try {
            userService.saveUserProfile(user);
        } catch (Exception e) {
            model.addAttribute("error", "Check if your account is already created!");
            return "index";
        }
        
        User u = AppUtil.getUserInSpringSecuritySession();
        
        if (u.getUserrole().equals(AppUtil.ROLE_ADMIN)) {
            return "redirect:/users";
        } else {
            model.addAttribute("msg", "Profile Successfully Updated!");
            return "index";
        }
    }
    
    @RequestMapping("user/delete/{id}")
    public String delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return "redirect:/users";
    }
    
    @RequestMapping("updateProfile")
    public String updateProfile(Model model){
        
        User user = AppUtil.getUserInSpringSecuritySession();
        
        User usr = userService.getUserById(user.getId());
        
        model.addAttribute("user", usr);
        return "profileform";
    }        
    
}