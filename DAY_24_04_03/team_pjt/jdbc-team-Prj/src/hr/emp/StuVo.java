package hr.emp;

import java.sql.Date;

import lombok.*;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class StuVo {
	private int stuId;
	private String stuName;
	private String stuClass;
	private int stuGroup;
	private String phoneNumber;
}
