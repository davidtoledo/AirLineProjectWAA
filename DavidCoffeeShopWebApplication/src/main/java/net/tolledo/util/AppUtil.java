package net.tolledo.util;

import java.util.ArrayList;
import java.util.Collection;
import net.tolledo.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author DAVID
 */
public final class AppUtil {
    
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    
    public static Collection <GrantedAuthority> getUserAuthority() {
        //make everyone ROLE_USER
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            //anonymous inner type
            public String getAuthority() {
                return ROLE_USER;
            }
        }; 
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }    

    public static Collection <GrantedAuthority> getAdminAuthority() {
        //make everyone ROLE_USER
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            //anonymous inner type
            public String getAuthority() {
                return ROLE_ADMIN;
            }
        }; 
        grantedAuthorities.add(grantedAuthority);
        return grantedAuthorities;
    }    
    
    public static User getUserInSpringSecuritySession() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return user;
    }
    
}