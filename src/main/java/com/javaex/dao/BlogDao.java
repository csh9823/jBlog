package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BlogVo;

@Repository
public class BlogDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//블로그 생성
	public int blog(BlogVo Blogvo) {
		System.out.println("[BlogDao.blogVo]");
		System.out.println(Blogvo);
		
		int count = sqlSession.insert("blog.setblog",Blogvo);
		System.out.println(count+"개의 블로그가 생성되었습니다.");
		return count;
	}
	
}
