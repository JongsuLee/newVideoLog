package dev.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import dev.web.model.User;
import dev.web.model.UserVideo;
import dev.web.model.Video;

// USER CONTROLLER
@RestController
public class UserController {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("VideoLog");
	private final EntityManager em = emf.createEntityManager();
	private final EntityTransaction tx = em.getTransaction();
	
	
	@RequestMapping("/member/login")
	public ModelAndView logIn(@RequestParam("userId") String userid, @RequestParam("passwd") String passwd, HttpServletRequest request, HttpServletRequest response) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../main.jsp");
		try {
			User user = authorize(userid, passwd);
			HttpSession session = request.getSession();
			session.setAttribute("login", user);
		} catch (Exception e) {
			// 아이디 혹은 비밀번호가 맞지 않은 경우 예외발생
			request.setAttribute("error", "아이디와 비밀번호를 다시 확인해주십시오");
		}

		return modelAndView;
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
	 * 	3. getVideos를 통해 Video table에서 해당 list가 포함된 video들을 불러와 listVideo에 저장
	 * 	3. request "videoLog"에 listVideo를 저장
	 * 	 >> profile.jsp를 실행
	 */
	@RequestMapping("/profile")
	public ModelAndView getProfile(HttpServletRequest request, @SessionAttribute("login") User user) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("userForm.jsp");
		
		List<UserVideo> list = listVideo(user.getId());
		List<Video> listVideo = getVideos(list);
		request.setAttribute("videoLog", listVideo);
		
		return modelAndView;
	}
	
	public List<UserVideo> listVideo(long id) {
		TypedQuery<UserVideo> query = em.createQuery("select uv from UserVideo uv where user_id = :id", UserVideo.class);
		query.setParameter("id", id);
		
		return query.getResultList();
	}

	public List<Video> getVideos(List<UserVideo> list) {
		
		List<Long> listVideoId = new ArrayList<Long>();
		for (UserVideo uv : list) listVideoId.add(uv.getVideoId());
		
		TypedQuery<Video> query = em.createQuery("select v from Video v where id in :id", Video.class);
		query.setParameter("id", listVideoId);
		
		return query.getResultList();
	}
	
	/*
	 * clickVideo
	 */
	
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
