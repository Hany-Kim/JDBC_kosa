package hr.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	public List<CheckLogVo> selectDayLog(int stuId, String day) {
		// 일 단위 조회
		Connection con = null;
		List<CheckLogVo> checkLogList = new ArrayList<>();
		try {
			con = DataSource.getConnection();
			String sql = "select s.stu_id, s.stu_name, s.stu_class, l.enter_date, l.exit_date "
					+ "from attendance_log l "
					+ "join students s "
					+ "on l.stu_id = s.stu_id "
//					+ "where l.enter_date LIKE '%/%/? %' "
					+ "where to_char(l.enter_date, 'YYYY/MM/DD HH24:MI:SS') LIKE ? "
					+ "and l.stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, day);
			stmt.setInt(2, stuId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CheckLogVo cl = new CheckLogVo();
				cl.setStuId(rs.getInt("s.stu_id"));
				cl.setStuName(rs.getString("s.stu_name"));
				cl.setStuClass(rs.getString("s.stu_class"));
				cl.setEnterDate(rs.getDate("l.enter_date"));
				cl.setExitDate(rs.getDate("l.exit_date"));
				
				checkLogList.add(cl);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return checkLogList;
	}

	@Override
	public List<CheckLogVo> selectMonthLog(int stuId, String month) {
		// 월 단위 조회
		Connection con = null;
		List<CheckLogVo> checkLogList = new ArrayList<>();
		try {
			con = DataSource.getConnection();
			String sql = "select s.stu_id, s.stu_name, s.stu_class, l.enter_date, l.exit_date "
					+ "from attendance_log l "
					+ "join students s "
					+ "on l.stu_id = s.stu_id "
					+ "where l.enter_date LIKE '%/?/%' "
					+ "and l.stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, month);
			stmt.setInt(2, stuId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				CheckLogVo cl = new CheckLogVo();
				cl.setStuId(rs.getInt("s.stu_id"));
				cl.setStuName(rs.getString("s.stu_name"));
				cl.setStuClass(rs.getString("s.stu_class"));
				cl.setEnterDate(rs.getDate("l.enter_date"));
				cl.setExitDate(rs.getDate("l.exit_date"));
				
				checkLogList.add(cl);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return checkLogList;
	}

	@Override
	public List<CheckLogVo> selectStuLog(int logId, int stuId) {
		// TODO Auto-generated method stub
		return null;
	}

}
