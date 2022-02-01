package com.recruitment.site.applyment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.recruitment.site.post.entity.Post;
import com.recruitment.site.recruitment.entity.Resume;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applyment")
public class Applyment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "applyment_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="post_id")
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(name="resume_id")
    private Resume resume;

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private LocalDateTime createdDate;

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
