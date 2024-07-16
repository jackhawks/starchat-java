package com.starchat.controller;

import com.starchat.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User Controller
 *
 * @author Jack
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    private UserService userService;


}
