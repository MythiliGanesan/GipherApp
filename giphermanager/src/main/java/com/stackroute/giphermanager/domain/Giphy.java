package com.stackroute.giphermanager.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "giphy")
public class Giphy {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name = "gif_id")
	private String gifId;

	@Column(name = "gif_image_path")
	private String url;

	@Column(name = "gif_title")
	private String title;
	
	@Column(name = "gif_rating")
	private String rating;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "poster_path")
	private String posterPath;
	
	
	public String getPosterPath() {
		return posterPath;
	}

	public void setPosterPath(String posterPath) {
		this.posterPath = posterPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGifId() {
		return gifId;
	}

	public void setGifId(String gifId) {
		this.gifId = gifId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Giphy() {

	}

	public Giphy(int id, String gifId, String url, String title, String rating, String userId,String posterPath) {
		super();
		this.id = id;
		this.gifId = gifId;
		this.url = url;
		this.title = title;
		this.rating = rating;
		this.userId = userId;
		this.posterPath = posterPath;
	}

}
