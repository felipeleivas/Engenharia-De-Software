package managment;

import java.sql.SQLException;

import database.DataBase;
import domain.RegistredUser;

public class SessionData {
	private DataBase dataBase;
	private RegistredUser loggedUser;
	
	public SessionData(DataBase database){
		 this.dataBase = database;
		 this.loggedUser = null;
	}
	
	protected boolean login(String username, String password) throws SQLException {
		RegistredUser user = dataBase.readRegistredUser(username);
		if(user == null) {
			return false;
		}else{
			if(user.getPassword().equals(password)) {
				this.logged(user);
				return true;
			}else{
				return false;
			}
		}
	}
	public void logout() {
		loggedUser = null;
	}
	
	public void logged(RegistredUser user) {
		loggedUser = user;
	}
	
	public RegistredUser getLoggedUser() {
		return loggedUser;
	}
}
