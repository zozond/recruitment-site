package com.recruitment.site.post;

import com.recruitment.site.post.entity.Post;
import com.recruitment.site.post.entity.PostDTO;
import com.recruitment.site.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository repository;
    @Mock
    private UserRepository userRepository;

    private PostService postService;

    @BeforeEach
    public void setup(){
        postService = new PostServiceImpl(repository, userRepository);
    }

    @Test
    @DisplayName("DB 조회")
    public void findAll(){
        int page = 1;
        int size = 10;
        List<Post> posts = new ArrayList<>();
        Page<Post> pageList = new PageImpl<>(posts);
        given(repository.findAll(PageRequest.of(page, size))).willReturn(pageList);

        List<Post> result = postService.findAll(page, size);
        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void save(){
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("test");
        postDTO.setContent("test content");

        Post p = new Post();
        p.setId(1);
        p.setTitle(postDTO.getTitle());
        p.setContent(postDTO.getContent());
        when(repository.save(any(Post.class))).thenReturn(p);

        Post post = postService.save(postDTO);
        Assertions.assertEquals(1, post.getId());
        Assertions.assertEquals("test", post.getTitle());
        Assertions.assertEquals("test content", post.getContent());
    }

    @DisplayName("생성한 다음 조회 후 아이디로 삭제 후 조회")
    @Test
    public void deleteByIdTest(){
        // 생성
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("test");
        postDTO.setContent("test content");

        Post p = new Post();
        p.setId(1);
        p.setTitle(postDTO.getTitle());
        p.setContent(postDTO.getContent());
        when(repository.save(any(Post.class))).thenReturn(p);

        Post post = postService.save(postDTO);
        Assertions.assertEquals(1, post.getId());
        Assertions.assertEquals("test", post.getTitle());
        Assertions.assertEquals("test content", post.getContent());

        // 조회
        int page = 1;
        int size = 10;
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        Page<Post> pageList = new PageImpl<>(posts);
        given(repository.findAll(PageRequest.of(page, size))).willReturn(pageList);

        List<Post> result = postService.findAll(page, size);
        Assertions.assertEquals(1, result.size());

        // 삭제
        postService.deleteById(1L);
        posts.remove(0);

        // 재조회
        Page<Post> pageList2 = new PageImpl<>(posts);
        given(repository.findAll(PageRequest.of(page, size))).willReturn(pageList2);
        result = postService.findAll(page, size);
        Assertions.assertEquals(0, result.size());
    }


}
