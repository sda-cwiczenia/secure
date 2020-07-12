package com.sda.secure.controller;

import com.sda.secure.model.Role;
import com.sda.secure.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdminController {

    RoleRepository roleRepository;

    public AdminController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/addrole")
    public String formToAddRole(Model model) {
        Role role2 = new Role();
        model.addAttribute("role22",role2);
        return "/addrole";
    }

    @PostMapping("/addrole")
    public String addRole(@ModelAttribute("role2") Role role) {

        roleRepository.save(role);
        // tu dodajemy role
        return "redirect:/admin/addRoleSuccess";
    }

    @GetMapping("/addRoleSuccess")
    public String addRoleSuccess() {
        return "/admin/addrole-success";
    }
//        @GetMapping("/addrole")
//    public String formToAddRole(@RequestParam String roleName) {
//            System.out.println("Dostałem zmienną "+roleName);
//        Role role = new Role();
//
//        role.setRole(roleName);
//        roleRepository.save(role);
//        return "redirect/admin/addRoleSuccess";
//    }


}
