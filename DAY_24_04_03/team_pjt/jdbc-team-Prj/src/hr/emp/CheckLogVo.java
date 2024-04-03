package hr.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class CheckLogVo {
	private int stuId;
	private String stuName;
	private String stuClass;
	private Date enterDate;
	private Date exitDate;
}
