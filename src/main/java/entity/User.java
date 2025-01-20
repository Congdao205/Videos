package entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

/**
 * The persistent class for the Users database table.
 *
 */
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "User.findlikedhref", procedureName = "sp_selectUserlikedVideoByVideoHerf", resultClasses = {
				User.class }, parameters = @StoredProcedureParameter(name = "videohref", type = String.class)) })
@Entity
@Table(name = "Users")
@NamedQuery(name = "User.findAll", query = "SELECT o FROM User o")
@NamedQuery(name = "User.findNamePass", query = "SELECT o FROM User o where o.id = :id and o.password = :password")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "Admin")
	private boolean admin;

	@Column(name = "Email")
	private String email;

	@Column(name = "Fullname")
	private String fullname;

	@Column(name = "Password")
	private String password;

	// bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "user")
	private List<Favorite> favorites;

	// bi-directional many-to-one association to Share
	@OneToMany(mappedBy = "user")
	private List<Share> shares;

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setUser(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setUser(null);

		return favorite;
	}

	public List<Share> getShares() {
		return this.shares;
	}

	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	public Share addShare(Share share) {
		getShares().add(share);
		share.setUser(this);

		return share;
	}

	public Share removeShare(Share share) {
		getShares().remove(share);
		share.setUser(null);

		return share;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", admin=" + admin + ", email=" + email + ", fullname=" + fullname + ", password="
				+ password + ", favorites=" + favorites + ", shares=" + shares + "]";
	}

}