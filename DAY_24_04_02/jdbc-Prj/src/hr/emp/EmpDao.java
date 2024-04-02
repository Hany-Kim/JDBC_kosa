package hr.emp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hr.DataSource;

public class EmpDao implements IEmpDao {

	@Override
	public void insertEmp(EmpVo emp) {
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "insert into employees (employee_id, first_name, last_name, email,"
					+ "phone_number, hire_date, job_id, salary, commission_pct, manager_id,"
					+ "department_id) values (?, ?, ?, ?, ?, sysdate, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql); // sql문 에외 발생 => try~catch
			stmt.setInt(1, emp.getEmployeeId());
			stmt.setString(2, emp.getFirstName());
			stmt.setString(3, emp.getLastName());
			stmt.setString(4, emp.getEmail());
			stmt.setString(5, emp.getPhoneNumber());
			stmt.setString(6, emp.getJobId());
			stmt.setDouble(7, emp.getSalary());
			stmt.setDouble(8, emp.getCommissionPct());
			stmt.setInt(9, emp.getManagerId());
			stmt.setInt(10, emp.getDepartmentId());
			stmt.executeUpdate(); // return type => int(변경된 행의 수)
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con); // 반환 시 사용했던 커넥션을 그대로 반환해주어야 한다.
		}
	}

	@Override
	public void updateEmpSalary(EmpVo emp) {
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "update employees set salary=? where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql); // sql문 에외 발생 => try~catch
			stmt.setDouble(1, emp.getSalary());
			stmt.setInt(2, emp.getEmployeeId());
			stmt.executeUpdate(); // update sql문 실행
			
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con); // 반환 시 사용했던 커넥션을 그대로 반환해주어야 한다.
		}
	}

	@Override
	public void deleteEmp(int employeeId, String email) {
		Connection con = null;
		try {
			con = DataSource.getConnection();
			con.setAutoCommit(false); // 자동 커밋은 커넥션 연결되자마자 꺼주어야 함. 늦게 처리하면 처리 시점에 따라 다른 sql문이 롤백해야하는데 어떤건 커밋되게되고, 어떤건 아니게됨
			// job_history테이블의 데이터 삭제
			String sql1 = "delete from job_history where employee_id=?";
			PreparedStatement stmt1 = con.prepareStatement(sql1); // sql문 에외 발생 => try~catch
			stmt1.setInt(1, employeeId);
			stmt1.executeUpdate();
			
			// employees 테이블의 데이터 삭제
			String sql2 = "delete from employees where employee_id=? and email=?";
			PreparedStatement stmt2 = con.prepareStatement(sql2); // sql문 에외 발생 => try~catch
			stmt2.setInt(1, employeeId);
			stmt2.setString(2, email);
			int deleteCount = stmt2.executeUpdate(); // executeUpdate()의 결과값을 리턴받아 롤백이 필요한 경우 sql1까지 롤백해주어야한다.
			if(deleteCount == 0) { // 이 부분에서 롤백안하면 sql2는 롤백되어도 sql1의 결과는 메모리에서 롤백되지 않는다.
				// deleteCount == 0이면, delete구문은 정상적으로 시작되었지만, 실제로 삭제되는 경우는 없는 경우다.
				throw new RuntimeException("사원정보가 삭제되지 않았습니다.");
			}
			
			con.commit();
		}catch(Exception e) {
			try { con.rollback(); }catch(Exception e2) {} // 중간에 예외발생하면 롤백 해주어야함
			throw new RuntimeException(e);
		}finally {
			try {con.setAutoCommit(true);}catch(Exception e) {} // 자동 커밋 다시 활성화
			DataSource.closeConnection(con); // 반환 시 사용했던 커넥션을 그대로 반환해주어야 한다.
		}		
	}

	@Override
	public EmpVo selectEmp(int employeeId) {
		Connection con = null;
		try {
			con = DataSource.getConnection();
			String sql = "select employee_id, first_name, last_name, email, phone_number, "
					+ "hire_date, job_id, salary, commission_pct, manager_id, department_id "
					+ "from employees where employee_id=?";
			PreparedStatement stmt = con.prepareStatement(sql); // sql문 에외 발생 => try~catch
			stmt.setInt(1, employeeId);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) { 
				// 조회한 데이터가 있을 경우
				EmpVo emp = new EmpVo();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				return emp;
			} else { 
				// 조회한 데이터가 없는 경우
				throw new RuntimeException("조회한 사원의 정보가 없습니다.");
			}
			// 데이터가 있다면 emp객체를 리턴하거나, 실행타임예외로 메세지를 넘겨줄거임.
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con); // 반환 시 사용했던 커넥션을 그대로 반환해주어야 한다.
		}
//		return null; // 여기에 리턴이 있다면, 정상적인 로직이 아니라면 여기까지 도착할 일이 없기 때문에 오류가 생김 => else에서 throw new 처리를 했기때문
	}

	@Override
	public List<EmpVo> selectAllEmps() {
		Connection con = null;
		List<EmpVo> empList = new ArrayList<>();
		try {
			con = DataSource.getConnection();
			String sql = "select * from employees";
			PreparedStatement stmt = con.prepareStatement(sql); // sql문 에외 발생 => try~catch
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				// rs에서 정보를 조회해서 emp객체에 저장하고, emp객체를 empList에 add()해야 함
				EmpVo emp = new EmpVo();
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				
				empList.add(emp);
			}
		}catch(Exception e) {
			throw new RuntimeException(e);
		}finally {
			DataSource.closeConnection(con); // 반환 시 사용했던 커넥션을 그대로 반환해주어야 한다.
		}
		return empList;
	}
	
}
