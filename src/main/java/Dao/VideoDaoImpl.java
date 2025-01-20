package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Utils.JpaUtils;
import entity.Video;

public class VideoDaoImpl implements VideoDao {

	@Override
	public void Create(Video video) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.persist(video);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void Update(Video video) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.merge(video);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void Delete(String id) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			Video video = em.find(Video.class, id);
			tran.begin();
			em.remove(video);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public Video FindId(String id) {
		try {
			EntityManager em = JpaUtils.EntityManager();
			String jpql = "Select v from Video v where v.id=:id";
			TypedQuery<Video> query = em.createQuery(jpql, Video.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Video FindPoster(String id) {
		EntityManager em = JpaUtils.EntityManager();
		String jpql = "Select v from Video v where v.poster =:poster";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setParameter("poster", id);
		return query.getSingleResult();
	}

	@Override
	public List<Video> FindAll() {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public List<Video> page(int PageNumber, int PageSize) {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<Video> query = em.createNamedQuery("Video.findAll", Video.class);
		query.setFirstResult((PageNumber - 1) * PageSize);
		query.setMaxResults(PageSize);
		return query.getResultList();
	}

	public static void main(String[] args) {
		VideoDaoImpl dao = new VideoDaoImpl();
		Video share = dao.FindPoster("kPuHaXqh8FA");
		System.out.println(share);
//		List<Video> share = dao.FindAll();
//		share.forEach(System.out::println);
	}

}
