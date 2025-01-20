package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import Utils.JpaUtils;
import entity.User;

public class UserDaoImpl implements UserDao {

	@Override
	public void Create(User user) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.persist(user);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();

		} finally {
			em.close();
		}
	}

	@Override
	public void Update(User user) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.merge(user);
			tran.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();

		} finally {
			em.close();
		}
	}

	@Override
	public boolean Delete(String id) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			User user = em.find(User.class, id);
			if (user == null) {
				return false; // Người dùng không tồn tại
			}
			tran.begin();
			em.remove(user);
			tran.commit();
			return true; // Thành công
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			return false; // Thất bại
		} finally {
			em.close();
		}

	}

	@Override
	public User FindId(String id) {
		try {
			EntityManager em = JpaUtils.EntityManager();
			String jpql = "Select u from User u where u.id=:id";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<User> FindAll() {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public List<User> page(int PageNumber, int PageSize) {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		query.setFirstResult((PageNumber - 1) * PageSize);
		query.setMaxResults(PageSize);
		return query.getResultList();
	}

	@Override
	public User Login(String id, String password) {
		EntityManager em = JpaUtils.EntityManager();
		TypedQuery<User> query = em.createNamedQuery("User.findNamePass", User.class);
		query.setParameter("id", id);
		query.setParameter("password", password);
		List<User> users = query.getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return null;
	}

	public static void main(String[] args) {
		UserDaoImpl dao = new UserDaoImpl();
//		User share = dao.Login("US01", "12345");
//		System.out.println(share);
		List<User> share = dao.findLikedUsersByVideoHref("kPuHaXqh8FA");
		share.forEach(System.out::println);
	}

	@Override
	public List<User> findLikedUsersByVideoHref(String videoHref) {
		EntityManager em = JpaUtils.EntityManager();
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("User.findlikedhref");
		query.setParameter("videohref", videoHref);
		return query.getResultList();
	}

	@Override
	public boolean Createboolen(User user) {
		EntityManager em = JpaUtils.EntityManager();
		EntityTransaction tran = em.getTransaction();
		try {
			tran.begin();
			em.persist(user);
			tran.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			tran.rollback();
			return false;
		} finally {
			em.close();
		}
	}
}
