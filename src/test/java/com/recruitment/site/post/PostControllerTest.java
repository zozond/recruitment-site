package com.recruitment.site.post;

import com.recruitment.site.post.entity.Post;
import com.recruitment.site.post.entity.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {

    @MockBean
    private PostService service;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){}

    @Test
    @DisplayName("채용 정보 조회")
    public void findAll() throws Exception {
        this.mockMvc.perform(get("/posts").param("page", "1").param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


    @Test
    @DisplayName("채용 정보 올린 후 조회")
    public void save_than_findAll() throws Exception {
        Post post = new Post();
        post.setId(1);
        post.setTitle("sample");
        post.setContent("content sample");

        List<Post> list = new ArrayList<>();
        list.add(post);
        given(service.findAll(1, 10)).willReturn(list);

        this.mockMvc.perform(
                    post("/posts")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\":\"sample\",\"content\":\"content sample\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));

        this.mockMvc.perform(get("/posts").param("page", "1").param("size", "10"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"title\":\"sample\",\"content\":\"content sample\"}]"));
    }
}