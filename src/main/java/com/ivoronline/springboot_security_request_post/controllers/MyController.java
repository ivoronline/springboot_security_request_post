package com.ivoronline.springboot_security_request_post.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

  //PROPERTIES
  @Autowired private AuthenticationManager authenticationManager;

  //===============================================================================
  // CUSTOM LOGIN FORM
  //===============================================================================
  @RequestMapping("CustomLoginEndpoint")
  String customLoginForm() {
    return "CustomLoginForm";
  }

  //========================================================================
  // AUTHENTICATE
  //========================================================================
  @ResponseBody
  @RequestMapping("Authenticate")
  String authenticate(@RequestParam String username, @RequestParam String password) {

    //CREATE AUTHENTICATION OBJECT (with Entered Username & Password)
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

    //GET    AUTHENTICATION OBJECT (with Authorities)
    authentication = authenticationManager.authenticate(authentication);

    //STORE  AUTHENTICATION OBJECT (into Context)
    SecurityContextHolder.getContext().setAuthentication(authentication);

    //RETURN SOMETHING
    return "User Authenticated";

  }

  //========================================================================
  // HELLO
  //========================================================================
  @ResponseBody
  @Secured("ROLE_USER")
  @RequestMapping("Hello")
  String hello() {
    return "Hello from Controller";
  }

}

