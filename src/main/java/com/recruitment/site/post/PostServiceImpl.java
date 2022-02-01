package com.recruitment.site.post;

import com.recruitment.site.post.entity.Post;
import com.recruitment.site.post.entity.PostDTO;
import com.recruitment.site.user.UserRepository;
import com.recruitment.site.user.entity.User;
import com.recruitment.site.utils.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository repository;
    private UserRepository userRepository;

    PostServiceImpl(PostRepository repository,
                    UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Post> findAll(int page, int size){
        Page<Post> posts = repository.findAll(PageRequest.of(page, size));
        List<Post> result = new ArrayList<>();

        for(Post post : posts){
            result.add(post);
        }

        return result;
    }

    @Override
    public Post save(PostDTO postDTO) throws Exception {
        long userId = Long.parseLong(postDTO.getUserId());
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty() ){
            throw new Exception("Not Found User");
        }

        Post post = convertPost(postDTO);
        post.setUser(user.get());
        return repository.save(post);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById((Long) id);
    }

    private Post convertPost(PostDTO postDTO){
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setCreatedDate(Util.convertLocalDateTime(postDTO.getCreatedDate()));
        post.setExpiredDate(Util.convertLocalDateTime(postDTO.getExpiredDate()));
        return post;
    }
}
