TRUNCATE TABLE member;
INSERT INTO member(username, password, name, remark)
VALUES
    ('skku_student1','test1','학생1','테스트 학생'),
    ('skku_student2','test2','학생2','테스트 학생');

SET FOREIGN_KEY_CHECKS=0;

TRUNCATE TABLE board;
INSERT INTO board(id, title, remark)
VALUES
    (1,'자유게시판','이 게시판은 자유게시판입니다. 매너와 규칙 지켜주세요'),
    (2,'질문과 답변','이곳은 질문과 답변 게시판입니다. 매너와 규칙을 지켜주세요');

TRUNCATE TABLE student;
INSERT INTO student (github_id, name, semester)
VALUES ('SeoJeongYeop', '서정엽', 6);

TRUNCATE TABLE github_data;
INSERT INTO github_data (commit_count, follower_count, following_count, github_id,
                         issue_count, pr_count, repo_count, star_count, year)
VALUES (100, 1, 1, 'SeoJeongYeop', 10, 1, 10, 1, 2023);

TRUNCATE TABLE post;
INSERT INTO post (title, author, content, created_date, modified_date, board_id)
VALUES
    ('title', 'author', 'content', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000', 1),
    ('title2', 'author2', 'content2', '2023-01-11T00:00:00.000000', '2023-01-11T00:00:00.000000', 1);

SET FOREIGN_KEY_CHECKS=1;