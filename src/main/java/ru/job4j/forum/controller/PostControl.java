package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostControl {

    /**
     * open post form
     * @param model
     * @return post.html
     */
    @GetMapping("/post")
    public String posts(Model model) {
        return "post";
    }

    /**
     * save new post and open
     * @param model
     * @return post.html
     */
    @PostMapping("/create_post")
    public String savePost(Model model) {
        return "redirect:/post";
    }
}
