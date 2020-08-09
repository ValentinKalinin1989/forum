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
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = ForumApplication.class)
@AutoConfigureMockMvc
public class RegControlTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void shouldReturnPostPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

    @Test
    @WithMockUser
    public void shouldReturnUser() throws Exception {
        this.mockMvc.perform(post("/reg")
                .param("username", "Имя пользователя")
                .param("password", "Пароль пользователя"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userService).save(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue().getUsername(), is("Имя пользователя"));
    }
}

