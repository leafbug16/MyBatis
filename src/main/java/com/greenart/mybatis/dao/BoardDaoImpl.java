package com.greenart.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greenart.mybatis.model.BoardDto;
import com.greenart.mybatis.model.SearchCondition;

@Repository
public class BoardDaoImpl implements BoardDao {
	//SqlSession ��ü�� �����ͼ� sql�޼��带 ����ϴ� ������� �����Ѵ�
	@Autowired
	SqlSession session;
	//Mapper�� ������ namespace�� �����ϰ� ����Ѵ�
	String namespace = "com.greenart.boardMapper.";
	
	//�޼������ �ƹ����Գ� �ص� �ǳ� namespace������ ���°��� Mapper�� id�� ���ƾ��Ѵ�
	@Override
	public List<BoardDto> selectPage(Map map) throws Exception {
		return session.selectList(namespace+"selectPage", map);
	}
	
	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace+"deleteAll");
	}
	
	@Override
	public int delete(Integer bno, String writer) throws Exception {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		return session.delete(namespace+"delete", map);
	}
	
	@Override
	public int insert(BoardDto dto) throws Exception {
		return session.insert(namespace+"insert", dto);
	}
	
	@Override
	public List<BoardDto> selectAll() throws Exception {
		return session.selectList(namespace+"selectAll");
	}
	
	@Override
	public BoardDto select(int bno) throws Exception {
		return session.selectOne(namespace+"select", bno);
	}
	
	@Override
	public int increaseViewCnt(Integer bno) throws Exception {
		return session.update(namespace+"increaseViewCnt", bno);
	}
	
	@Override
	public int count() throws Exception {
		return session.selectOne(namespace+"count");
	}
	
	@Override
	public int update(BoardDto dto) throws Exception {
		return session.update(namespace+"update", dto);
	}
	
	@Override
	public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
		return session.selectList(namespace+"searchSelectPage", sc);
	}
	
	@Override
	public int searchResultCnt(SearchCondition sc) throws Exception {
		return session.selectOne(namespace+"searchResultCnt", sc);
	}
	
	@Override
	public int updateCommentCnt(Integer bno, Integer comment_cnt) {
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("comment_cnt", comment_cnt);
		return session.update(namespace+"updateCommentCnt", map);
	}
	
}










































