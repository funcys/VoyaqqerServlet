package service;

import dao.UserDAO;
import model.User;

public class RegistUserLogic {
	public void execute(User registUser) {
		UserDAO dao = new UserDAO();
		dao.create(registUser);
	}

}
