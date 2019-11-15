delete from user_role;
delete from user_data;

insert into user_data (user_id, active, username, password)
    values ('4a53428d-6d71-4a11-ae2b-12416ddaa553', true, 'user',
            '$2a$08$VUP7vIXjigHgvOiRb9i4MeZqjQNNDlZwoGqbFSH/ivF48pvvBiCR2'),
           ('411c8960-047f-4d15-bccf-1397d9f00d06', true, 'u',
            '$2a$08$110JCxkGH8wiDfYbGLVNv.CnNKoVeiDwNxEeEjoiFZ4LAKhtO31um');

insert into user_role (user_id, roles)
    values  ('4a53428d-6d71-4a11-ae2b-12416ddaa553', 'USER'),
            ('411c8960-047f-4d15-bccf-1397d9f00d06', 'USER'),
            ('411c8960-047f-4d15-bccf-1397d9f00d06', 'ADMIN');