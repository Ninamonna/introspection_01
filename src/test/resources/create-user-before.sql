delete from user_role;
delete from usr;

insert into usr(id, username, password, active) values
(1, 'admin', '$2a$08$/hqJIsjnACJ11oa7iFXht.55NrvOVbRb88g/5MZ6OpG8CRytEUK9K', true),
(2, 'like', '$2a$08$aZPxpcdO6KCXx8LqPzdasOqRSMCqUl25/F7FBLJzfgWyNrgnu65wK', true);

insert into user_role(user_id, roles) values
(1, 'ADMIN'), (1, 'USER'),
(2, 'USER');