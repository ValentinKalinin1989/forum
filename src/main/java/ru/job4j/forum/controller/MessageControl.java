package ru.job4j.forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

@Controller
public class MessageControl {
    private final PostService postService;
    private final MessageService messageService;

    public MessageControl(PostService postService, MessageService messageService) {
        this.postService = postService;
        this.messageService = messageService;
    }

    @PostMapping("/message_save")
    public String saveMessage(@ModelAttribute Post post, Model model) {

                return "redirect:/post";
    }
}
