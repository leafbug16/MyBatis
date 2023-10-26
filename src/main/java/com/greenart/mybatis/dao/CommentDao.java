package com.greenart.mybatis.dao;

import java.util.List;

import com.greenart.mybatis.model.CommentDto;

public interface CommentDao {

	int deleteAll(Integer bno) throws Exception;

	int delete(Integer cno, String commenter) throws Exception;

	int count(Integer bno) throws Exception;

	int insert(CommentDto commentDto) throws Exception;

	List<CommentDto> selectAll(Integer bno) throws Exception;

	CommentDto select(Integer cno) throws Exception;

	int update(CommentDto commentDto) throws Exception;

}