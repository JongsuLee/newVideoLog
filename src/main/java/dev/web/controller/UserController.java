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
			// �븘�씠�뵒 �샊�� 鍮꾨�踰덊샇媛� 留욎� �븡�� 寃쎌슦 �삁�쇅諛쒖깮
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

	/*
	 * getProfile: �봽濡쒗븘 �럹�씠吏�濡� �씠�룞�떆 �룞�옉
	 *
	 * 1. session�뿉 �엳�뒗 login �젙蹂대�� 諛쏄린 2. listVideo瑜� �넻�빐 UserVideo table�뿉�꽌
	 * �빐�떦 �쑀��媛� �룷�븿�맂 �젅肄붾뱶�뱾�쓣 遺덈윭�� list�뿉 ���옣 3. getVideos瑜� �넻�빐 Video
	 * table�뿉�꽌 �빐�떦 list媛� �룷�븿�맂 video�뱾�쓣 遺덈윭�� listVideo�뿉 ���옣 3. request
	 * "videoLog"�뿉 listVideo瑜� ���옣 >> profile.jsp瑜� �떎�뻾
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
	 * clickVideo
	 */

	public String clickVideo(HttpServletRequest request, HttpServletResponse response,
			@SessionAttribute("login") User user, @RequestParam("") long videoId) {

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
