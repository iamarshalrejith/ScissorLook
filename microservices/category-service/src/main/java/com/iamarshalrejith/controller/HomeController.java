package com.iamarshalrejith.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController{
    @GetMapping
    public String HomeControllerHandler(){
        return "category microservice for salon booking system";
    }
}