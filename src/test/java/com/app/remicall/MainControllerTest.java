package com.app.remicall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user")
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testdb/create_user_before.sql", "/testdb/messages_list_before.sql"},
    executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/testdb/messages_list_after.sql", "/testdb/remove_user_after.sql"},
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {

    @Autowired
    private MockMvc mock;

    //try
//    @Test
//    public void mainPageTest() throws Exception {
//        this.mock.perform(get("/main"))
//                .andDo(print())
//                .andExpect(authenticated())
//                .andExpect(xpath("//div[@id='navbarSupportedContent']/div")
//                        .string("user"));
//    }

    @Test
    public void messageListTest() throws Exception {
        this.mock.perform(get("/main"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='message-list']/div")
                        .nodeCount(4));
    }
}
