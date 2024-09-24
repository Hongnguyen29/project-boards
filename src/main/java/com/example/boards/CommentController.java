package com.example.boards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article/{articleId}/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }
    //create
    @PostMapping
    public String create(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("passwordComment")
            String passwordComment,
            @RequestParam("content")
            String content
    ){
        commentService.create(articleId, passwordComment, content);
        return String.format("redirect:/article/%d",articleId);
    }
    //delete
    @PostMapping("{commentId}/delete")
    public String delete(
            @PathVariable("articleId")
            Long articleId,
            @PathVariable("commentId")
            Long commentId,
            @RequestParam("passwordCommentDelete")
            String passwordCommentDelete
    ){
        Long id = commentService.readOne(commentId).getArticle().getId();
        commentService.delete(commentId,passwordCommentDelete);
        return String.format("redirect:/article/%d",articleId);

    }

}

