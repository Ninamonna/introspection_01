package org.example.introspection;

import org.example.introspection.controller.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("like")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/notes-list-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/notes-list-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {

    @Autowired
    MainController mainController;// то что тестируем

    @Autowired
    MockMvc mockMvc; // подмененный веб-слой

    @Test
    public void mainPageTest() throws Exception{
        this.mockMvc.perform(get("/main")) // выводит страницу
                .andDo(print()) // выводит в консоль логи
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//div[@id='navbarsExample02']/ul/li[3]/a/b").string("like")); // аунт-я пользователя  по аннотации @WithUserDetails("like")
    }

    @Test
    public void messageListTest() throws Exception{
        this.mockMvc.perform(get("/userNotes")) // выводит страницу
                .andDo(print()) // выводит в консоль логи
                .andExpect(SecurityMockMvcResultMatchers.authenticated())
                .andExpect(xpath("//div[@id='table']/br").nodeCount(0)); // количество узлов
    }
}
