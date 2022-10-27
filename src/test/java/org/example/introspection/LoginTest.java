package org.example.introspection;

import org.example.introspection.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest {

    @Autowired
    MainController mainController;

    @Autowired
    MockMvc mockMvc; // подмененный веб-слой

    @Test
    public void contextLoader() throws Exception{
        this.mockMvc.perform(get("/")) // выводит запрос на главную страницу
                .andDo(print()) // выводит в консоль логи
                .andExpect(status().isOk()) // вернет статус 200 (те работает)
                .andExpect(content().string(containsString("Добро пожаловать в Дневник Самоанализа"))); // вернет контент строку
    }

    @Test
    public void accessDeniedTest() throws Exception{
        this.mockMvc.perform(get("/main")) // выводит страницу
                .andDo(print()) // выводит в консоль логи
                .andExpect(status().is3xxRedirection()) // вернет статус 300 (работает)
                .andExpect(redirectedUrl("http://localhost/login")); // вернет контент строку
    }

    @Test
    public void correctLoginTest() throws Exception{
        this.mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin().user("admin").password("1234")) // обращение к форме логина
                .andDo(print()) // выводит в консоль логи
                .andExpect(status().is3xxRedirection()) // вернет статус 300 (работает)
                .andExpect(redirectedUrl("/")); // вернет контент строку;
    }

    @Test
    public void badCredentials() throws Exception{
        this.mockMvc.perform(post("/login").param("user","Alfred")) // обращение к форме логина
                .andDo(print()) // выводит в консоль логи
                .andExpect(status().isForbidden()); // вернет статус 400 (не запрещено)

    }


}
