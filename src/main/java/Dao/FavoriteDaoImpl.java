package Dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Utils.JpaUtils;
import entity.Favorite;
import entity.User;
import entity.Video;

public class FavoriteDaoImpl implements FavoriteDao {
	private VideoDaoImpl vddao = new VideoDaoImpl();
	private UserDaoImpl usdao = new UserDaoImpl();

	@Override
	public void Create(Favorite favorite) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {

			tran.begin();
			em.persist(favorite);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void Update(Favorite favorite) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.merge(favorite);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void Delete(long id) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			Favorite favorite = em.find(Favorite.class, id);
			tran.begin();
			em.remove(favorite);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public Favorite FindId(long id) {
		EntityManager em = JpaUtils.EntityManager();
		String jpql = "Select f from Favorite f where f.id=:id";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("id", id);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Favorite> FindAll() {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<Favorite> query = em.createNamedQuery("Favorite.findAll", Favorite.class);
		return query.getResultList();
	}

	public static void main(String[] args) {

		FavoriteDaoImpl dao = new FavoriteDaoImpl();
//		boolean share = dao.likeUnlike("US01", "VD03");
//		System.out.println(share);

//		List<Favorite> share = dao.findIdUser("US01");
//		share.forEach(System.out::println);

//		List<Object[]> result = dao.getVideoFavoritesInfo();
//		for (Object[] row : result) {
//			String videoTitle = (String) row[0]; // videoTitle
//			Long favoriteCount = (Long) row[1]; // favoriteCount
//			java.util.Date latestDate = (java.util.Date) row[2]; // latestDate
//			java.util.Date oldestDate = (java.util.Date) row[3]; // oldestDate
//
//			// In ra thông tin
//			System.out.println("Video Title: " + videoTitle);
//			System.out.println("Favorite Count: " + favoriteCount);
//			System.out.println("Latest Date: " + latestDate);
//			System.out.println("Oldest Date: " + oldestDate);
//		}
	}

	@Override
	public List<Favorite> findIdUser(String idUser) {
		EntityManager em = JpaUtils.EntityManager();
		String jpql = "Select f from Favorite f where f.user.id =: id and f.video.active=1";
		TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
		query.setParameter("id", idUser);
		return query.getResultList();
	}

	@Override
	public Favorite findIdUserVideo(String idUser, String idVideo) {
		EntityManager em = JpaUtils.EntityManager();
		try {
			String jpql = "Select f from Favorite f where f.user.id =: idUS and f.video.id=: idVD and f.video.active=1";
			TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
			query.setParameter("idUS", idUser);
			query.setParameter("idVD", idVideo);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public boolean likeUnlike(String iduser, String idvd) {
		User user = usdao.FindId(iduser);
		Video video = vddao.FindId(idvd);

		if (user == null || video == null) {
			return false;
		}

		Favorite exitfavorite = findIdUserVideo(user.getId(), video.getId());

		if (exitfavorite == null) {
			exitfavorite = new Favorite();
			exitfavorite.setUser(user);
			exitfavorite.setVideo(video);
			exitfavorite.setLikeDate(new Date(System.currentTimeMillis()));
			Create(exitfavorite);
		} else {
			if (exitfavorite.getLikeDate() == null) {
				exitfavorite.setLikeDate(new Date(System.currentTimeMillis()));
			} else {
				exitfavorite.setLikeDate(null);
			}
			Update(exitfavorite);
		}

		return true;
	}

	@Override
	public Favorite Create1(User user, Video video) {
		Favorite favorite = findIdUserVideo(user.getId(), video.getId());
		if (favorite == null) {
			favorite = new Favorite();
			favorite.setUser(user);
			favorite.setVideo(video);
			favorite.setLikeDate(new Date(System.currentTimeMillis()));
			Create(favorite);
			return favorite;
		}
		return null;

	}

	@Override
	public List<Object[]> getVideoFavoritesInfo() {
		EntityManager em = JpaUtils.EntityManager();
		String jpql = "SELECT v.title AS videoTitle, COUNT(f.id) AS favoriteCount, "
				+ "MAX(f.likeDate) AS latestDate, MIN(f.likeDate) AS oldestDate FROM Video v "
				+ "LEFT JOIN v.favorites f GROUP BY v.id, v.title ORDER BY favoriteCount DESC";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		return query.getResultList();
	}

}
