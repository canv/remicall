package com.app.remicall.configurations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.matchers.JUnitMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class LoginTest {

    @Autowired
    private MockMvc mock;

    private final String hostname = "localhost";

    @Test
    public void contextLoadTest() throws Exception {
        this.mock.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())//status200
                .andExpect(content().string(containsString("Hello, dude!")));
    }

    @Test
    public void accessDeniedTest() throws Exception {
        this.mock.perform(get("/main"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())//redirect to login page
                .andExpect(redirectedUrl("http://" + hostname + "/login"));
    }

    @Test
    @Sql(value = {"/testdb/create_user_before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/testdb/remove_user_after.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void correctLoginTest() throws Exception {
        this.mock.perform(formLogin().user("user").password("pass"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())//redirect to login page
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void badCredentialsTest() throws Exception {
        this.mock.perform(post("/login").param("user","badPass"))
                .andDo(print())
                .andExpect(status().isForbidden()); // status403
    }
}
