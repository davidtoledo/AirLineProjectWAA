package net.tolledo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .csrf().disable()
            .authorizeRequests()
                                
                .antMatchers("/", 
                             "/home", 
                             "/products", 
                             "/user/new", 
                             "/user", 
                             "/perform_login")
                             .permitAll()
                                
                .antMatchers("/userProfile", 
                             "/placeOrder").
                            hasAnyRole("USER", "ADMIN")
                
                .antMatchers("/users", 
                             "/orders").
                            hasAnyRole("ADMIN")
                
                        
                .anyRequest().authenticated()
                
                .and()
                
            .formLogin()
                .loginPage("/")
            	.permitAll()
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/")
                .permitAll();
                
        //httpSecurity.authorizeRequests().antMatchers("/").permitAll();        
        //httpSecurity.csrf().disable();
        //httpSecurity.headers().frameOptions().disable();
    }
    
}