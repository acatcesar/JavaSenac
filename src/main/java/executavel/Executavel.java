package executavel;

import java.sql.SQLException;

import view.Login;

public class Executavel {

	public static void main(String[] args) throws InterruptedException, SQLException {
		Login menuLogin = new Login();
		menuLogin.apresentarMenuLogin();
		
	}

}
