package com.example.boards;

import com.example.boards.model.Board;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("article")
public class ArticleController {
    private final ArticleService service;
    private final BoardService boardService;

    public ArticleController(ArticleService service,
                             BoardService boardService){
        this.service =service;
        this.boardService = boardService;
    }
    //Create
    @GetMapping("create")
    public String createView(Model model){
        model.addAttribute("boards",boardService.readAll());
        return "articles/create.html";
    }
    @PostMapping("/create")
    public String create(
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("board")
            Board board

    ){
       Long id = service.create(title, content, password, board).getId();
        return String.format("redirect:/article/%d",id);
      //  return "redirect:/article/create";

    }
    //Read One
    @GetMapping("{articleId}")
    public String readOne(
        @PathVariable("articleId")
        Long articleId,
        Model model){
        model.addAttribute("article",service.readOne(articleId));
        model.addAttribute("preArticle",service.pre(articleId));
        model.addAttribute("nextArticle",service.next(articleId));


        return "/articles/readA.html";
    }
    //update
    @GetMapping("{articleId}/update")
    public String updateView(
            @PathVariable("articleId")
            Long articleId,
            Model model
    ){
        model.addAttribute("article",service.readOne(articleId));
        model.addAttribute("boards",boardService.readAll());
        return "/articles/update.html";
    }
    @PostMapping("{articleId}/update")
    public String update(
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("title")
            String title,
            @RequestParam("content")
            String content,
            @RequestParam("password")
            String password,
            @RequestParam("board")
            Board board
    ){
        service.update(articleId, title, content, password, board);
        return String.format("redirect:/article/%d",articleId);
    }
    //delete
    @PostMapping("{articleId}/delete")
    public String delete(
          /*  @PathVariable("boardId")
            Long boardId,*/
            @PathVariable("articleId")
            Long articleId,
            @RequestParam("password")
            String password,
            Model model
    ){
        model.addAttribute("article",service.readOne(articleId));
       Long id = service.readOne(articleId).getBoard().getId();
       System.out.println("---------------------------------");
       System.out.println(id);
        service.delete(articleId,password);
        return String.format("redirect:/boards/%d",id);


    }

}
