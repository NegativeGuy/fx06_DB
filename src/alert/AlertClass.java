package alert;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertClass {
	
	public static void alert(String msg) { // LoginServiceImpl클래스로부터 mgs를 받음
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(msg);
		alert.show();
	}
}
