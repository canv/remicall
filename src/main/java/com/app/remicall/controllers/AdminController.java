package com.app.remicall.controllers;

import com.app.remicall.domain.Role;
import com.app.remicall.domain.User;
import com.app.remicall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user,
                               Model model
    ) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "userList";
    }

    @PostMapping
    public String changeUserRoles(
            @RequestParam("username") String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userID") User user
    ) {
        userService.updateUserRoles(user, username, form);
        return "redirect:/user";
    }
}
