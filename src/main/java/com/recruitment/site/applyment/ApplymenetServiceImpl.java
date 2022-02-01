package com.recruitment.site.applyment;

import com.recruitment.site.applyment.entity.Applyment;
import com.recruitment.site.applyment.entity.ApplymentDTO;
import com.recruitment.site.post.PostRepository;
import com.recruitment.site.post.entity.Post;
import com.recruitment.site.recruitment.RecruitmentRepository;
import com.recruitment.site.recruitment.entity.Resume;
import com.recruitment.site.user.UserRepository;
import com.recruitment.site.user.entity.User;
import com.recruitment.site.utils.Util;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplymenetServiceImpl implements ApplymentService{

    private ApplymentRepository repository;
    private PostRepository postRepository;
    private RecruitmentRepository recruitmentRepository;

    ApplymenetServiceImpl(ApplymentRepository repository,
                          PostRepository postRepository,
                          RecruitmentRepository recruitmentRepository){
        this.repository = repository;
        this.postRepository = postRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    @Override
    public List<Applyment> findAll(int page, int size) {
        Page<Applyment> applyments = repository.findAll(PageRequest.of(page, size));
        List<Applyment> result = new ArrayList<>();

        for(Applyment applyment : applyments){
            result.add(applyment);
        }
        return result;
    }

    @Override
    public Applyment save(ApplymentDTO applymentDTO) throws Exception {
        long postId = Long.parseLong(applymentDTO.getPostId());
        long resumeId = Long.parseLong(applymentDTO.getResumeId());
        String createdDate = applymentDTO.getCreatedDate();

        Optional<Resume> resume = recruitmentRepository.findById(resumeId);
        if (resume.isEmpty()){
            throw new Exception("Not Found Resume");
        }

        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()){
            throw new Exception("Not Found Post");
        }

        Applyment applyment = new Applyment();
        applyment.setCreatedDate(Util.convertLocalDateTime(createdDate));
        applyment.setPost(post.get());
        applyment.setResume(resume.get());
        return repository.save(applyment);
    }


    @Override
    public Applyment findByPostId(Long postId) throws Exception {
        Optional<Applyment> applyment = repository.findByPostId(postId);
        if(applyment.isEmpty()){
            throw new Exception("Not found applyment");
        }

        return applyment.get();
    }
}
