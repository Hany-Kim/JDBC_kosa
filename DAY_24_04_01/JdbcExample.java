
public class JdbcExample {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("데이터베이스 연결을 테스트합니다.");
		
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("드라이버 클래스가 로드되었습니다.");
	}
}
