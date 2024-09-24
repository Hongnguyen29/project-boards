package com.example.boards;

import com.example.boards.model.Article;
import com.example.boards.model.Board;
import com.example.boards.repo.ArticleRepository;
import com.example.boards.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final BoardRepository boardRepository;

    public ArticleService(ArticleRepository articleRepository,
                          BoardRepository boardRepository){
        this.boardRepository = boardRepository;
        this.articleRepository = articleRepository;
    }
    //Create
    public Article create(
            String title,
            String content,
            String password,
            Board board
            //Long boardId
    ){
        Article article = new Article(title,content,password,board);
        return articleRepository.save(article);
       /* Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setPassword(password);

        Optional<Board> board = boardRepository.findById(boardId);
        article.setBoard(board.orElse(null));

        return articleRepository.save(article);*/
    }
    //readAll
    public List<Article> readAll(){
        List<Article> articles = articleRepository.findAll();
        articles.sort((a1, a2) -> Long.compare(a2.getId(), a1.getId()));
        return articles;
    }
    //readOne
    public Article readOne(Long id){
        return articleRepository.findById(id).orElseThrow();
    }
    //update
    public Article update(
            Long id,
            String title,
            String content,
            String password,
            Board board
    ){
        Optional<Article> optionalTarget = articleRepository.findById(id);
        if(optionalTarget.isEmpty()){
            return  null;
        }
        Article target = optionalTarget.get();
        if(!password.equals(target.getPassword())){
            return null;
        }
        target.setTitle(title);
        target.setContent(content);
        target.setBoard(board);
        return articleRepository.save(target);
    }
    //delete
    public void delete(Long id, String password){
       Article optionalTarget = articleRepository.findById(id).orElseThrow();
       if(password.equals(optionalTarget.getPassword())){
           articleRepository.deleteById(id);
       }
    }
    public Article next(Long id){
        Long idNext=0L;
        Long idB = readOne(id).getBoard().getId();
       List<Article> articles = boardRepository.findById(idB).orElseThrow().getArticles();
       articles.forEach(a -> System.out.print(a.getId() + " "));
       for (int i =0; i<articles.size()-1;i++){
           if (articles.get(i).getId().equals(id)){
               idNext = articles.get(i+1).getId();break;
           }
       }
        // 해당 게시판의 글들 중, id가 id인 게시글을 찾고,
     /*   int nextIdx = -1;
        for (int i = 0; i < articles.size(); i++) {
            if (articles.get(i).getId().equals(id)) {
                nextIdx = i;
                break;
            }
        }
        // 그 다음의 글의 id를 회수하고 싶다.
        nextIdx++;
        if (!(nextIdx == articles.size() || nextIdx == 0))
            idNext = articles.get(nextIdx).getId();*/

       if(idNext==0) idNext=id;
       System.out.println(idNext);
       return readOne(idNext);
    }
    public Article pre (Long id){
        Long idPre=0L;
        Long idB = readOne(id).getBoard().getId();
        List<Article> articles = boardRepository.findById(idB).orElseThrow().getArticles();
        for (int i =1; i<articles.size();i++){
            if (articles.get(i).getId().equals(id)){
                idPre = articles.get(i-1).getId();break;
            }
        }
        if(idPre==0) idPre=id;
        System.out.println(idPre);
        return readOne(idPre);
    }
}
