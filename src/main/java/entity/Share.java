package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the Share database table.
 *
 */
@Entity
@NamedQuery(name = "Share.findAll", query = "SELECT s FROM Share s")
public class Share implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private long id;

	@Column(name = "Emails")
	private String emails;

	@Column(name = "ShareDate")
	private Date shareDate;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UserId")
	private User user;

	// bi-directional many-to-one association to Video
	@ManyToOne
	@JoinColumn(name = "VideoId")
	private Video video;

	public Share() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmails() {
		return this.emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return this.video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

//	public String getShareDate() {
//		return shareDate;
//	}
//
//	public void setShareDate(String shareDate) {
//		this.shareDate = shareDate;
//	}

	@Override
	public String toString() {
		return "Share [id=" + id + ", emails=" + emails + ", shareDate=" + shareDate + "]";
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

}