package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Search for data in different repositories
 * and save them in one collection
 */
@Service
public class SearchService {

    private final MessageService messageService;
    private final PostService postService;

    public SearchService(MessageService messageService, PostService postService) {
        this.messageService = messageService;
        this.postService = postService;
    }

    /**
     * The search occurs first by the name of the post, then by the description
     * and in the conclusion by the message.
     * The results is saved to the LinkedHashMap to preserve the search order.
     *
     * @param textForSearch - text for search
     * @return list of posts
     */
    public List<Post> findTextInPostNameDescAndMessage(String textForSearch) {
        Map<Long, Post> postMap = new LinkedHashMap<>();
        postService.findByNameContaining(textForSearch).forEach(post -> {
            Post tempPost = new Post();
            Long postId = post.getId();
            tempPost.setId(postId);
            tempPost.setName(post.getName());
            tempPost.setCreated(post.getCreated());
            tempPost.setDescription(post.getDescription());
            postMap.put(postId, tempPost);
        });
        postService.findByDescriptionContaining(textForSearch).forEach(post -> {
            Long postId = post.getId();
            if (postMap.containsKey(postId)) {
                postMap.get(postId).setDescription(post.getDescription());
            } else {
                Post tempPost = new Post();
                tempPost.setId(postId);
                tempPost.setName(post.getName());
                tempPost.setCreated(post.getCreated());
                tempPost.setDescription(post.getDescription());
                postMap.put(postId, tempPost);
            }
        });
        messageService.findByTextContaining(textForSearch).forEach(message -> {
            Post post = message.getPost();
            Long postId = post.getId();
            if (postMap.containsKey(postId)) {
                postMap.get(postId).addMessage(message);
            } else {
                Post tempPost = new Post();
                tempPost.setId(postId);
                tempPost.setName(post.getName());
                tempPost.setCreated(post.getCreated());
                tempPost.setDescription(post.getDescription());
                tempPost.addMessage(message);
                postMap.put(postId, tempPost);
            }
        });
        return new ArrayList<>(postMap.values());
    }
}
