import java.sql.DriverManager;

public class JdbcExample {
//	4.
	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 클래스가 로드되었습니다.");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) throws Exception {
		System.out.println("데이터베이스 연결을 테스트합니다.");
		
//		1.
//		Class.forName("oracle.jdbc.OracleDriver");
//		2.
//		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
//		3.
//		new oracle.jdbc.driver.OracleDriver();
//		System.out.println("드라이버 클래스가 로드되었습니다.");
		
	}
}
