package hr.emp;

import java.util.List;

public interface IStuDao {
	// 학생 인터페이스
	
	// 학생 추가
	void insertStu(StuVo stu);
	
	// 학생 삭제
	void deleteStu(int employeeId, String stuName);
	
	// 학생 정보 수정
		// 전화번호 수정
	void updateStuPhoneNumber(StuVo stu);
		// 조 수정
	void updateStuGroup(StuVo stu);
		// 반 수정
	void updateStuClass(StuVo stu);
	
	// 학생 조회
	StuVo selectStu(int stuId);
	
	// 모든 학생 조회
	List<StuVo> selectAllStus();
}
