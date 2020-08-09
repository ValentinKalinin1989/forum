package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.ForumApplication;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = ForumApplication.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    /*
    @Test
    @WithMockUser
    public void shouldReturnPostPage() throws Exception {
        this.mockMvc.perform(get("/post?id_post=8"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }
     */

    @Test
    @WithMockUser
    public void shouldReturnNewPostPage() throws Exception {
        this.mockMvc.perform(get("/create_post"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("new_post"));
    }

    @Test
    @WithMockUser
    public void shouldReturnDefaultPost() throws Exception {
        this.mockMvc.perform(post("/save_post")
                .param("name", "Имя поста")
                .param("description", "Описание поста"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argumentCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postService).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getName(), is("Имя поста"));
        assertThat(argumentCaptor.getValue().getDescription(), is("Описание поста"));
    }
}
