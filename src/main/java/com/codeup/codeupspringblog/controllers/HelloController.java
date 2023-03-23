package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//-- States that this is a class
//=- you are telling spring that this class should be used to handle incoming HTTP requests from
@Controller
class HelloController {

    //-- Define a method that should be involved when a request is received
    @GetMapping("/hello")
    //-- Tell spring that whatever is returned should be in the body of the response
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @GetMapping("hello/{name}&{last}")
    @ResponseBody
    public String sayHello(@PathVariable String name, @PathVariable String last){
        return "Hello " + name + " " + last + "!";
    }

    @GetMapping("/dice")
    @ResponseBody
    public String rollDice(){
        int diceRoll = (int) (Math.random() * 6) + 1;
        return "Roll-result: "+ diceRoll;
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }



}


