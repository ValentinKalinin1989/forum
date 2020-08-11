package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PostControl {

    private final PostService postService;
    private final UserService userService;

    public PostControl(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    /**
     * open post form
     *
     * @param model
     * @return post.html
     */
    @GetMapping(value = {"/post","post"})
    public String posts(@RequestParam(value = "id_post") Long id,
                        Model model) {
        Post post = postService.findById(id).orElse(new Post());
        List<Message> messages = post.getMessages();
        model.addAttribute("post", post);
        model.addAttribute("messages", messages);
        return "post";
    }

    @GetMapping(value = {"/create_post","create_post"})
    public String createPost() {
        return "new_post";
    }

    /**
     * save new post and open
     *
     * @param post
     * @return post.html
     */
    @PostMapping(value = {"/save_post", "save_post"})
    public String savePost(@ModelAttribute Post post, Model model) {
        post.setCreated(LocalDate.now());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        post.setUser(user);
        postService.save(post);
        return "redirect:/";
    }
}
