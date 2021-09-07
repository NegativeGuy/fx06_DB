package Auser;

import alert.AlertClass;
import fx06_DB.MemberDTO;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class LoginServiceImpl implements LoginService{
	LoginDB db;
	
	public LoginServiceImpl() {	// 생성자 생성
		db = new LoginDB();
	}
	@Override
	public void loginChk(Parent root) { // Controller로 부터 root를 받음
		
		TextField id = (TextField)root.lookup("#fxId"); //root를 통해 Id창의 입력값을 id변수에 대입
		TextField pwd = (TextField)root.lookup("#fxPwd"); //root를 통해 Pwd창의 입력값을 id변수에 대입
		
		MemberDTO dto = db.loginChk(id.getText()); 
		// 입력한 아이디, 비번과 비교하는 LoginDB클래스의 loginChk매서드 호출. (이곳의 loginChk매서드 아님 주의)
		// 호출 하면서 Id창의 입력값이 대입된 id변수를 통해 입력값을 해당 매서드로 보냄후 연산결과를 dto 변수에 대입
		
		
		String msg = null;
		if(dto == null) { //LoginDB클래스의 loginChk매서드 결과에 따라 null인지 아닌지 결정됨
			msg = "해당 아이디는 존재하지 않습니다";
			
		}else {
			if(dto.getPwd().equals(pwd.getText())) { // 아이디가 존재하므로 입력된 비밀번호를 비교
				// dto.getPwd()	 : DB에 기록되어 있는 비밀번호
				// pwd.getText() : 비밀번호 입력창에 써져있는 비밀번호 
				msg = "인증 성공 되었습니다";
			}else {
				msg = "비밀번호가 틀렸습니다";
			}
		}
		AlertClass.alert(msg); 
		// 위의 연산결과에 따라 msg의 메시지가 달라지고
		// 그 메시지를 AlertClass 클래스의 alert 매서드로 보냄 
	}

}
