#

mybatis provides the db operation for user

#DB init script
table my_user, id login_name password username
table my_role  id authority

# insert the init data to tables
 insert into 'my_role' ('id','authority') VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_DBA'), (3, 'ROLE_USER');
 
insert into my_user VALUES 
  (1, 'admin','$2a$10$oOO/sOXDUwFWt3yz8dgETuNu.hi.Wbfl0OFTtv6.Zvf93Rhu6nlTi','管理员'), (2, 'user1','$2a$10$5gfj2oA4s1q4x4fGYTucdOc0tXSVEIYqJqYhCOA49a5JK4AiKxVau','普通用户');
  insert into 'my_user_role' ('user_id','role_id') VALUES (1, 1), (1, 2), (2, 3);
 
 
 $2a$10$oOO/sOXDUwFWt3yz8dgETuNu.hi.Wbfl0OFTtv6.Zvf93Rhu6nlTi --admin
 $2a$10$5gfj2oA4s1q4x4fGYTucdOc0tXSVEIYqJqYhCOA49a5JK4AiKxVau --user1pw
