package dev.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Video {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "UPLOADER")
	private String uploader;

	@Column(name = "READCNT")
	private int readcnt;

	@Column(name = "LIKE_IT")
	private int like;

	@Column(name = "HATE_IT")
	private int hate;
	
	@Column(name = "DESCRIPTION")
	private String description;

	public Video() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Video(long id, String title, String uploader, int readcnt, int like, int hate, String description) {
		super();
		this.id = id;
		this.title = title;
		this.uploader = uploader;
		this.readcnt = readcnt;
		this.like = like;
		this.hate = hate;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", uploader=" + uploader + ", readcnt=" + readcnt + ", like="
				+ like + ", hate=" + hate + ", description=" + description + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public int getHate() {
		return hate;
	}

	public void setHate(int hate) {
		this.hate = hate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
