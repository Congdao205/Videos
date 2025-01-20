package Dao;

import java.util.List;

import entity.User;

public interface UserDao {
	void Create(User user);

	boolean Createboolen(User user);

	void Update(User user);

	boolean Delete(String id);

	User FindId(String id);

	User Login(String id, String password);

	List<User> FindAll();

	List<User> page(int PageNumber, int PageSize);

	List<User> findLikedUsersByVideoHref(String videoHref);

}
