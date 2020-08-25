package ru.job4j.forum.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.SearchService;
import ru.job4j.forum.service.UserService;
import ru.job4j.forum.tool.TextValidation;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PostControl {

    private final PostService postService;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final SearchService searchService;


    public PostControl(PostService postService, UserService userService, MessageService messageService, SearchService searchService) {
        this.postService = postService;
        this.userService = userService;
        this.objectMapper = new ObjectMapper();
        this.searchService = searchService;
    }

    /**
     * open post form
     *
     * @param model
     * @return post.html
     */
    @GetMapping("/post")
    public String posts(@RequestParam(value = "id_post") Long id,
                        Model model) {
        Post post = postService.findById(id).orElse(new Post());
        List<Message> messages = post.getMessages();
        model.addAttribute("post", post);
        model.addAttribute("messages", messages);
        return "/post";
    }

    @GetMapping("/create_post")
    public String createPost() {
        return "/new_post";
    }

    /**
     * save new post and open
     *
     * @param post
     * @return post.html
     */
    @PostMapping("/save_post")
    public String savePost(@ModelAttribute Post post, Model model) {
        post.setCreated(LocalDate.now());
        User user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        post.setUser(user);
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/finding_posts")
    @ResponseBody
    public String findPost(@RequestParam(value = "stringForSearch") String stringForSearch) throws JsonProcessingException {
        if (TextValidation.checkOnOnlySpaceInStr(stringForSearch)) {
            return "{\"error\":\"Запрос состоял из одних пробелов. Ввведите запрос снова.\"}";
        }
        if (!TextValidation.checkStrLengthBetween3And100(stringForSearch)) {
            return "{\"error\":\"Запрос имел меньше трех значащих символов или его длина была больше 100 символов. Ввведите запрос снова.\"}";
        }
        List<Post> findPosts = searchService.findTextInPostNameDescAndMessage(stringForSearch);
        return objectMapper.writeValueAsString(findPosts);
    }
}
