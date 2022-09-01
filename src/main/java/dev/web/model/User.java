package dev.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USERID")
	private String userId;

	@Column(name = "PASSWD")
	private String passwd;

	@Column(name = "NAME")
	private String userName;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "AGE")
	private int age;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(long id, String userId, String passwd, String userName, String gender, int age) {
		super();
		this.id = id;
		this.userId = userId;
		this.passwd = passwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", passwd=" + passwd + ", userName=" + userName + ", gender="
				+ gender + ", age=" + age + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
