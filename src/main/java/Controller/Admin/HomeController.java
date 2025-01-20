package Controller.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FavoriteDaoImpl;
import Dao.ShareDaoImpl;
import Dao.UserDaoImpl;
import Dao.VideoDaoImpl;
import constant.SessionAttr;
import entity.Share;
import entity.User;
import entity.Video;

/**
 * Servlet implementation class HomeController
 */
@WebServlet(urlPatterns = { "/admin", "/admin/user", "/admin/video", "/favorites" }, name = "HomeControllerAdmin")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static UserDaoImpl usdao = new UserDaoImpl();
	private static VideoDaoImpl vddao = new VideoDaoImpl();
	private static FavoriteDaoImpl fddao = new FavoriteDaoImpl();
	private static ShareDaoImpl sdao = new ShareDaoImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null && currentUser.getAdmin() == Boolean.TRUE) {
			String path = request.getServletPath();
			switch (path) {
			case "/admin":
				doGetAdmin(session, request, response);
				break;
			case "/favorites":
				doGetFavorites(request, response);
				break;
			}
		} else {
			response.sendRedirect("index");
		}

	}

	private void doGetAdmin(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> user = usdao.FindAll();
		List<Video> video = vddao.FindAll();
		List<Share> share = sdao.findVideoDetails();
		List<Object[]> favorite = fddao.getVideoFavoritesInfo();
		request.setAttribute("favorite", favorite);
		request.setAttribute("share", share);
		request.setAttribute("user", user);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/Views/Admin/Admin.jsp").forward(request, response);
	}

	private void doGetFavorites(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		String action = request.getParameter("action");
		if ("user".equals(type)) {
			handleUserActions(request, response, action);
		} else if ("video".equals(type)) {
			handleVideoActions(request, response, action);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action type");
		}
	}

	private void handleVideoActions(HttpServletRequest request, HttpServletResponse response, String action)
			throws IOException {
		String videoId = request.getParameter("id");
		String title = request.getParameter("title");
		String poster = request.getParameter("poster");
		int views = Integer.parseInt(request.getParameter("views"));
		String description = request.getParameter("description");
		boolean active = request.getParameter("active") != null;

		switch (action) {
		case "create":
			Video video = new Video();
			video.setId(videoId);
			video.setTitle(title);
			video.setViews(views);
			video.setPoster(poster);
			video.setDescription(description);
			video.setActive(active);
			vddao.Create(video);
			break;
		case "update":
			Video videoupdate = new Video();
			videoupdate.setId(videoId);
			videoupdate.setTitle(title);
			videoupdate.setViews(views);
			videoupdate.setDescription(description);
			videoupdate.setActive(active);
			vddao.Update(videoupdate);
			break;
		case "edit":
			Video videoedit = vddao.FindId(videoId);
			request.setAttribute("editUser", videoedit);
			break;
		case "delete":
			vddao.Delete(videoId);
			break;
		default:
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin");
	}

	private void handleUserActions(HttpServletRequest request, HttpServletResponse response, String action)
			throws IOException {
		String userId = request.getParameter("id");
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");

		switch (action) {
		case "update":
			User user = new User();
			user.setId(userId);
			user.setPassword(password);
			user.setEmail(email);
			user.setFullname(fullname);
			user.setAdmin(false);
			usdao.Update(user);
			break;
		case "delete":
			usdao.Delete(userId);
			break;
		case "edit":
			User useredit = usdao.FindId(userId);
			request.setAttribute("editUser", useredit);
			break;
		default:
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
			return;
		}
		response.sendRedirect(request.getContextPath() + "/admin");
	}

}
