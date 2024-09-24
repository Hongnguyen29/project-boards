package com.example.boards;

import com.example.boards.model.Article;
import com.example.boards.model.Board;
import com.example.boards.repo.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService(BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    //Read All
    public List<Board> readAll(){
        return boardRepository.findAll();
    }
    //Read One
    public Board readOne(Long id){

       Board board= boardRepository.findById(id).orElseThrow();
       List<Article> articles = board.getArticles();
        articles.sort((a1, a2) -> Long.compare(a2.getId(), a1.getId()));
        board.setArticles(articles);
        return board;
    }
    //Search

    public List<Article> search(
            List<Article> articles,
            String control,
            String searchS){
        Set<Article> answer = new HashSet<>();
        if(control.equals("title")){
            for(Article a : articles){
                if(a.getTitle().contains(searchS)){
                    answer.add(a);
                }
            }
        }
        else if (control.equals("content")){
            for(Article a : articles){
                if(a.getContent().contains(searchS)){
                    answer.add(a);
                }
            }
        }
        List<Article> setToList = new ArrayList<>(answer);
        return setToList;
    }
}
