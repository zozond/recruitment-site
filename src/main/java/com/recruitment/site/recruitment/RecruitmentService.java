package com.recruitment.site.recruitment;

import com.recruitment.site.recruitment.entity.Resume;
import com.recruitment.site.recruitment.entity.ResumeDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecruitmentService {
    List<Resume> findAll();
    Optional<Resume> findByUserId(Long userId);
    Resume save(ResumeDTO resumeDTO) throws Exception;
    void deleteById(Long id);
}
