package com.example.payrollapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/test")
    public String sayHello() {
        return "I'm up and running waiting for some requests!";
    }

}
