INSERT INTO backend."tb_user" ("username", "email", "password")
VALUES ('admin', 'admin@gmail.com', '{bcrypt}$2a$10$qDlX.9kMceieVr0WHhqpuutTOaRxvCTQosqXHJ3d/ssI1ts7faZ6O'),
       ('mcdd', 'mcdd@gmail.com', '{bcrypt}$2a$10$d5faMWj3z9vzYch3AKAUvurF2z.y8Xp0uUT9ku6DpFA0jZgKbv8om');
-- admin-pwd {bcrypt}$2a$10$qDlX.9kMceieVr0WHhqpuutTOaRxvCTQosqXHJ3d/ssI1ts7faZ6O
-- mcdd-pwd {bcrypt}$2a$10$d5faMWj3z9vzYch3AKAUvurF2z.y8Xp0uUT9ku6DpFA0jZgKbv8om
INSERT INTO backend."tb_role" ("code")
VALUES ('ADMIN'),
       ('GENERAL');

INSERT INTO backend."tb_permission" ("code")
VALUES ('ADD'),
       ('DELETE'),
       ('UPDATE'),
       ('SELECT');

INSERT INTO backend."user_role_map" ("user_id", "role_id")
VALUES (1, 1),
       (2, 2);

INSERT INTO backend."role_permission_map" ("role_id", "permission_id")
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 4);
