package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit {

    private final UserService userService;

    @Autowired
    public DbInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initDbUsers() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        User admin = new User();
        Set<Role> adminRoles = new HashSet<>();
        Collections.addAll(adminRoles, roleAdmin, roleUser);
        admin.setId(1L);
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setSurname("admin");
        admin.setAge(21);
        admin.setRoles(new String[]{"ROLE_ADMIN"});
        userService.saveUser(admin);

        User user = new User();
        Set<Role> userRoles = new HashSet<>();
        Collections.addAll(userRoles, roleUser);
        user.setId(2L);
        user.setUsername("user");
        user.setPassword("user");
        user.setSurname("user");
        user.setAge(22);
        user.setRoles(new String[]{"ROLE_USER"});
        userService.saveUser(user);

    }
}
