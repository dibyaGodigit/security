package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.security.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class MySecurityConfiguration {
	
	@Autowired
	private CustomUserDetailsService customUserDetails;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http 
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(authorize -> authorize
				/*
				 * Here if we want to permit all the urls starting with suppose 'public'
				 * we can do that like below 
				 * 
				 * .requestMatcher("public/**").permitAll();
				 * 
				 * it means now when we will hit any url starting with /public will not be authenticated 
				 * 
				 * we can use hasRole("any-role")
				 *  it will authenticate based on role of that user 
				 */
				.requestMatchers("users/add").permitAll()
				.requestMatchers("users/**").hasRole("ADMIN")
//				.requestMatchers("/users/**").hasRole("ADMIN")
//				.requestMatchers("/public/**").hasRole("USER")
                .anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults());
		
		
//    	http.csrf(csrf->csrf.disable())
//    	.authorizeHttpRequests()
//    	.requestMatchers("users/**").permitAll()
//    	.anyRequest()
//    	.authenticated();
//    	//.and().httpBasic(Customizer.withDefaults());
    	
    	
		return http.build();
	}
    
//    @Bean
//    UserDetailsService users() {
////    	UserDetails user1 = User.builder().username("Dibya").password(this.passwordEncoder().encode("123")).roles("USER").build();
////    	UserDetails user2 = User.builder().username("Kumar").password(this.passwordEncoder().encode("123")).roles("ADMIN","USER").build();
////
////    	return new InMemoryUserDetailsManager(user1,user2);
//    
//    }
    
    @Bean
    DaoAuthenticationProvider user() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(customUserDetails);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
