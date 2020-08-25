package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.repository.MessageRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    /**
     * search for text in message text
     *
     * @param textForSearch - text for search
     * @return - list of message
     */
    public List<Message> findByTextContaining(String textForSearch) {
        List<Message> messages = new ArrayList<>();
        messageRepository.findByTextContaining(textForSearch).forEach(messages::add);
        return messages;
    }
}
