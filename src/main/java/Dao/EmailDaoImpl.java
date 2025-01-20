package Dao;

import Utils.EmailUtility;
import entity.User;

public class EmailDaoImpl implements EmailDao {

	private static final String Email_Welcome = "welcome to Online entertaiment";
	private static final String Email_Pass = "Online entertaiment - new password";

	@Override
	public void sendMail(String host, String port, String user, String pass, User recipient, String type) {
		try {
			String content = null;
			String subject = null;
			switch (type) {
			case "welcome":
				subject = Email_Welcome;
				content = "dear" + recipient.getFullname() + ", hope you have a good time!";
				break;
			case "fogot":
				subject = Email_Pass;
				content = "dear" + recipient.getFullname() + ", Your new password here: " + recipient.getPassword();
				break;
			default:
				subject = "Online Entertainment";
				content = "Maybe this email is wrong, don't care about it";
			}
			EmailUtility.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Lỗi khi gửi email: " + e.getMessage());
		}
	}

}
