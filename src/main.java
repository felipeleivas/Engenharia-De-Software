import database.DataBase;
import managment.Controller;

public class main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
	
		DataBase dataBase = new DataBase();
		Controller controller = new Controller(dataBase);
	}

}
