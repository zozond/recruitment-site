package com.recruitment.site.applyment;

import com.recruitment.site.applyment.entity.ApplymentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/applyment")
public class ApplymentController {
    private ApplymentService service;

    ApplymentController(ApplymentService service){
        this.service = service;
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") String page,
                                     @RequestParam(value = "size", defaultValue = "10") String size){
        return ResponseEntity.ok().body(service.findAll(Integer.parseInt(page), Integer.parseInt(size)));
    }

    @GetMapping
    public ResponseEntity<?> findByPostId(@RequestParam(value = "postId") String postId) throws Exception {
        return ResponseEntity.ok().body(service.findByPostId(Long.parseLong(postId)));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ApplymentDTO applymentDTO) throws Exception {
        return ResponseEntity.ok().body(service.save(applymentDTO));
    }
}
