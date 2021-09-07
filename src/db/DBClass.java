package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fx06_DB.MemberDTO;

public class DBClass {
	public static Connection conn;
	
	public DBClass() {
		try { // 자바에서 오라클 연결하기 위해 필수로 있어야하는 코드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 오라클과 연동된 객체를 가져오는 코드
			conn = DriverManager.getConnection
					("jdbc:oracle:thin:@210.221.253.215:1521:xe","java4jo","4444");
//			conn = DriverManager.getConnection
//					("jdbc:oracle:thin:@localhost:1521:xe","dydgns2446","evan9396");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//---------------------
	public int insert(MemberDTO dto) {
		String sql = "insert into fx_member(id,pwd,name) values(?,?,?)";
		int result = 0;
		
		try {
			// 연결된 객체(conn)을 이용해서 쿼리문(명령어) 전송할 수 있는 전송객체 얻어옴
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//쿼리문의 물음표 자리에 값을 채워줌
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPwd());
			ps.setString(3, dto.getName());
			
			// 전송객체를 이용해서 명령어 실행(DB에 명령어 전송)
			result = ps.executeUpdate(); // 결과값 : 성공=1 / 실패=0
			// 보편적으로 select를 제외한 나머지 명령어는 executeUpdate를 사용함
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result; //호출한 곳으로 result값(0 혹은 1)을 retrun보냄
	}
	
	public MemberDTO loginChk(String inputId) {
		String sql = "select * from fx_member where id= ?";
		MemberDTO dto = null;
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, inputId);
			
			//ResultSet은 배열과 비슷한 방식으로 가져옴
			//ResultSet은 select 문에서는 무조건 사용해야함
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) { // 입력한 아이디가 DB에 있다면
				dto = new MemberDTO();	// dto가 객체값을 가지게 됨
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
			}	// // 입력한 아이디가 DB에 없다면 dto는 객체화가 안되고 위에 null값만 가지고 return됨
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
