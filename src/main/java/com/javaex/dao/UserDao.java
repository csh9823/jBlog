package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	// 회원가입
	public int insert(UserVo userVo) {
		System.out.println("[UserDao.insert()");
		System.out.println(userVo);
		int count = sqlSession.insert("user.insert", userVo);
		System.out.println("[" + count + "건이 등록되었습니다(UserDao)");
		return count;
	}

	// 유저정보가져오기(로그인시 사용)
	public UserVo selectUser(UserVo userVo) {
		System.out.println("[UserDao.selectUser()]");

		System.out.println(userVo);

		// System.out.println(userVo);
		UserVo authUser = sqlSession.selectOne("user.selectUser", userVo);

		System.out.println(authUser + "로그인 완료");

		return authUser;
	}

	// id 중복체크
	public String selectId(UserVo userVo) {
		System.out.println("[UserDao.getId]");
		
		System.out.println(userVo);
		return sqlSession.selectOne("user.selectId", userVo);
	}

}
