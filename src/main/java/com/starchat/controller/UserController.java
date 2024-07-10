package com.starchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller
 *
 * @author Jack
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/test")
    public String test(){
        return "hello world";
    }
}
