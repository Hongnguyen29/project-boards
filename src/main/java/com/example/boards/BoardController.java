package com.example.boards;

import com.example.boards.model.Article;
import com.example.boards.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("boards")
public class BoardController {
    private final BoardService boardService;
    private final ArticleService articleService;

    public BoardController(BoardService boardService,
                           ArticleService articleService){
        this.boardService = boardService;
        this.articleService = articleService;
    }

    @GetMapping
    public String readAll(Model model){
        model.addAttribute("boards",boardService.readAll());
        model.addAttribute("articles",articleService.readAll());
        List<String> scopes = new ArrayList<>();
        scopes.add("title");scopes.add("content");
        model.addAttribute("scopes",scopes);
        return "boards/home.html";
    }
    @GetMapping("{id}")
    public String readOne(
            @PathVariable("id")
            Long id,
            Model model
            ){
        model.addAttribute("board",boardService.readOne(id));
        model.addAttribute("articles",boardService.readOne(id).getArticles());


        return "boards/readB.html";
    }

    @PostMapping("search")
    public String searchView(
            @RequestParam("scope")
            String scope,
            @RequestParam("searchS")
            String searchS,
            Model model
    ){
        List<Article> articleList=  articleService.readAll();

        model.addAttribute("articles",
                boardService.search(articleList,scope,searchS));

        return "boards/search.html";
    }
    @GetMapping("read/{articleId}")
    public String read(
            @PathVariable("articleId")
            Long articleId,
             Model model){
            model.addAttribute("article",articleService.readOne(articleId));
        return "boards/next.html";
    }




}
