package com.recruitment.site.recruitment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RecruitmentControllerTest {
    @MockBean
    private RecruitmentService service;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    public void setup(){}

    @Test
    @DisplayName("구인글 조회")
    public void test() throws Exception {
        this.mockMvc.perform(get("/post"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"content\":[]}"));
    }


    @Test
    @DisplayName("유저 데이터 추가 후 데이터 가져오기")
    public void create() throws Exception {
//        given(userService.createUser(createdUser)).will(invocation -> {
//            User user = invocation.getArgument(0);
//            user.setId(1);
//            return user;
//        }); // willReturn 방식을 이용하면, id 는 null 값

//        this.mockMvc.perform(post("/create").contentType(MediaType.APPLICATION_JSON).content(toJsonString(createdUser)))
//                .andDo(print())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(status().isOk());
//
//        this.mockMvc.perform(get("/findAll"))
//                .andDo(print())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//                .andExpect(status().isOk());
    }
}