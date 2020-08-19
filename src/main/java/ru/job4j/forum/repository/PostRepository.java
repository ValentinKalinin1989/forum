package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByDescriptionContaining(String textForSearch);

    List<Post> findByNameContaining(String textForSearch);
}
