package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.List;

@Controller
public class IndexControl {

    private final PostService postService;

    public IndexControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("")
    public String index(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }
}
