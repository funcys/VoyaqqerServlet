package service;

import dao.UserDAO;
import model.User;

public class UpdateUserLogic {
	public void execute(User updateUser) {
		UserDAO dao = new UserDAO();
		dao.update(updateUser);
	}


}
