--NUMBER 타입 변수 선언
VARIABLE var_out NUMBER;
-- 프로시저 실행
EXEC GETSALARY(103, :var_out);
--결과 출력
print var_out;