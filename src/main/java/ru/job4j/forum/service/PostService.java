package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(posts::add);
        return posts;
    }

    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    /**
     * search for text in post name
     *
     * @param textForSearch - text for search
     * @return list of posts
     */
    public List<Post> findByNameContaining(String textForSearch) {
        List<Post> posts = new ArrayList<>();
        postRepository.findByNameContaining(textForSearch).forEach(posts::add);
        return posts;
    }

    /**
     * search for text in post description
     *
     * @param textForSearch - text for search
     * @return list of posts
     */
    public List<Post> findByDescriptionContaining(String textForSearch) {
        List<Post> posts = new ArrayList<>();
        postRepository.findByDescriptionContaining(textForSearch).forEach(posts::add);
        return posts;
    }


}
