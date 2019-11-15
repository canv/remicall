delete from message;

insert into message(id, tag, text, user_id) values
(1, 'first message', 'tag one', '4a53428d-6d71-4a11-ae2b-12416ddaa553'),
(2, 'second message', 'tag two', '4a53428d-6d71-4a11-ae2b-12416ddaa553'),
(3, 'third message', 'tag three', '4a53428d-6d71-4a11-ae2b-12416ddaa553'),
(4, 'fourth message', 'tag four', '4a53428d-6d71-4a11-ae2b-12416ddaa553');

alter sequence hibernate_sequence restart with 10;

