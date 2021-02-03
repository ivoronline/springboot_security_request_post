package com.ivoronline.springboot_security_request_post.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  @Autowired private AuthenticationManager authenticationManager;
  @Autowired private UserDetailsService    userDetailsService;

  @RequestMapping("/MyLogin")
  public String myLogin() {
    return "MyLogin";
  }

  @ResponseBody
  @PostMapping("/Authenticate")
  public String authenticate(@RequestParam String username, @RequestParam String password) {

    //CREATE TOKEN (FROM USERNAME & PASSWORD)
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

    //AUTHENTICATE
    try                               { authenticationManager.authenticate(authToken); }
    catch (BadCredentialsException e) { return "Invalid Credentials";                  }

    //GET USER OBJECT
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    System.out.println(userDetails);

    //SUCCESSFUL AUTHENTICATION
    return "User Authenticated";

  }

}

