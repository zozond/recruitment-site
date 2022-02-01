package com.recruitment.site.recruitment;

import com.recruitment.site.recruitment.entity.ResumeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/recruitment")
public class RecruitmentController {

    private RecruitmentService service;

    RecruitmentController(RecruitmentService service){
        this.service = service;
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findAll(){
        Map<String, Object> result = new HashMap<>();
        result.put("content", service.findAll());
        return ResponseEntity.ok().body(result);
    }

    @GetMapping
    public ResponseEntity<?> findByUserId(@RequestParam(value = "userId") String userId){
        Map<String, Object> result = new HashMap<>();
        result.put("content", service.findByUserId(Long.parseLong(userId)));
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ResumeDTO resumeDTO) throws Exception{
        Map<String, Object> result = new HashMap<>();
        result.put("content", service.save(resumeDTO));
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@RequestParam(value = "id") String id){
        service.deleteById(Long.parseLong(id));
        return ResponseEntity.ok().build();
    }
}
