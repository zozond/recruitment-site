package com.recruitment.site.post;

import com.recruitment.site.post.entity.Post;
import com.recruitment.site.post.entity.PostDTO;

import java.util.List;

public interface PostService {
    List<Post> findAll(int page, int size);
    Post save(PostDTO postDTO) throws Exception;
    void deleteById(long id);
}
