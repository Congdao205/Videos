package Dao;

import java.util.List;

import entity.Favorite;
import entity.User;
import entity.Video;

public interface FavoriteDao {
	public void Create(Favorite favorite);

	public Favorite Create1(User user, Video video);

	public void Update(Favorite favorite);

	public void Delete(long id);

	public Favorite FindId(long id);

	public List<Favorite> FindAll();

	public List<Favorite> findIdUser(String idUser);

	public Favorite findIdUserVideo(String idUser, String idVideo);

	boolean likeUnlike(String user, String idvd);

	public List<Object[]> getVideoFavoritesInfo();

}
