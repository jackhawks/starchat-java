package com.starchat.controller;

import com.starchat.entity.User;
import com.starchat.entity.vo.ResVO;
import com.starchat.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.starchat.controller.BaseController;

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

    @GetMapping("test")
    public ResVO<User> test() {
        return success(userService.getById(1));
    }
}
