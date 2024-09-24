package com.example.boards;

import com.example.boards.model.Article;
import com.example.boards.model.Comment;
import com.example.boards.repo.ArticleRepository;
import com.example.boards.repo.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentService(CommentRepository commentRepository,
                          ArticleRepository articleRepository){
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }
    //Create
    public Comment create(
            Long articleId,
            String password,
            String content
    ){
        Article article = articleRepository.findById(articleId).orElseThrow();
        Comment comment = new Comment(password,content,article);
        return commentRepository.save(comment);
    }
    //delete
    public  void delete(
            Long id,
            String password
    ){
        Comment commentTarget = commentRepository.findById(id).orElseThrow();
        if(password.equals(commentTarget.getPassword())){
            commentRepository.deleteById(id);
        }

    }
    //readOne
    public  Comment readOne(Long id){
        return commentRepository.findById(id).orElseThrow();

    }

}
