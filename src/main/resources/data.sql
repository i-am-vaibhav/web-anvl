insert into TBL_USER(id,email,username,designation,password) values(1000,'vg@anvl.com','vaibhav','SDE-2','$2a$10$p3PHnpoBAnzZiL8mr3gMieMhVVSb585ajC2ZsBB0kwb4KvZKFSdNi');
insert into TBL_USER(id,email,username,designation,password) values(1001,'prinsee@gmail.com','prinsee','Trainee','$2a$10$p3PHnpoBAnzZiL8mr3gMieMhVVSb585ajC2ZsBB0kwb4KvZKFSdNi');
insert into TBL_USER(id,email,username,designation,password) values(1002,'anshul@gmail.com','anshul','Trainee','$2a$10$p3PHnpoBAnzZiL8mr3gMieMhVVSb585ajC2ZsBB0kwb4KvZKFSdNi');

insert into TBL_ROLE(id,name,description) values(1,'ADMIN',null);
insert into TBL_ROLE(id,name,description) values(2,'USER',null);

insert into TBL_USER_ROLE(user_id,role_id) values(1000,1);
insert into TBL_USER_ROLE(user_id,role_id) values(1001,1);
insert into TBL_USER_ROLE(user_id,role_id) values(1002,2);