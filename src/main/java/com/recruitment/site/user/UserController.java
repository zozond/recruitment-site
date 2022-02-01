package com.recruitment.site.user;

import com.recruitment.site.recruitment.entity.ResumeDTO;
import com.recruitment.site.user.entity.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        Map<String, Object> result = new HashMap<>();
        result.put("content", service.findAll());
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO){
        Map<String, Object> result = new HashMap<>();
        result.put("content", service.save(userDTO));
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<?> findById(@RequestParam(value = "id") String id) throws NumberFormatException{
        return ResponseEntity.ok().body(service.findById(Long.parseLong(id)));
    }
}
