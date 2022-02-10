package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service //서비스 할때 필요함
public class UserService {

	@Autowired // 다오로 넘겨줌
	private UserDao userDao;
	
	@Autowired
	private BlogDao Blogdao;

	// 회원가입
	public void join(UserVo uservo) {
		System.out.println("UserService/join");
		System.out.println(uservo);
		userDao.insert(uservo);
		
		
		//블로그 자동생성
		BlogVo Blogvo = new BlogVo();
		Blogvo.setId(uservo.getId());
		Blogvo.setBlogTitle(uservo.getUserName()+"의 블로그입니다.");
		Blogvo.setLogoFile("spring-logo.jpg"); //기본 파일이 올라가므로 고정 값으로 기본 파일 값 넣어줌
		
		System.out.println(Blogvo);
		
		Blogdao.blog(Blogvo);
		
	}

	// 로그인
	public UserVo login(UserVo userVo) {
		System.out.println("UserService/login");
		System.out.println(userVo);
		UserVo authUser = userDao.selectUser(userVo);
		return authUser;
	}
	
	//id 중복체크
	public String Check(UserVo userVo) {
		System.out.println("[UserService.idCheck]");

		String resultId = userDao.selectId(userVo);

		if (resultId == null) {
			return "success";
		} else {
			return "fail";
		}
	}
}
