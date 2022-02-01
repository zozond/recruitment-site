package com.recruitment.site.applyment;

import com.recruitment.site.applyment.entity.Applyment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplymentRepository extends JpaRepository<Applyment, Long> {
    Page<Applyment> findAll(Pageable pageable);
    Optional<Applyment> findByPostId(Long postId);
}
