-- DROP TABLE  if exists member;
-- CREATE TABLE if NOT exists member;
INSERT INTO member(username, password, name, remark)
VALUES ('skku_student1', 'test1', '학생1', '테스트 학생'),
       ('skku_student2', 'test2', '학생2', '테스트 학생');

-- SET FOREIGN_KEY_CHECKS=0;
-- DROP TABLE  if exists board;
-- CREATE TABLE if NOT exists board;
INSERT INTO board(id, title, remark, created_date, modified_date)
VALUES (1, '자유게시판', '이 게시판은 자유게시판입니다. 매너와 규칙 지켜주세요', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       (2, '질문과 답변', '이곳은 질문과 답변 게시판입니다. 질문은 되도록 자세하게 써주세요', '2023-01-10T00:00:00.000000',
        '2023-01-10T00:00:00.000000'),
       (3, '정보게시판', '이곳은 정보게시판입니다. 개발에 도움이 될만한 정보를 올려주세요', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       (4, '홍보게시판', '이곳은 홍보게시판입니다. 대회, 부트캠프, 인턴, 기타 행사 등 많은 사람들이 알았으면 하는 내용을 올려주세요', '2023-01-10T00:00:00.000000',
        '2023-01-10T00:00:00.000000');
-- DROP TABLE  if exists student;
-- CREATE TABLE if NOT exists student;
INSERT INTO student (github_id, name, semester)
VALUES ('SeoJeongYeop', '서정엽', 6);

-- DROP TABLE  if exists team;
-- CREATE TABLE if NOT exists team;
INSERT INTO team (name, status, description, picture, created_date, modified_date, user_id)
VALUES ('2023 SW코칭 프로그램 - 스프링프레임워크', 'NORMAL', '스프링프레임워크로 웹애플리케이션 개발실무를 익히고 코드리뷰를 진행',
        '/images/default-team.png', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000',(SELECT id FROM user_entity WHERE name='서정엽')),
       ('2023 SW코칭 프로그램 - GitHub, DevOps', 'BLOCK', 'GitHub를 통해서 배워보는 기업용 파일 버전관리, 데브옵스 (DevOps)',
        '/images/default-team.png', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000',(SELECT id FROM user_entity WHERE name='서정엽')),
       ('SSA', 'NORMAL', 'SW중심대학사업에 필요한 웹 서비스 개발 및 운영에 참여 (서비스 기획 및 디자인, 웹 서비스 프론트엔드 및 백엔드 개발 및 운영, 인프라 보안 운영, 콘텐츠 운영)',
        '/images/ssa.png', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000', (SELECT id FROM user_entity WHERE name='서정엽'));

-- DROP TABLE  if exists github_data;
-- CREATE TABLE if NOT exists github_data;
INSERT INTO github_data (commit_count, follower_count, following_count, github_id,
                         issue_count, pr_count, repo_count, star_count, nyear)
VALUES (100, 1, 1, 'SeoJeongYeop', 10, 1, 10, 1, 2023);

-- DROP TABLE  if exists 'user';
-- CREATE TABLE if NOT exists 'user';
INSERT INTO user_entity (email, name, picture, role, created_date, modified_date)
VALUES ('sjyskku99@g.skku.edu', '서정엽', 'https://avatars.githubusercontent.com/u/41911523?s=40&v=4', 'ADMIN',
        '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000');

-- DROP TABLE  if exists post;
-- CREATE TABLE if NOT exists post;
INSERT INTO post (title, author, content, created_date, modified_date, board_id, user_id)
VALUES ('Hello World!!', '운영자', '자유게시판의 첫글입니다.', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000', 1, (SELECT id FROM user_entity WHERE name='서정엽')),
       ('필요한 기능이 있나요?', '운영자', '필요한 기능이 있으면 댓글을 달아주세요. 댓글 기능도 곧 만들 거예요.', '2023-01-11T00:00:00.000000',
        '2023-01-11T00:00:00.000000', 2, (SELECT id FROM user_entity WHERE name='서정엽'));

-- SET FOREIGN_KEY_CHECKS=1;
