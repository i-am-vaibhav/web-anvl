insert into TBL_USER(id,email,username,designation,password) values(1000,'vg@anvl.com','vaibhav','SDE-2','$2y$10$5KlDzbZYxKVa.R21d2XwMefVZX2krdX27aCehQZq/dm2u3NlKX1JC');
insert into TBL_USER(id,email,username,designation,password) values(1001,'prinsee@gmail.com','prinsee','Trainee','$2y$10$5KlDzbZYxKVa.R21d2XwMefVZX2krdX27aCehQZq/dm2u3NlKX1JC');
insert into TBL_USER(id,email,username,designation,password) values(1002,'anshul@gmail.com','anshul','Trainee','$2y$10$5KlDzbZYxKVa.R21d2XwMefVZX2krdX27aCehQZq/dm2u3NlKX1JC');

insert into TBL_ROLE(id,name,description) values(1,'ADMIN',null);
insert into TBL_ROLE(id,name,description) values(2,'USER',null);

insert into TBL_USER_ROLE(user_id,role_id) values(1000,1);
insert into TBL_USER_ROLE(user_id,role_id) values(1001,1);
insert into TBL_USER_ROLE(user_id,role_id) values(1002,2);