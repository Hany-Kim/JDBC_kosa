package hr.emp;

import java.util.List;

public interface IEmpDao {
	// 사원 관리 시스템라면 필요한 기능?
	
	// 기능 - CRUD(관리)
	
	// 사원정보 입력, 수정, 삭제, 조회, 전체 조회
	// 기능 구현을 위한 메서드 정의 (실제 구현인 class)
	void insertEmp(EmpVo emp);
	void updateEmpSalary(EmpVo emp);
//	void updateEmpSalary(int employeeId, double salary);
	void deleteEmp(int employeeId, String email); // 사원 id만 입력받아서 삭제하면, 실수할 수 있으니, 복합키의 형태로 이메일까지 인자로 받기
	EmpVo selectEmp(int employeeId);
	List<EmpVo> selectAllEmps();
}
