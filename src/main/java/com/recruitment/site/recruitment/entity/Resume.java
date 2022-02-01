package com.recruitment.site.recruitment.entity;

import com.recruitment.site.user.entity.User;

import javax.persistence.*;

@Entity
@Table(name = "resume")
public class Resume {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "resume_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // 외래키
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
