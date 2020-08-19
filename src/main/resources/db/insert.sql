INSERT INTO authorities(id, authority)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities(id, authority)
VALUES (2, 'ROLE_USER');

INSERT INTO users(enabled, password, username, authority_id)
VALUES (TRUE, '$2a$10$qjRKBlcn4tk5881ukK9NBuz9BuFLxwPO648gCOeuJPepZKYmt657C', 'user', 2);
INSERT INTO users(enabled, password, username, authority_id)
VALUES (TRUE, '$2a$10$Sc3P8OPN.JY3SLOFTtKePuso.wnlmTkXoiokhA5jMz0rc.B8mUAD6', 'ololo228', 2);
INSERT INTO users(enabled, password, username, authority_id)
VALUES (TRUE, '$2a$10$EQMdTgEy/VfBC7CY3vXVSOleFPZzc53f4FRKQw0xsa9maJT.RMCJu', 'AzazinKrit', 2);
INSERT INTO users(enabled, password, username, authority_id)
VALUES (TRUE, '$2a$10$z.twjkuVNItW.PmaipLoruUMjgsk76DtcRJqaFqOAzsvGcDwV2LyS', 'JohnKick', 2);

INSERT INTO post(created, description, name, user_id)
VALUES ('2020-08-08', 'Как лучше форматировать код. Использовать tab или четыре пробела?',
        'tab или 4 проблема?', 2);
INSERT INTO post(created, description, name, user_id)
VALUES ('2020-08-08', 'Создаст ли Шаоми новую секту, которая победит секту любителей айфона?',
        'Шаоми ми 10 - это топ за свои деньги и убийца айфона?', 3);

INSERT INTO message(created, text, post_id, user_id)
VALUES ('2020-08-09',
        'tab быстрее',
        1, 2);
INSERT INTO message(created, text, post_id, user_id)
VALUES ('2020-08-09',
        '    Афтор плохо знает русский языка, где знаки препинания. Я ничего не поняль.
     В каких это тестах айфон проигрывает шаоми?',
        2, 1);
INSERT INTO message(created, text, post_id, user_id)
VALUES ('2020-08-09',
        'Когда нечего написать, то пишут про ошибки в орфографии. В AnTuTu шаоми ми 10 рвет андроид. Можно играть в ыыыыыыгры на телефоне.',
        2, 2);
INSERT INTO message(created, text, post_id, user_id)
VALUES ('2020-08-09',
        'Ошибся шаоми рвет не андроид, а айфон. Как редактировать сообщения?',
        2, 2);
INSERT INTO message(created, text, post_id, user_id)
VALUES ('2020-08-09',
        'Что за трольский пост? Где модераторы?',
        2, 3);

