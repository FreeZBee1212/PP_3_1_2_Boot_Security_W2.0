package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class RoleService {


    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }



    public List<Role> getRoles() {
        return roleRepository.findAll();
    }


    public Role findRoleById(Long id) {
        return roleRepository.findById(id).get();
    }


    public void addRole(Role role) {
        roleRepository.save(role);
    }

//    @PostConstruct
//    public void initDefaultRoles() {
//        Role adminRole = new Role();
//        adminRole.setRole("ROLE_ADMIN");
//
//        Role userRole = new Role();
//        userRole.setRole("ROLE_USER");
//
//        roleRepository.save(adminRole);
//        roleRepository.save(userRole);
//    }
}
