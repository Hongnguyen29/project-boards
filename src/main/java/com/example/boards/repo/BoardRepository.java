package com.example.boards.repo;

import com.example.boards.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository
        extends JpaRepository<Board, Long> {
}
