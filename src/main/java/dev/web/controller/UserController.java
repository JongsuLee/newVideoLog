package dev.web.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import dev.web.model.User;

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
	
}
