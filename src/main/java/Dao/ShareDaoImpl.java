package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Utils.JpaUtils;
import entity.Share;

public class ShareDaoImpl implements ShareDao {

	@Override
	public void Create(Share share) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.persist(share);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public void Update(Share share) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.merge(share);
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
			Share share = em.find(Share.class, id);
			tran.begin();
			em.remove(share);
			tran.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
		} finally {
			em.close();
		}

	}

	@Override
	public Share FindId(long id) {
		EntityManager em = JpaUtils.EntityManager();
		String jpql = "Select s from Share s where s.id =:id";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		query.setParameter("id", id);
		return query.getSingleResult();

	}

	@Override
	public List<Share> FindAll() {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<Share> query = em.createNamedQuery("Share.findAll", Share.class);
		return query.getResultList();
	}

	public static void main(String[] args) {
		ShareDaoImpl dao = new ShareDaoImpl();
		Share share = dao.FindId(1);
		System.out.println(share);
//		List<Share> share = dao.FindAll();
//		share.forEach(System.out::println);
	}

	@Override
	public List<Share> findVideoDetails() {
		EntityManager em = JpaUtils.EntityManager();
		String jpql = "SELECT s FROM Share s JOIN FETCH s.user u JOIN FETCH s.video v WHERE s.shareDate IS NOT NULL";
		TypedQuery<Share> query = em.createQuery(jpql, Share.class);
		return query.getResultList();
	}
}
