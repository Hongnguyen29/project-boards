INSERT INTO board (id, name) SELECT 1, '자유 게시판'
    WHERE NOT EXISTS (SELECT 1 FROM board WHERE id = 1);

INSERT INTO board (id, name) SELECT 2, '개발 게시판'
    WHERE NOT EXISTS (SELECT 1 FROM board WHERE id = 2);

INSERT INTO board (id, name) SELECT 3, '일상생활 게시판'
    WHERE NOT EXISTS (SELECT 1 FROM board WHERE id = 3);

INSERT INTO board (id, name) SELECT 4, '사고사건 게시판'
    WHERE NOT EXISTS (SELECT 1 FROM board WHERE id = 4);