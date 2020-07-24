package ru.job4j.forum.model;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String desc;
    private LocalDate created;

    @OneToMany
    private List<Message> messageList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
