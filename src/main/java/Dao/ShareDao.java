package Dao;

import java.util.List;

import entity.Share;

public interface ShareDao {
	public void Create(Share share);

	public void Update(Share share);

	public void Delete(long id);

	public Share FindId(long id);

	public List<Share> FindAll();

	public List<Share> findVideoDetails();
}
