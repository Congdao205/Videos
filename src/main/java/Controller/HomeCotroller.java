package Controller;

import java.io.IOException;
import java.util.ArrayList;
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
import entity.Favorite;
import entity.User;
import entity.Video;

/**
 * Servlet implementation class HomeCotroller
 */
@WebServlet({ "/index", "/favorite", "/history" })
public class HomeCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeCotroller() {
		super();
		// TODO Auto-generated constructor stub
	}

	private VideoDaoImpl vddao = new VideoDaoImpl();
	private FavoriteDaoImpl fdao = new FavoriteDaoImpl();
	private static final int max_page = 6;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = request.getServletPath();
		switch (path) {
		case "/index":
			doGetIndex(request, response);
			break;
		case "/favorite":
			doGetfavorite(session, request, response);
			break;
		}

	}

	private void doGetIndex(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Video> countvideo = vddao.FindAll();
		int maxpage = (int) Math.ceil(countvideo.size() / (double) max_page);
		request.setAttribute("maxpage", maxpage);
		String pagenumber = request.getParameter("page");
		List<Video> videos;
		if (pagenumber == null || Integer.valueOf(pagenumber) > maxpage) {
			videos = vddao.page(1, max_page);
			request.setAttribute("currentpage", 1);
		} else {
			videos = vddao.page(Integer.valueOf(pagenumber), max_page);
			request.setAttribute("currentpage", Integer.valueOf(pagenumber));
		}

		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/Views/User/Index.jsp").forward(request, response);
	}

	private void doGetfavorite(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<Favorite> favorites = fdao.findIdUser(user.getId());
		List<Video> videos = new ArrayList<>();
		for (Favorite item : favorites) {
			if (item.getLikeDate() != null) {
				videos.add(item.getVideo());
			}
		}

		request.setAttribute("favoritevideos", videos);
		request.getRequestDispatcher("/Views/User/Favorite.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
