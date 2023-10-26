package com.greenart.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.mybatis.model.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	SqlSession session;
	//Mapper에 정의한 namespace를 저장하고 사용한다
	String namespace = "com.greenart.commentMapper.";
	
	@Override
	public int deleteAll(Integer bno) throws Exception {
		return session.delete(namespace+"deleteAll", bno);
	}
	
	@Override
	public int delete(Integer cno, String commenter) throws Exception {
		Map map = new HashMap();
		map.put("cno", cno);
		map.put("commenter", commenter);
		return session.delete(namespace+"delete", map);
	}
	
	@Override
	public int count(Integer bno) throws Exception {
		return session.selectOne(namespace+"count", bno);
	}
	
	@Override
	public int insert(CommentDto commentDto) throws Exception {
		return session.insert(namespace+"insert", commentDto);
	}
	
	@Override
	public List<CommentDto> selectAll(Integer bno) throws Exception {
		return session.selectList(namespace+"selectAll", bno);
	}
	
	@Override
	public CommentDto select(Integer cno) throws Exception {
		return session.selectOne(namespace+"select", cno);
	}
	
	@Override
	public int update(CommentDto commentDto) throws Exception {
		return session.update(namespace+"update", commentDto);
	}
	
}
































