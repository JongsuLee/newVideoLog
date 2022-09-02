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
	public ModelAndView logIn(@RequestParam("userId") String userid, @RequestParam("passwd") String passwd,
			HttpServletRequest request, HttpServletRequest response) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("../main.jsp");
		try {
			User user = authorize(userid, passwd);
			HttpSession session = request.getSession();
			session.setAttribute("login", user);
		} catch (Exception e) {
			request.setAttribute("error", "�븘�씠�뵒�� 鍮꾨�踰덊샇瑜� �떎�떆 �솗�씤�빐二쇱떗�떆�삤");
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
		TypedQuery<UserVideo> query = em.createQuery("select uv from UserVideo uv where user_id = :id",
				UserVideo.class);
		query.setParameter("id", id);

		return query.getResultList();
	}

	public List<Video> getVideos(List<UserVideo> list) {

		List<Long> listVideoId = new ArrayList<Long>();
		for (UserVideo uv : list)
			listVideoId.add(uv.getVideoId());

		TypedQuery<Video> query = em.createQuery("select v from Video v where id in :id", Video.class);
		query.setParameter("id", listVideoId);

		return query.getResultList();
	}

	/*
	 * clickVideo: 비디오 클릭시 동작
	 * 
	 * 	1. session에서 login정보를 불러옴
	 * 	2. 해당 비디오의 id값을 videoId로 저장
	 *  3. videoId로 DB에서 video를 불러옴
	 *  4. relateVideo에 User와 Video를 넘겨 UserVideo table에 저장
	 *  5. video의 readCnt를 1씩 증가
	 *    >> forward:/retrieve
	 */
	@RequestMapping("/videoRetrieve")
	public ModelAndView clickVideo(HttpServletRequest request, HttpServletResponse response,
							 @SessionAttribute("login") User user, @RequestParam("") long videoId) {
		
		ModelAndView modelAndView = new ModelAndView();
		if (user != null) {
			Video video = em.find(Video.class, videoId);

			tx.begin();
			relateUserVideo(user, video);
			increaseReadCnt(video);
			tx.commit();
			video = em.find(Video.class, videoId);
			request.setAttribute("video", video);
			modelAndView.setViewName("videoRetrieve.jsp");
		} else {
			modelAndView.setViewName("videoRetrieve.jsp");
			request.setAttribute("error", "로그인 정보가 필요합니다");
		}
		return modelAndView;
	}

	private void relateUserVideo(User user, Video video) {
		UserVideo userVideo = new UserVideo();
		userVideo.setUserId(user.getId());
		userVideo.setVideoId(video.getId());
		em.persist(userVideo);
	}
	
	private void increaseReadCnt(Video video) {
		video.setReadcnt(video.getReadcnt() + 1);
		em.persist(video);
	}
	
	/*
	 *	getMain: 메인 페이지로 이동시 동작
	 *
	 * 	1. getVideos에서 모든 video에 대한 레코드들을 불러와 list에 저장
	 * 	2. request "list"에 list를 저장
	 * 	 >> main.jsp를 실행
	 */
	@RequestMapping(value = "/")
	public ModelAndView getMain(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main.jsp");
		
		List<Video> list = getVideos();
		request.setAttribute("list", list);
		System.out.println(list);
		
		return modelAndView;
	}
	
	public List<Video> getVideos() {
		
		TypedQuery<Video> query = em.createQuery("select v from Video v order by v.id desc", Video.class);
		
		return query.getResultList();
	}
	
	/*
	 * like/hate: like / hate 버튼 클릭시 동작
	 * 
	 * 	1. request에서 video에 대한 정보를 받기
	 * 	2. increaseLike/Hate에 video를 넘기기
	 *  3. video의 like/hate을 1씩 증감
	 */
	
	@RequestMapping("/like")
	public ModelAndView like(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("videoRetrieve.jsp");
		
		Video video = (Video) request.getAttribute("video");
		increaseLike(em.find(Video.class, video.getId()));
		
		return modelAndView;
	}
	
	public void increaseLike(Video video) {
		
		video.setLike(video.getLike() + 1);
		em.persist(video);
		tx.commit();
	}
	
	@RequestMapping("/hate")
	public ModelAndView hate(HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("videoRetrieve.jsp");
		
		Video video = (Video) request.getAttribute("video");
		increaseLike(em.find(Video.class, video.getId()));
		
		return modelAndView;
	}
	
	public void increaseHate(Video video) {
		
		video.setHate(video.getHate() + 1);
		em.persist(video);
		tx.commit();
	}
	
}
