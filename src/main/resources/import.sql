
INSERT INTO perfiles ( birth_date, first_name, last_name) VALUES( '2022-01-01 00:00:00.000', 'gus', 'mar');

INSERT INTO direcciones ( city, "number", street, perfil_id) VALUES( 'bogota', '123456', 'cundi', 1);

INSERT INTO usuarios (username, password, perfil_id) VALUES( 'admin', '$2a$10$tNkIoSb9KvjGfyktOv30ren99dLumG.q0arP.8KwwGJUpwgbQeR8u', 1);
INSERT INTO usuarios (username, password, perfil_id) VALUES( 'user', '$2a$10$qxGJf7am2lCoJY1s9qv6c.UzpyFA0o9Tef3jsp.IK/YCZHMXNHa42', 1);

INSERT INTO roles ( nombre) VALUES( 'admins');
INSERT INTO roles( nombre) VALUES('users');
INSERT INTO roles ( nombre) VALUES('supervisors');

INSERT INTO usuarios_en_role ( role_id, usuario_id) VALUES( 1, 1);
INSERT INTO usuarios_en_role ( role_id, usuario_id) VALUES(2, 2);



