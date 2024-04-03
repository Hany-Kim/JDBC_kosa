package hr.emp;

import java.sql.Date;

import lombok.*;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class LogVo {
	private int logId;
	private Date enterDate;
	private Date exitDate;
	private int stuId;
}
