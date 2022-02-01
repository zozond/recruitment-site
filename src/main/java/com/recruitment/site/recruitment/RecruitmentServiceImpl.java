package com.recruitment.site.recruitment;

import com.recruitment.site.recruitment.entity.Resume;
import com.recruitment.site.recruitment.entity.ResumeDTO;
import com.recruitment.site.user.UserRepository;
import com.recruitment.site.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{

    private UserRepository userRepository;
    private RecruitmentRepository repository;

    public RecruitmentServiceImpl(RecruitmentRepository repository, UserRepository userRepository){
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Resume> findAll() {
        List<Resume> result = new ArrayList<>();
        Resume resume = new Resume();
        result.add(resume);

        return repository.findAll();
    }

    @Override
    public Optional<Resume> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Resume save(ResumeDTO resumeDTO) throws Exception{
        String userId = resumeDTO.getUserId();
        Optional<User> user = userRepository.findById(Long.parseLong(userId));
        if (user.isEmpty()){
            throw new Exception("not found user");
        }

        Resume resume = convertResume(resumeDTO);
        resume.setUser(user.get());
        return repository.save(resume);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Resume convertResume(ResumeDTO resumeDTO){
        Resume resume = new Resume();
        resume.setContent(resumeDTO.getContent());
        resume.setTitle(resumeDTO.getTitle());
        return resume;
    }
}
