package com.ivoronline.springboot_security_request_post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  //=================================================================
  // AUTHENTICATION MANAGER BEAN
  //=================================================================
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  //=================================================================
  // USER DETAILS SERVICE
  //=================================================================
  @Bean
  @Override
  protected UserDetailsService userDetailsService() {

    //CREATE USER
    UserDetails user = User.withDefaultPasswordEncoder()
      .username("myuser")
      .password("mypassword")
      .roles   ("USER")
      .build();

    //STORE USER
    return new InMemoryUserDetailsManager(user);

  }

  //=================================================================
  // CONFIGURE
  //=================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {

    //ANONYMOUS ACCESS (NO LOGIN)
    httpSecurity.authorizeRequests().antMatchers("/MyLogin", "/Authenticate").permitAll();

    //ENABLE POST TO Authenticate
    httpSecurity.csrf().disable();

  }

}
