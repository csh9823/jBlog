package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired // 다오를 쓰지 않고 서비스에 넘기고 서비스에서 다오를 실행해야됨
	private UserService userService;

	// 로그인 화면
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {

		System.out.println("[UserController.loginForm()]");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");

		System.out.println("--------------------------------");
		System.out.println(userVo);
		System.out.println("--------------------------------");

		UserVo authUser = userService.login(userVo);

		System.out.println("--------------------------------");
		System.out.println(authUser + "authUser");
		System.out.println("--------------------------------");

		if (authUser != null) {
			// 세션에 저장
			System.out.println("세션 성공");
			session.setAttribute("authUser", authUser); // ("authUser" = jstl 꺼내쓸때 쓰는 이름 , authUser)세션에 값 넣어주기
			return "redirect:/";
		} else {
			System.out.println("로그인 실패");
			return "redirect:/user/loginForm?result=fail";
		}

	}

	// 회원가입 폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserComtroller.joinForm()]");

		return "/user/joinForm";
	}

	// 회원가입 완료
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserComtroller.join()]");
		System.out.println(userVo);
		userService.join(userVo);
		return "/user/joinSuccess";
	}

	// 로그아웃
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginout(HttpSession session) {

		System.out.println("로그아웃");
		session.removeAttribute("authUser"); // 같이써줘야됨
		session.invalidate(); // 같이 써줘야됨

		return "redirect:/";

	}

	// 아이디 중복체크
	@ResponseBody //바디에 묶어줌 하나만 선택할때 사용함
	@RequestMapping("/Check")
	public String idCheck(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.Check]");

		String result = userService.Check(userVo);
		
		return result;
	}

}
