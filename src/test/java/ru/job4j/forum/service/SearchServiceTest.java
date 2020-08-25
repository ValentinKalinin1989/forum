package ru.job4j.forum.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import javax.persistence.EntityManager;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan("ru.job4j.forum.service")
class SearchServiceTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SearchService searchService;

    @Test
    void findTextInPostNameDescAndMessage() {

        Authority authority = new Authority();
        authority.setId(2L);
        authority.setAuthority("ROLE_USER");

        User user = new User();
        user.setId(1L);
        user.setEnabled(true);
        user.setUsername("user");
        user.setPassword("$2a$10$qjRKBlcn4tk5881ukK9NBuz9BuFLxwPO648gCOeuJPepZKYmt657C");
        user.setAuthority(authority);

        Post firstPost = new Post("Tab или четыре пробела", "Каким  символом лучше выделять абзацы", user);
        firstPost.addMessage(new Message("Tab быстрее", user, firstPost));
        firstPost.addMessage(new Message("4 имеют одинаковое форматирование", user, firstPost));
        firstPost.addMessage(new Message("Tab по-умолчанию в Idea", user, firstPost));

        Post secondPost = new Post("Шаоми", "Бобедит ли экосистема Шаоми экосистемы Айфона, выпустив автомобили Шаоми?", user);
        secondPost.addMessage(new Message("Слава Шаоми", user, secondPost));
        secondPost.addMessage(new Message("Автомобиль под управоением MUI", user, secondPost));
        secondPost.addMessage(new Message("Ура!!!", user, secondPost));
        secondPost.addMessage(new Message("Конец айфонам", user, secondPost));
        secondPost.addMessage(new Message("Когда Шаоми будет строить дома в России? Хаачу дом от Шаоми, что точно полня экосистема была", user, secondPost));

        entityManager.persist(firstPost);
        entityManager.persist(secondPost);
        entityManager.flush();

        List<Post> postsFullWord = searchService.findTextInPostNameDescAndMessage("Tab");
        List<Post> postsFewSymOfWord = searchService.findTextInPostNameDescAndMessage("фонам");


        assertThat(postsFullWord.get(0).getMessages().get(0).getText(), is("Tab быстрее"));
        assertThat(postsFullWord.get(0).getMessages().get(1).getText(), is("Tab по-умолчанию в Idea"));
        assertThat(postsFewSymOfWord.get(0).getMessages().get(0).getText(), is("Конец айфонам"));
    }
}