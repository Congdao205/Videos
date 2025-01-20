package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.FavoriteDaoImpl;
import Dao.VideoDaoImpl;
import constant.SessionAttr;
import entity.User;
import entity.Video;

/**
 * Servlet implementation class VideoController
 */
@WebServlet({ "/video", "/like" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VideoController() {
		super();
		// TODO Auto-generated constructor stub
	}

	VideoDaoImpl dao = new VideoDaoImpl();
	FavoriteDaoImpl fdao = new FavoriteDaoImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String actionparam = request.getParameter("action");
		String href = request.getParameter("id");
		HttpSession session = request.getSession();
		switch (actionparam) {
		case "video":
			doGetDetail(session, href, request, response);
			break;
		case "like":
			doGetLike(session, href, request, response);
			break;
		}
	}

	private void doGetDetail(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Video video = dao.FindPoster(href);
		List<Video> videos = dao.FindAll();
		request.setAttribute("video", video);
		request.setAttribute("listvideo", videos);
		request.getRequestDispatcher("/Views/User/Detail.jsp").forward(request, response);
	}

	private void doGetLike(HttpSession session, String href, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Video video = dao.FindPoster(href);
		if (video == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Video không tồn tại");
			return;
		}
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser == null) {
			response.sendRedirect("login");
			return;
		}
		String idUser = currentUser.getId();
		String idvideo = video.getId();
		boolean result = fdao.likeUnlike(idUser, idvideo);
		if (result) {
			response.sendRedirect(request.getHeader("referer"));

		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi cập nhật trạng thái thích");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
