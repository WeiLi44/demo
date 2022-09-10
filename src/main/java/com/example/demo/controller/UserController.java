package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    UserRepo urp;

    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login/home")
    public String userLogin(@ModelAttribute("user") User user){
        String username = user.getUsername();
        User currUser = urp.findByUsername(username);
        if(user.getPassword().equals(currUser.getPassword())){
            return "home";
        }
        return "error";
    }
}
