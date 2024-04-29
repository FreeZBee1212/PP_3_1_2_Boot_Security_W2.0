package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/auth")
public class AuthController {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user, @ModelAttribute("role") Role role) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") User user, @RequestParam(value = "roles", required = false) List<String> roles) {
        if (roles != null) {
            Set<Role> userRoles = new HashSet<>();
            for (String role : roles) {
                Role existingRole = roleRepository.findByName(role);
                if (existingRole != null) {
                    userRoles.add(existingRole);
                }
            }
            user.setRoles(userRoles);
        }
        userRepository.save(user);
        return "redirect:/auth/login";
    }

}
