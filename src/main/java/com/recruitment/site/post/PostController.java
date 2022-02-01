package com.recruitment.site.post;

import com.recruitment.site.post.entity.PostDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/posts")
public class PostController {
    private PostService service;

    PostController(PostService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") String page,
                                     @RequestParam(value = "size", defaultValue = "10") String size){
        // 디폴트 page / size 처리
        return ResponseEntity.ok().body(service.findAll(Integer.parseInt(page), Integer.parseInt(size)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PostDTO postDTO) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("content", service.save(postDTO));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
