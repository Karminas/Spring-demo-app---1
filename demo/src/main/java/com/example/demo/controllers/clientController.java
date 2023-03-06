package com.example.demo.controllers;

import com.example.demo.models.helloWorldBeanModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class clientController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @RequestMapping("/helloWorldBean")
    public helloWorldBeanModel helloWorldBean() {
        return new helloWorldBeanModel("Hello World");
    }

    @RequestMapping("/helloWorld/{name}/{message}")
    public helloWorldBeanModel helloWorldBeanWithVariable(@PathVariable String name,
                                                          @PathVariable String message) {
        return new helloWorldBeanModel("Hello World, " + name + " Message: " + message);
    }
}
