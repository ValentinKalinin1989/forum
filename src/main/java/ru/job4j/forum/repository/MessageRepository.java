package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
