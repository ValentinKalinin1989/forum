package ru.job4j.forum.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorityService;
import ru.job4j.forum.service.UserService;

@Controller
public class RegControl {
    private final PasswordEncoder passwordEncoder;
    private final AuthorityService authorityService;
    private final UserService userService;

    public RegControl(AuthorityService authorityService, UserService userService) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authorityService = authorityService;
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String save(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        userService.save(user);
        return "redirect:/login";
    }
    @GetMapping("/reg")
    public String reg() {
        return "/reg";
    }
}
