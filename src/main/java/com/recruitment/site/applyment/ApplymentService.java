package com.recruitment.site.applyment;

import com.recruitment.site.applyment.entity.Applyment;
import com.recruitment.site.applyment.entity.ApplymentDTO;

import java.util.List;

public interface ApplymentService {
    List<Applyment> findAll(int page, int size);
    Applyment save(ApplymentDTO applymentDTO) throws Exception;
    Applyment findByPostId(Long postId) throws Exception;
}
