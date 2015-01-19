create or replace PROCEDURE update1 (semester IN sem_batch.sem%TYPE ) AS 
cursor p is
select std_id,batch_id from student_Detail where batch_id like (semester||'%') ;
no_std number(3); 
BEGIN 
select count(std_id) into no_std from student_Detail where batch_id like (semester||'%') ;

for no_std in p loop
update student_detail set batch_id= (no_std.batch_id+100) where student_Detail.std_id=no_std.std_id;
end loop;

End;
/