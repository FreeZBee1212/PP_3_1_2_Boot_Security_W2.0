package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;


@Controller
public class UserController {
    private final UserServiceImpl userService;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserServiceImpl userService, RoleService roleService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/admin/users")
    public String getUsers(Model model) {
        model.addAttribute("index", userService.getAllUsers());
        return "admin/users/index";
    }

    @GetMapping("/admin/users/id")
    public String showId(@RequestParam("id") Long id, Model model) {
        model.addAttribute("show", userService.getUserById(id));
        return "admin/users/show";
    }

    @GetMapping("/admin/users/new")
    public String createNewUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/users/new";
    }


    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/id/edit")
    public String edit(Model model, @RequestParam("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/users/edit";
    }

    @PostMapping("/admin/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return "redirect:/admin/users";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/users";
    }
}
