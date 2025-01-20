package Dao;

import java.util.List;

import entity.Video;

public interface VideoDao {
	public void Create(Video video);

	public void Update(Video video);

	public void Delete(String id);

	public Video FindId(String id);

	public List<Video> FindAll();

	public List<Video> page(int PageNumber, int PageSize);

	Video FindPoster(String id);

}
