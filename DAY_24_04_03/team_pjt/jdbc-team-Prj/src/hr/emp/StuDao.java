package hr.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hr.DataSource;

public class StuDao implements IStuDao {

	@Override
	public void insertStu(StuVo stu) {
		// 학생 추가
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "insert into students (stu_id, stu_name, stu_class, stu_group, phone_number) "
					+ "values (?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, stu.getStuId());
			stmt.setString(2, stu.getStuName());
			stmt.setString(3, stu.getStuClass());
			stmt.setInt(4, stu.getStuGroup());
			stmt.setString(5, stu.getPhoneNumber());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw new RuntimeException("학생 추가에 실패했습니다.");
//			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
	}

	@Override
	public void deleteStu(int employeeId, String stuName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStuPhoneNumber(StuVo stu) {
		// 학생 전화번호 수정
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "update students "
					+ "set phone_number=? "
					+ "where stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, stu.getPhoneNumber());
			stmt.setInt(2, stu.getStuId());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
	}

	@Override
	public void updateStuGroup(StuVo stu) {
		// 학생 조 수정
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "update students "
					+ "set stu_group=? "
					+ "where stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, stu.getStuGroup());
			stmt.setInt(2, stu.getStuId());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
	}

	@Override
	public void updateStuClass(StuVo stu) {
		// 학생 반 수정
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "update students "
					+ "set stu_class=? "
					+ "where stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, stu.getStuClass());
			stmt.setInt(2, stu.getStuId());
			stmt.executeUpdate();
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
	}

	@Override
	public StuVo selectStu(int stuId) {
		// 학생 조회
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "select stu_id, stu_name, stu_class, stu_group, phone_number "
					+ "from students where stu_id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, stuId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				StuVo stu = new StuVo();
				stu.setStuId(rs.getInt("stu_id"));
				stu.setStuName(rs.getString("stu_name"));
				stu.setStuClass(rs.getString("stu_class"));
				stu.setStuGroup(rs.getInt("stu_group"));
				stu.setPhoneNumber(rs.getString("phone_number"));
				
				return stu;
			} else {
				throw new RuntimeException("조회한 학생 정보가 없습니다.");
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		
	}

	@Override
	public List<StuVo> selectAllStus() {
		// 모든 학생 정보 조회
		Connection con = null;
		List<StuVo> stuList = new ArrayList<>();
		try {
			con = DataSource.getConnection();
			String sql = "select stu_id, stu_name, stu_class, stu_group, phone_number "
					+ "from students";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				StuVo stu = new StuVo();
				stu.setStuId(rs.getInt("stu_id"));
				stu.setStuName(rs.getString("stu_name"));
				stu.setStuClass(rs.getString("stu_class"));
				stu.setStuGroup(rs.getInt("stu_group"));
				stu.setPhoneNumber(rs.getString("phone_number"));
				
				stuList.add(stu);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con);
		}
		return stuList;
	}

}
