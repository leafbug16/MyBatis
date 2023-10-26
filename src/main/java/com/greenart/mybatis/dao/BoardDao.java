package com.greenart.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.greenart.mybatis.model.BoardDto;
import com.greenart.mybatis.model.SearchCondition;

public interface BoardDao {
	
	List<BoardDto> selectPage(Map map) throws Exception;

	int deleteAll() throws Exception;

	int delete(Integer bno, String writer) throws Exception;

	int insert(BoardDto dto) throws Exception;

	List<BoardDto> selectAll() throws Exception;

	BoardDto select(int bno) throws Exception;

	int increaseViewCnt(Integer bno) throws Exception;

	int count() throws Exception;
	
	int update(BoardDto dto) throws Exception;
	
	List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
	
	public int searchResultCnt(SearchCondition sc) throws Exception;

	int updateCommentCnt(Integer bno, Integer comment_cnt);

}