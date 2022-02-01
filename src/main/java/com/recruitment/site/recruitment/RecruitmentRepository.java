package com.recruitment.site.recruitment;

import com.recruitment.site.recruitment.entity.Resume;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecruitmentRepository extends CrudRepository<Resume, Long> {
    List<Resume> findAll();
    Optional<Resume> findById(Long id);
    Optional<Resume> findByUserId(Long userId);

    Resume save(Resume resume);
    void deleteById(Long id);
    void deleteAll();
}
