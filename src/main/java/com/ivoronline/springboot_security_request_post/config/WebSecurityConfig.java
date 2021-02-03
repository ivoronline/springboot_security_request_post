package com.ivoronline.springboot_security_request_post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  //=================================================================
  // USER DETAILS SERVICE
  //=================================================================
  @Bean
  @Override
  protected UserDetailsService userDetailsService() {

    UserDetails user = User.withDefaultPasswordEncoder()
      .username("myuser")
      .password("mypassword")
      .roles   ("USER")
      .build();

    return new InMemoryUserDetailsManager(user);

  }

  //=================================================================
  // CONFIGURE
  //=================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers("/MyLogin", "/Authenticate").permitAll(); //ANONYMOUS ACCESS (NO LOGIN)
    httpSecurity.csrf().disable();
  }

  //=================================================================
  // AUTHENTICATION MANAGER BEAN
  //=================================================================
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
