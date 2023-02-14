--TRUNCATE TABLE 'user';
INSERT INTO app_user (email, name, picture, role, created_date, modified_date)
VALUES ('sjyskku99@g.skku.edu', '소프트웨어학과/서정엽', 'https://avatars.githubusercontent.com/u/41911523?s=40&v=4', 'ADMIN',
        '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       ('sample1@gmail.com', '김서준', 'https://loremflickr.com/150/150', 'USER',
        '2023-01-20T00:00:00.000000', '2023-01-21T00:00:00.000000'),
       ('sample2@gmailcom', '최하준', 'https://loremflickr.com/140/140', 'USER',
        '2023-01-22T00:00:00.000000', '2023-01-30T00:00:00.000000'),
       ('sample3@gmail.com', '도하윤', 'https://loremflickr.com/160/160', 'USER',
        '2023-02-09T00:00:00.000000', '2023-02-10T00:00:00.000000');

-- SET FOREIGN_KEY_CHECKS=0;

--TRUNCATE TABLE board;
INSERT INTO board(id, title, remark, created_date, modified_date)
VALUES (1, '자유게시판', '이 게시판은 자유게시판입니다. 매너와 규칙 지켜주세요', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       (2, '질문과 답변', '이곳은 질문과 답변 게시판입니다. 질문은 되도록 자세하게 써주세요', '2023-01-10T00:00:00.000000',
        '2023-01-10T00:00:00.000000'),
       (3, '정보게시판', '이곳은 정보게시판입니다. 개발에 도움이 될만한 정보를 올려주세요', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       (4, '홍보게시판', '이곳은 홍보게시판입니다. 대회, 부트캠프, 인턴, 기타 행사 등 많은 사람들이 알았으면 하는 내용을 올려주세요', '2023-01-10T00:00:00.000000',
        '2023-01-10T00:00:00.000000');

--TRUNCATE TABLE team;
INSERT INTO team (name, status, description, picture, created_date, modified_date, user_id)
VALUES ('2023 SW코칭 프로그램 - 스프링프레임워크', 'NORMAL', '스프링프레임워크로 웹애플리케이션 개발실무를 익히고 코드리뷰를 진행',
        '/images/default-team.png', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000',
        (SELECT id FROM app_user WHERE email = 'sjyskku99@g.skku.edu')),
       ('2023 SW코칭 프로그램 - GitHub, DevOps', 'BLOCK', 'GitHub를 통해서 배워보는 기업용 파일 버전관리, 데브옵스 (DevOps)',
        '/images/default-team.png', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000',
        (SELECT id FROM app_user WHERE email = 'sjyskku99@g.skku.edu')),
       ('SSA', 'NORMAL', 'SW중심대학사업에 필요한 웹 서비스 개발 및 운영에 참여 (서비스 기획 및 디자인, 웹 서비스 프론트엔드 및 백엔드 개발 및 운영, 인프라 보안 운영, 콘텐츠 운영)',
        '/images/ssa.png', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000',
        (SELECT id FROM app_user WHERE email = 'sjyskku99@g.skku.edu')),
       ('시나브로', 'NORMAL', '같이 여행가자',
        '/images/default-team.png', '2023-01-25T00:00:00.000000', '2023-01-25T00:00:00.000000',
        (SELECT id FROM app_user WHERE email = 'sample1@gmail.com'));

--TRUNCATE TABLE github_data;
INSERT INTO github_data (commit_count, follower_count, following_count, github_username,
                         issue_count, pr_count, repo_count, star_count, date_year)
VALUES (100, 1, 1, 'SeoJeongYeop', 10, 1, 10, 6, 2023),
       (75, 3, 4, 'seojoon', 3, 2, 7, 5, 2023),
       (63, 2, 6, 'choihj', 5, 1, 4, 4, 2023),
       (83, 6, 5, 'dhdh1557', 5, 7, 12, 7, 2023);

--TRUNCATE TABLE user_team_relation;
INSERT INTO user_team_relation (user_id, team_id, relation_status, created_date, modified_date)
VALUES (1, 3, 'IN', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       (3, 3, 'IN', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000'),
       (2, 4, 'IN', '2023-01-26T00:00:00.000000', '2023-01-26T00:00:00.000000'),
       (3, 4, 'IN', '2023-01-27T00:00:00.000000', '2023-01-27T00:00:00.000000'),
       (4, 4, 'IN', '2023-01-28T00:00:00.000000', '2023-01-28T00:00:00.000000');

--TRUNCATE TABLE post;
INSERT INTO post (title, author, content, created_date, modified_date, board_id, user_id)
VALUES ('Hello World!!', '운영자', '자유게시판의 첫글입니다.', '2023-01-10T00:00:00.000000', '2023-01-10T00:00:00.000000', 1,
        (SELECT id FROM app_user WHERE name = '서정엽')),
       ('필요한 기능이 있나요?', '운영자', '필요한 기능이 있으면 댓글을 달아주세요. 댓글 기능도 곧 만들 거예요.', '2023-01-11T00:00:00.000000',
        '2023-01-11T00:00:00.000000', 2, (SELECT id FROM app_user WHERE email = 'sjyskku99@g.skku.edu')),
       ('휴학에 대해 진지한 조언을 해주세요', '최하준',
        '2학년 끝나고 휴학하려고 하는데 지금 목표가 어학자격증이랑 아르바이트해서 돈을 모으는 것입니다. 돈을 조금 많이 모아야 해서 한학기 휴학하고 복학하고 다시 휴학을 할 까요? 아니면 한꺼번에 1년을 휴학 할까요?',
        '2023-01-24T00:00:00.000000', '2023-01-24T00:00:00.000000', 2,
        (SELECT id FROM app_user WHERE name = '최하준')),
       ('팝송 추천해줘', '도하윤', '비트 강한 팝송 없을까? 가볍게 신나는 것보다는 묵직하게 신났으면해. 락이랑 밴드 좋아해. ', '2023-02-10T00:00:00.000000',
        '2023-02-10T00:00:00.000000', 1,
        (SELECT id FROM app_user WHERE name = '도하윤')),
       ('배낭 여행 해본 사람 있어?', '김서준',
        '나 처음으로 유럽 단독 여행을 계획하고 있는데 조금 긴장된다. 혼자 여행하면서, 안전하게 여행하는 팁이 있나요? 그리고 저렴한 가격대의 여행지나 꼭 가봐야 할 관광지를 추천해줘',
        '2023-02-12T00:00:00.000000', '2023-02-12T00:00:00.000000', 1,
        (SELECT id FROM app_user WHERE name = '김서준'));

-- SET FOREIGN_KEY_CHECKS=1;
