package com.example.boards.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String content;
    @Setter
    private String password;

    @Setter
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @Setter
    @ManyToOne
    private Board board;

    public Article() {}
    public Article(String title, String content, String password, Board board) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.board = board;
    }
}
