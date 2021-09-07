package fx06_DB;

import java.net.URL;
import java.util.ResourceBundle;

import Auser.LoginService;
import Auser.LoginServiceImpl;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
	
	Parent root;
	
	public static DBClass db; //공통적으로 DB를 사용해야 하므로 static으로 만들어 줌 (localhost 연동되어있음)
	private db.DBClass comDB; // (공동작업 시 - 공동으로쓰는 ip(DB) 연동되어있음)
	private LoginService ls; // (공동작업 시)
	
	public void setRoot(Parent root) {
		this.root = root;	// main으로부터 root 받음
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //초기화 기능 (객체 생성)
		db = new DBClass(); // 초기화로 db 객체 생성
		comDB = new db.DBClass(); // 초기화로 comDB 객체 생성 (공동작업 시)
		ls = new LoginServiceImpl(); // 초기화로 ls 객체 생성 (공동작업 시)
	}
	
	// 회원가입 버튼 누룰 경우 실행(호출됨)
	public void membership() {
		// 입력창에 있는 내용들(id,name,pwd)을 변수에 대입
		TextField id = (TextField)root.lookup("#memberId");
		TextField name = (TextField)root.lookup("#memberName");
		TextField pwd = (TextField)root.lookup("#memberPwd");
		
		// MemberDTO자료형으로 객체 생성
		MemberDTO dto = new MemberDTO();
		dto.setId(id.getText());	// 입력창에 쓴 id를 setter로 보냄
		dto.setPwd(pwd.getText());	// 입력창에 쓴 pwd를 setter로 보냄
		dto.setName(name.getText());	// 입력창에 쓴 name를 setter로 보냄
		
		//int result = db.insert(dto);	// 삽입기능을 하는 매서드 insert로 dto를 보내고 결과를 받음
		int result = comDB.insert(dto);	// 공동 DB에 추가하려면 이거 활성화. (공동작업 시)
		// result는 결과값을 return 받아 0 또는 1의 값을 갖게됨
		
		if(result == 1) {	// 공동작업 시에는 Alert도 AlertClass로 따로 만들어 줬음
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("회원가입에 성공하셨습니다");
			alert.show();
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("동일한 아이디가 존재합니다");
			alert.show();
		}
	}
	
	// 로그인 버튼 누르면 실행(호출됨)
	public void login() {
		ls.loginChk(root); //LoginServiceImpl 클래스의 해당 매서드에 root를 전달하고 실행
		
		/* 다른사람이 login() 매서드를 만든다는 가정하에 주석처리 한 것
		TextField id = (TextField)root.lookup("#fxId");
		TextField pwd = (TextField)root.lookup("#fxPwd");
		
		MemberDTO dto = db.loginChk(id.getText()); // 입력한 아이디, 비번과 비교하는 매서드 호출
		
		if(dto == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("존재하지 않는 아이디 입니다");
			alert.show();
		}else {
			if(dto.getPwd().equals(pwd.getText())) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("인증 성공!!");
				alert.show();
			}else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("비밀번호 틀림!!");
				alert.show();
			}
		}
		*/
	}
	
}
