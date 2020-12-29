package service;

import java.util.List;

import dao.UserDAO;
import model.User;

public class GetUserListLogic {

		public List<User> execute() {
			UserDAO dao = new UserDAO();
			List<User> userList = dao.findAll();
			return userList;
		}
}
