package com.zxz.wordladder;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import com.zxz.wordladder.controller.WordladderController;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WordladderController.class,webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebTest{

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void init() {
        System.out.println("测试请求Web层");
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
   public void pathTesting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/wordladder?word1={str1}&word2={str2}","code", "spring")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value("code --> rode --> rone --> prone --> prong --> sprong --> spring"))
                .andDo(MockMvcResultHandlers.print());

     mvc.perform(MockMvcRequestBuilders.post("/wordladder?word1={str1}&word2={str2}","practice", "hello")
             .contentType(MediaType.APPLICATION_JSON_UTF8)
             .accept(MediaType.APPLICATION_JSON_UTF8)
             .session(session)
     ).andExpect(MockMvcResultMatchers.status().isOk())
             .andExpect(MockMvcResultMatchers.jsonPath("$").value("There is no path between: practice and hello"))
             .andDo(MockMvcResultHandlers.print());
    }

    @After
    public void after() {
        System.out.println("测试结束");
    }

}