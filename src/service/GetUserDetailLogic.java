package service;

import dao.UserDAO;
import model.User;

public class GetUserDetailLogic {
	public User execute(int userId) {
		UserDAO dao = new UserDAO();
		return dao.getDetail(userId);
	}
}
