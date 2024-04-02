import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CallableExample {
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
		
		// Connection 생성 ----------------------------------------------
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "hr";
		String password = "hr";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println(con);
			
//			CallableStatement ---------------------------------------------
			String sql = "{call GETSALARY(?, ?)}"; // (입력 파라미터, 출력 파라미터)
			CallableStatement stmt = con.prepareCall(sql);
			stmt.setInt(1, 103); // sql문자열 변수에서 첫번째 ?에 매핑될 값
			stmt.registerOutParameter(2, java.sql.Types.DOUBLE); // sql문자열 변수에서 두번째 ?에 매핑될 값
			stmt.execute(); // stmt가 sql을 이미 인자로 받아서, execute()에 인자값이 비어있어도 됨.
			System.out.println(stmt.getDouble(2)); // 출력 파라미터가 두번째 인자이기 때문에 2를 인자값으로 넣어준다.
			
//			CallableStatement ---------------------------------------------
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {con.close();}catch(Exception e) {}
		}
		// Connection 생성 ----------------------------------------------
	}
}
