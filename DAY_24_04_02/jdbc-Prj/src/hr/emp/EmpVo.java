package hr.emp;

import java.sql.Date;

import lombok.*;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class EmpVo { // value object
	// DB column name : employee_id -> Java variable name : employeeId
	// DB column name : first_name -> Java variable name : firstName
	// DB column type : number(4) -> Java variable type : int
	// DB column type : varchar2(20) -> Java variable type : String
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate; // DB에서 가져오는 날짜 data는 라이브러리를 util이 아닌 sql의 date 라이브러리 사용, util의 date로 할 경우 가능하나 코드 몇 줄 추가해야함
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
}
