package hr.emp;

public interface ILogDao {
	// 입실
	void insertEnterLog(LogVo log);
	
	// 퇴실
	void updateExitLog(LogVo log);
	
	// 조회
		// 일
	void selectDayLog(int logId);
		// 월
	void selectMonthLog(int logId);
		// 사람
	void selectStuLog(int logId, int stuId);
}
