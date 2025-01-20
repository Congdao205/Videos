package Dao;

import entity.User;

public interface EmailDao {
	void sendMail(String host, String port, String user, String pass, User recipient, String type);
}
