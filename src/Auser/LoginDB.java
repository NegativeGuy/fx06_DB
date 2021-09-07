package Auser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.DBClass;
import fx06_DB.MemberDTO;

public class LoginDB {
	
	public MemberDTO loginChk(String inputId) { // LoginServiceImpl로 부터 id값을 받음
		String sql = "select * from fx_member where id= ?";
		MemberDTO dto = null; //아래 if 연산 결과에 따라 값이 null이거나 아니거나로 결정됨
		
		try {
			PreparedStatement ps = DBClass.conn.prepareStatement(sql);
			ps.setString(1, inputId); // 전해받은 id값을 sql 명령어 중 '?'자리에 대입
			
			//ResultSet은 배열과 비슷한 방식으로 가져옴
			//ResultSet은 select 문에서는 무조건 사용해야함
			ResultSet rs = ps.executeQuery(); // sql명령어로 실행한 결과를 rs에 대입
			
			if(rs.next()) { // 입력한 아이디가 DB에 있다면 (id입력값이 DB에 있다면)
				dto = new MemberDTO();	// dto가 객체값을 가지게 됨 (초기값null에서 해당 객체값으로 바뀜)
				dto.setId(rs.getString("id"));	// 해당 객체값을 가졌기 때문에 그걸 통해 DB의 id값을 받아옴
				dto.setPwd(rs.getString("pwd")); // 마찬가지로 pwd 값을 받아옴
				dto.setName(rs.getString("name")); // name값을 받아옴
			}	// 입력한 아이디가 DB에 없다면 dto는 객체화가 안되고 위에 null값만 가지고 return됨
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
