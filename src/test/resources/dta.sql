INSERT INTO gift_certificate (id, name, description, price, creationTime, updateTime, duration) VALUES (6, 'test', 'test', 2.50, '2020-01-01 03:55:55', '2020-01-01 03:55:55', 5);
INSERT INTO gift_certificate (id, name, description, price, creationTime, updateTime, duration) VALUES (7, 'test2', 'descr t2', 2.20, '2020-10-28 12:28:29', '2020-10-28 12:28:31', 25);

INSERT INTO tag (id, name) VALUES (11, '123');
INSERT INTO tag (id, name) VALUES (3, 'activity');
INSERT INTO tag (id, name) VALUES (10, 'diving');
INSERT INTO tag (id, name) VALUES (7, 'extrim');
INSERT INTO tag (id, name) VALUES (8, 'food');
INSERT INTO tag (id, name) VALUES (6, 'massage');
INSERT INTO tag (id, name) VALUES (2, 'relax');
INSERT INTO tag (id, name) VALUES (5, 'sea');
INSERT INTO tag (id, name) VALUES (1, 'spa');
INSERT INTO tag (id, name) VALUES (4, 'water');


INSERT INTO gift_to_tag (cert_id, id, tag_id) VALUES (6, 1, 11);
INSERT INTO gift_to_tag (cert_id, id, tag_id) VALUES (7, 2, 1);
INSERT INTO gift_to_tag (cert_id, id, tag_id) VALUES (7, 3, 4);

