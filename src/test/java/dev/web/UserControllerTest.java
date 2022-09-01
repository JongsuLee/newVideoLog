package dev.web;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import dev.web.model.User;
import dev.web.model.UserVideo;
import dev.web.model.Video;


class UserControllerTest extends HttpServlet {

	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoLog");
	private final EntityManager em = emf.createEntityManager();
	private final EntityTransaction tx = em.getTransaction();
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	/*
	 *  logIn: 로그인시 동작
	 *  
	 *  1. userid, passwd 정보 받기
	 *  2. DB에서 user 정보 조회
	 *  	(만일 정보가 없을 경우, request "error"에 오류 메시지 저장)
	 *  3. session "login"에 해당 유저 정보를 저장
	 *  4. index.html로 이동  
	 *   >> forward: /
	 */
	
	@Test
	public String logIn(@RequestParam("") String userid, @RequestParam("") String passwd, HttpServletRequest request, HttpServletRequest response) {

		try {
			User user = authorize(userid, passwd);
			HttpSession session = request.getSession();
			session.setAttribute("login", user);
		} catch (Exception e) {
			// 아이디 혹은 비밀번호가 맞지 않은 경우 예외발생
			request.setAttribute("error", "아이디와 비밀번호를 다시 확인해주십시오");
		}

		return "forward:/";
	}
	
	public User authorize(String userid, String passwd) {
		String sql = "select u from User u where userid = :userid and passwd = :passwd";
		TypedQuery<User> query = em.createQuery(sql, User.class);
		query.setParameter("userid", userid);
		query.setParameter("passwd", passwd);
		
		return query.getSingleResult(); 
	}
	
	/*
	 *	getProfile: 프로필 페이지로 이동시 동작
	 *
	 * 	1. session에 있는 login 정보를 받기
	 * 	2. listVideo를 통해 UserVideo table에서 해당 유저가 포함된 레코드들을 불러와 list에 저장
	 * 	3. request "videoLog"에 list를 저장
	 * 	 >> profile.jsp를 실행
	 */
	
	@Test
	public String getProfile(HttpServletRequest request, @SessionAttribute("login") User user) {

		List<UserVideo> list = listVideo(user.getId());
		request.setAttribute("videoLog", list);
		
		return "userForm.jsp";
	}
	
	public List<UserVideo> listVideo(long id) {
		TypedQuery<UserVideo> query = em.createQuery("select uv from UserVideo uv where user_id = :id", UserVideo.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	/*
	 *  clickVideo: 비디오 클릭시 동작
	 *  
	 * 	1. session에서 login정보를 받기
	 * 	2. 해당 비디오 정보 받기
	 * 	3. UserVideo table에 각각의 id 담기
	 * 	4. 비디오 조회수 1증가  
	 *  >> forward: /retrieve
	 */
	
	@Test
	public String clickVideo(HttpServletRequest request, HttpServletResponse response, @SessionAttribute("login") User user, @RequestParam("") long videoId) {
		
		if (user != null) {
			Video video = em.find(Video.class, videoId);
			
			tx.begin();
			relateUserVideo(user, video);
			video.setReadcnt(video.getReadcnt() + 1);
			em.persist(video);
			tx.commit();
			video = em.find(Video.class, videoId);
			request.setAttribute("video", video);
		}
		
		return "forward:/retrieve";
	}
	
	private void relateUserVideo(User user, Video video) {
		UserVideo userVideo = new UserVideo();
		userVideo.setUserId(user.getId());
		userVideo.setVideoId(video.getId());
		em.persist(userVideo);
	}

}
