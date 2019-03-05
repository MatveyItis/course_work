package ru.itis.teamwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.teamwork.models.User;
import ru.itis.teamwork.services.UserService;

import javax.validation.Valid;

@Controller
public class HomeController {
    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(@AuthenticationPrincipal User authUser) {
        if (authUser != null) {
            return "redirect:/users";
        }
        return "registration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@AuthenticationPrincipal User authUser) {
        if (authUser != null) {
            return "redirect:/users";
        }
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(@Valid User user,
                               Model model) {
        if (!userService.addUser(user)) {
            model.addAttribute("message", "Registration error. See log for details");
            return "registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(@AuthenticationPrincipal User user,
                           Model model) {
        model.addAttribute("user", user);
        return "users";
    }

}