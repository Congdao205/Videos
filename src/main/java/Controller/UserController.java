package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.EmailDaoImpl;
import Dao.UserDaoImpl;
import constant.SessionAttr;
import entity.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = { "/login", "/logout", "/register" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	private UserDaoImpl dao = new UserDaoImpl();
	private EmailDaoImpl emaildao = new EmailDaoImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		switch (path) {
		case "/login":
			doGetLogin(request, response);
			break;
		case "/register":
			doGetRegister(request, response);
			break;
		case "/logout":
			doGetLogout(session, request, response);
			break;

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		switch (path) {
		case "/login":
			doPostLogin(session, request, response);
			break;
		case "/register":
			doPostRegister(session, request, response);
			break;
		case "/logout":
			doPostRegister(session, request, response);
			break;
		}
	}

	private void doGetLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Views/User/Login.jsp").forward(request, response);
	}

	private void doGetRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/Views/User/Register.jsp").forward(request, response);
	}

	private void doGetLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		response.sendRedirect("index");
	}

	private void doPostLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Account = request.getParameter("id");
		String Password = request.getParameter("password");
		String message = "";
		User user = dao.FindId(Account);

		if (user == null) {
			message = "Sai Account";
			request.setAttribute("message", message);
//			response.sendRedirect("login");
			request.getRequestDispatcher("/Views/User/Login.jsp").forward(request, response);
		} else if (!user.getPassword().equals(Password)) {
			message = "Sai password";
			request.setAttribute("message", message);
//			response.sendRedirect("login");
			request.getRequestDispatcher("/Views/User/Login.jsp").forward(request, response);
		} else {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			if (user.getAdmin() == Boolean.TRUE) {
				response.sendRedirect("admin");
			} else {
				response.sendRedirect("index");
			}
		}
	}

	private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Account = request.getParameter("id");
		String Email = request.getParameter("email");
		String Password = request.getParameter("password");
		String Fullname = request.getParameter("name");

		User user = new User();
		user.setId(Account); // Gán ID
		user.setEmail(Email); // Gán Email
		user.setPassword(Password); // Gán Password
		user.setFullname(Fullname);
		user.setAdmin(false);

		boolean created = dao.Createboolen(user);
		if (created) {
			String host = "smtp.gmail.com";
			String port = "587";
			String userMail = "congdhps40841@gmail.com";
			String passMail = "hkgr vkzd gnmi szyg";

			emaildao.sendMail(host, port, userMail, passMail, user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			response.sendRedirect("index");
		} else {
			response.sendRedirect("register");
		}
	}

}
