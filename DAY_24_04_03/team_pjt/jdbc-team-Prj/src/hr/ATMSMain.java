package hr;

import hr.emp.StuDao;
import hr.emp.StuVo;
import hr.emp.IStuDao;
import hr.emp.LogDao;
import hr.emp.LogVo;

import java.util.List;

import hr.emp.CheckLogVo;
import hr.emp.ILogDao;

public class ATMSMain {
	// ATtendace Manage System
	public static void main(String[] args) {
		IStuDao stuDao = new StuDao();
		ILogDao logDao = new LogDao();
		
//		insert 학생 추가
//		StuVo stu = new StuVo(20247, "홍길동", null, 0, "010-0000-0000");
//		addStudent(stu);
		
//		select 특정 학생 조회
//		searchStudent(20241);

//		select 모든 학생 조회
//		searchAllStudent();
		
//		update 특정 학생 정보 수정
//		changeStudentClassName(20241, "lux");
//		changeStudentGroup(20241, 55);		
//		changeStudentPhoneNumber(20241, "010-2222-2222");
		
		
//		insert 학생 입실
//		LogVo log = new LogVo(2, null, null, 20242);
//		enterStudent(log);
		
//		update 학생 퇴실
//		exitStudent(2, 20242);
		
//		select 일 단위 학생 조회
		searchDayLog(20241, "03");
//		searchMonthLog(20241, "04");
		
	}
	
	private static void addStudent(StuVo stu) {
		// insert 학생 추가
		IStuDao stuDao = new StuDao();
		stuDao.insertStu(stu);
		System.out.println("신규 학생정보가 입력되었습니다.");
	}
	
	private static void searchStudent(int stuId) {
		// select 특정 사원 조회
		System.out.println("학생의 정보를 조회합니다.");
		IStuDao stuDao = new StuDao();
		try {
			StuVo stu = stuDao.selectStu(stuId);
//			System.out.println(stu);
			printStudentForm(stu);
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void searchAllStudent() {
		System.out.println("모든 학생의 정보를 조회합니다.");
		IStuDao stuDao = new StuDao();
		try {
			List<StuVo> stuList = stuDao.selectAllStus();
			for(StuVo stu : stuList) {
//				System.out.println(stu);
				printStudentForm(stu);
			}
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void printStudentForm(StuVo stu) {
		System.out.print("[학번 : " + stu.getStuId() + " | ");
		System.out.print("이름 : " + stu.getStuName() + " | ");
		System.out.print("반 : " + stu.getStuClass() + " | ");
		System.out.print("조 : " + stu.getStuGroup() + " | ");
		System.out.println("핸드폰번호 : " + stu.getPhoneNumber() + " ]");
	}
	
	private static void changeStudentPhoneNumber(int stuId, String pn) {
		System.out.print("수정전 ");
		searchStudent(stuId);
		
		IStuDao stuDao = new StuDao();
		StuVo updateStu = new StuVo();
		updateStu.setStuId(stuId);
		updateStu.setPhoneNumber(pn);
		stuDao.updateStuPhoneNumber(updateStu);
		
		System.out.print("수정된 ");
		searchStudent(stuId);
	}
	
	private static void changeStudentGroup(int stuId, int gn) {
		System.out.print("수정전 ");
		searchStudent(stuId);
		
		IStuDao stuDao = new StuDao();
		StuVo updateStu = new StuVo();
		updateStu.setStuId(stuId);
		updateStu.setStuGroup(gn);
		stuDao.updateStuGroup(updateStu);
		
		System.out.print("수정된 ");
		searchStudent(stuId);
	}
	
	private static void changeStudentClassName(int stuId, String cn) {
		System.out.print("수정전 ");
		searchStudent(stuId);
		
		IStuDao stuDao = new StuDao();
		StuVo updateStu = new StuVo();
		updateStu.setStuId(stuId);
		updateStu.setStuClass(cn);
		stuDao.updateStuClass(updateStu);
		
		System.out.print("수정된 ");
		searchStudent(stuId);
	}
	
	private static void enterStudent(LogVo log) {
		// insert 학생 입실
		ILogDao logDao = new LogDao();
		logDao.insertEnterLog(log);
		System.out.println("학생이 입실 했습니다.");
	}
	private static void exitStudent(int logId, int stuId) {
		// update 학생 입실
		ILogDao logDao = new LogDao();
		LogVo updateLog = new LogVo();
		updateLog.setLogId(logId);
		updateLog.setStuId(stuId);
		logDao.updateExitLog(updateLog);
		System.out.println("학생이 퇴실 했습니다.");
	}
	
	private static void searchDayLog(int stuId, String day) {
		System.out.println(day + "일 출결 정보를 조회합니다.");
		ILogDao logDao = new LogDao();
		try {
			List<CheckLogVo> clList = logDao.selectDayLog(stuId, day);
			for(CheckLogVo cl : clList) {
				printLog(cl);
			}
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void searchMonthLog(int stuId, String month) {
		System.out.println(month + "월 출결 정보를 조회합니다.");
		ILogDao logDao = new LogDao();
		try {
			List<CheckLogVo> clList = logDao.selectMonthLog(stuId, month);
			for(CheckLogVo cl : clList) {
				printLog(cl);
			}
		}catch(RuntimeException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void printLog(CheckLogVo cl) {
		System.out.print("[학번 : " + cl.getStuId() + " | ");
		System.out.print("이름 : " + cl.getStuName() + " | ");
		System.out.print("반 : " + cl.getStuClass() + " | ");
		System.out.print("입실 시간 : " + cl.getEnterDate() + " | ");
		System.out.println("퇴실 시간 : " + cl.getExitDate() + " ]");
	}
}
