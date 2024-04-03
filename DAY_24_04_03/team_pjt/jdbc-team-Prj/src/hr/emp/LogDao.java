package hr.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;

import hr.DataSource;

public class LogDao implements ILogDao {

	@Override
	public void insertEnterLog(LogVo log) {
		// 입실
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "insert into attendance_log "
					+ "(log_id, enter_date, exit_date, stu_id) "
					+ "values(?, sysdate, null, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, log.getLogId());
			stmt.setInt(2, log.getStuId());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
	}

	@Override
	public void updateExitLog(LogVo log) {
		// 학생 퇴실
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "update attendance_log "
					+ "set exit_date=sysdate "
					+ "where log_id=?"
					+ "and stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, log.getLogId());
			stmt.setInt(2, log.getStuId());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		
		
	}

	@Override
	public void selectDayLog(int logId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectMonthLog(int logId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectStuLog(int logId, int stuId) {
		// TODO Auto-generated method stub
		
	}

}
