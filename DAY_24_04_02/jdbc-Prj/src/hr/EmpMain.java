package hr;

import java.util.List;

import hr.emp.EmpDao;
import hr.emp.EmpVo;
import hr.emp.IEmpDao;

public class EmpMain {
	public static void main(String[] args) {
		// UI(CUI) & 제어로직(if, switch 문 등...)
		
		IEmpDao dao = new EmpDao();
		
		// insert 추가 ---------------------------------------------------------------------
//		EmpVo emp = new EmpVo(300, "JinKyoung", "Heo", "HEOJK", "010", null, "IT_PROG",
//				7000, 0, 103, 60); // 현재 구성된 로직상 사원id와 이메일은 중복되면 안된다. Job id는 외래키로 Job 테이블을 참조중이기에 아무거나 쓰면 안된다. 매니저 id도 외래키이기에 존재하는 사원id를 써야한다. 부서id도 외래키이이게 존재하는 부서id를 써야한다.
//		dao.insertEmp(emp);
//		System.out.println("고객정보가 입력되었습니다.");
		
		// select 특정 사원 조회 ---------------------------------------------------------------------
//		System.out.println("고객의 정보를 조회합니다.");
//		try {
//			EmpVo emp = dao.selectEmp(300); // 사원정보가 있는 경우 => EmpVo(employeeId=300, firstName=JinKyoung, lastName=Heo, email=HEOJK, phoneNumber=010, hireDate=2024-04-02, jobId=IT_PROG, salary=7000.0, commissionPct=0.0, managerId=103, departmentId=60)
////			EmpVo emp = dao.selectEmp(400); // 사원정보가 없는 경우 => java.lang.RuntimeException: 조회한 사원의 정보가 없습니다.
//			System.out.println(emp);
//		}catch(RuntimeException e) {
//			System.out.println(e.getMessage());
//		}
		
		
		
		// update 특정 사원 수정 ---------------------------------------------------------------------
//		System.out.println("수정전 고객의 정보를 조회합니다.");
//		try {
//			EmpVo emp = dao.selectEmp(300);
//			System.out.println(emp);
//		}catch(RuntimeException e) {
//			System.out.println(e.getMessage());
//		}
//		
//		EmpVo updateEmp = new EmpVo();
//		updateEmp.setEmployeeId(300);
//		updateEmp.setSalary(9000);
//		dao.updateEmpSalary(updateEmp);
//		
//		System.out.println("수정된 고객의 정보를 조회합니다.");
//		try {
//			EmpVo emp = dao.selectEmp(300);
//			System.out.println(emp);
//		}catch(RuntimeException e) {
//			System.out.println(e.getMessage());
//		}
		
		
		
		// delete 특정 사원 삭제 ---------------------------------------------------------------------
		System.out.println("특정 고객의 정보를 삭제하기전 확인합니다.");
		try {
			EmpVo emp = dao.selectEmp(300);
			System.out.println(emp);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("특정 고객의 정보를 삭제합니다.");
//		dao.deleteEmp(300, "dfghjkl"); // 이메일을 저장된 사원의 이메일과 다른 이메일 입력
		dao.deleteEmp(300, "HEOJK"); // 이메일을 저장된 사원의 이메일 입력
		
		System.out.println("특정 고객의 정보를 삭제했습니다.");
		try {
			EmpVo emp = dao.selectEmp(300);
			System.out.println(emp); // 삭제가 되었다면 사원정보를 찾을 수 없다.
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
		
		
		// select 모든 사원 조회 ---------------------------------------------------------------------
//		System.out.println("모든 고객의 정보를 조회합니다.");
//		try {
//			List<EmpVo> empList = dao.selectAllEmps();
//			for(EmpVo emp : empList) {
//				System.out.println(emp);
//			}
//		}catch(RuntimeException e) {
//			System.out.println(e.getMessage());
//		}
	}
}
