import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcExample {
//	4. jdbc 드라이버 로드
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 클래스가 로드되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		System.out.println("데이터베이스 연결을 테스트합니다.");
		
//		1. jdbc 드라이버 로드
//		Class.forName("oracle.jdbc.OracleDriver");
//		2. jdbc 드라이버 로드
//		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//		3. jdbc 드라이버 로드
//		new oracle.jdbc.driver.OracleDriver();
//		System.out.println("드라이버 클래스가 로드되었습니다.");
		
		// Connection 생성 ----------------------------------------------
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "hr";
		String password = "hr";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println(con);
			
//			Statement 생성 ----------------------------------------------
			Statement stmt = con.createStatement();
//			int deptno = 60;
			String deptno = "\'\' or \' \'=\' \'"; // department_id='' or ' '=' ' => null or true(=> 공백 == 공백)
//			String sql = "select employee_id, first_name, salary from employees";
			String sql = "select first_name, hire_date, salary, department_id "
					+ "from employees where department_id=" + deptno;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);  // sql문 실행
			System.out.println(rs);
			while(rs.next()) {
//				System.out.print(rs.getInt(1)); // 조회한 첫번째 열 순서
//				System.out.print(rs.getInt("employee_id") + "\t"); // 조회한 첫번째 열 별칭
//				System.out.print(rs.getString("first_name") + "\t");
//				System.out.println(rs.getDouble("salary"));
				
				System.out.print(rs.getString("first_name") + "\t");
				System.out.println(rs.getDouble("salary"));
			}
//			Statement 생성 ----------------------------------------------

			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {con.close();}catch(Exception e) {}
		}
		// Connection 생성 ----------------------------------------------
		
	}
}
