package dev.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserVideo {
	
	@Id
	@Column(name = "NO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@Column(name = "USER_ID")
	private long userId;
	
	@Column(name = "VIDEO_ID")
	private long videoId;

	public UserVideo() {
		super();
	}

	public UserVideo(Long no, long userId, long videoId) {
		super();
		this.no = no;
		this.userId = userId;
		this.videoId = videoId;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getVideoId() {
		return videoId;
	}

	public void setVideoId(long videoId) {
		this.videoId = videoId;
	}

	@Override
	public String toString() {
		return "UserVideo [no=" + no + ", userId=" + userId + ", videoId=" + videoId + "]";
	}

	
}
