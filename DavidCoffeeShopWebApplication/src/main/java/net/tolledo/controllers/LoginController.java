package net.tolledo.controllers;

import net.tolledo.domain.User;
import net.tolledo.webservices.UserWSClient;
import net.tolledo.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author David Costa (david.oracle@gmail.com)
 */
   
@Controller
public class LoginController {
    
    @Autowired
    private UserWSClient userService;
 
    @RequestMapping(value = "/perform_login", method = RequestMethod.POST)
    public String doLogin(User user, Model model) {
        
        if (!user.getEmail().equals("") && !user.getPassword().equals("")) {
            
            User dbUser = null;
            try {

                dbUser = userService.findUserByEmail(user.getEmail());
                
            } catch (Exception e) {
                dbUser = null;
            }
            
            if (dbUser != null) {
                System.out.println("\n\n\n************************************************************************************");
                System.out.println("WebService return <-> email: " + dbUser.getEmail() + " and password " + dbUser.getPassword());
                System.out.println("This user is an " + dbUser.getUserrole());
                System.out.println("************************************************************************************\n\n");

                if ( user.getEmail().equals(dbUser.getEmail()) && user.getPassword().equals(dbUser.getPassword()) ) {

                    // Spring Login
                    Authentication auth;
                    if ( dbUser.getUserrole().equals(AppUtil.ROLE_ADMIN) ) {
                        auth = new UsernamePasswordAuthenticationToken(dbUser, null, AppUtil.getAdminAuthority());
                    } else {
                        auth = new UsernamePasswordAuthenticationToken(dbUser, null, AppUtil.getUserAuthority());
                    }
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    
                    return "index";
                } 
            }
        }
        
        // ERROR
        model.addAttribute("error", "Invalid email and Password!");
        return "index";
        
    }
}