import java.sql.Connection;
import java.sql.DriverManager;

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
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {con.close();}catch(Exception e) {}
		}
		
		// Connection 생성 ----------------------------------------------
		
	}
}
