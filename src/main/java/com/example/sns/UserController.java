package com.example.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
public class UserController {
    @Autowired
    UserService us;

    @PostConstruct
    public void Init(){
        this.us.add("abc", "abc", "abc", "abc");
    }

    @GetMapping("/user")
    public User getUser(@RequestParam  String id, @RequestParam String pw) {
        return us.getUser(id, pw);
    }
}
