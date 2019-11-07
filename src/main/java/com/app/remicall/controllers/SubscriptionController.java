package com.app.remicall.controllers;

import com.app.remicall.domain.User;
import com.app.remicall.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SubscriptionController {

    @Autowired
    private UserService userService;

    @GetMapping("subscribe/{user}")
    public String subscribe(@PathVariable User user,
                            @AuthenticationPrincipal User currentUser
    ) {
        userService.subscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getUserId();
    }

    @GetMapping("unsubscribe/{user}")
    public String unsubscribe(@PathVariable User user,
                              @AuthenticationPrincipal User currentUser
    ) {
        userService.unsubscribe(currentUser, user);
        return "redirect:/user-messages/" + user.getUserId();
    }

    @GetMapping("{type}/{user}/list")
    public String userList(Model model,
                           @PathVariable User user,
                           @PathVariable String type
    ) {
        model.addAttribute("userChannel", user);
        model.addAttribute("type", type);

        if ("subscriptions".equals(type))
            model.addAttribute("users", user.getSubscriptions());
        else if ("subscribers".equals(type))
            model.addAttribute("users", user.getSubscribers());

        return "subscriptions";
    }
}
