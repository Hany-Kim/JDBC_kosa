package hr.emp;

import java.util.List;

public interface ILogDao {
	// 입퇴실 로그 인터페이스
	// 입실
	void insertEnterLog(LogVo log);
	
	// 퇴실
	void updateExitLog(LogVo log);
	
	// 조회
		// 일
	List<CheckLogVo> selectDayLog(int stuId, String day);
		// 월
	List<CheckLogVo> selectMonthLog(int stuId, String month);
		// 사람
	List<CheckLogVo> selectStuLog(int logId, int stuId);
}
