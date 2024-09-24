package com.example.boards.repo;

import com.example.boards.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository
extends JpaRepository<Article, Long> { }
