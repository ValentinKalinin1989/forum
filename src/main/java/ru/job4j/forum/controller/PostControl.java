package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.util.List;

@Controller
public class PostControl {

    private final PostService postService;

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    /**
     * open post form
     *
     * @param model
     * @return post.html
     */
    @GetMapping("/post")
    public String posts(@RequestParam(value = "id") Long id,
                        Model model) {
        Post post = postService.findById(id).orElse(new Post());
        List<Message> messages = post.getMessages();
        model.addAttribute("post", post);
        model.addAttribute("messages", messages);
        return "post";
    }

    /**
     * save new post and open
     *
     * @param model
     * @return post.html
     */
    @PostMapping("/create_post")
    public String savePost(Model model) {
        return "redirect:/post";
    }
}
