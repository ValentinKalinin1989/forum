package ru.job4j.forum.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MessageControl {
    private final PostService postService;
    private final MessageService messageService;
    private final UserService userService;

    public MessageControl(PostService postService, MessageService messageService, UserService userService) {
        this.postService = postService;
        this.messageService = messageService;
        this.userService = userService;
    }

    @PostMapping("message_save")
    public String saveMessage(@ModelAttribute Message message,
                              @RequestParam(value = "id_post") Long postId,
                              Model model) {
        Post post = postService.findById(postId).orElse(new Post());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        message.setPost(post);
        message.setCreated(LocalDate.now());
        message.setUser(user);
        messageService.save(message);

        post = postService.findById(postId).orElse(new Post());
        List<Message> messages = post.getMessages();
        model.addAttribute("post", post);
        model.addAttribute("messages", messages);

        return "post";
    }
}
