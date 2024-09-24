package com.example.boards.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String password;
    @Setter
    private String content;

    @Setter
    @ManyToOne
    private Article article;

    public Comment() {}

    public Comment(String password, String content, Article article) {
        this.password = password;
        this.content = content;
        this.article = article;
    }
}
