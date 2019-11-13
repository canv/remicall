package com.app.remicall.controllers;

import com.app.remicall.domain.User;
import com.app.remicall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("profile")
    public String getProfile(Model model,
                             @AuthenticationPrincipal User user
    ) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());

        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam("password") String password,
            @RequestParam("email") String email
    ) {
        userService.updateUserProfile(user, password, email);
        return "redirect:/user/profile";
    }
}
