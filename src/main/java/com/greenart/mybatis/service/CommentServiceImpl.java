package com.greenart.mybatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.mybatis.dao.BoardDao;
import com.greenart.mybatis.dao.CommentDao;
import com.greenart.mybatis.model.CommentDto;

@Service
public class CommentServiceImpl implements CommentService {
	private BoardDao boardDao;
	private CommentDao commentDao;
	public CommentServiceImpl() {}
	@Autowired
	public CommentServiceImpl(BoardDao boardDao, CommentDao commentDao) {
		this.boardDao = boardDao;
		this.commentDao = commentDao;
	}
	
	@Override
	public int getCount(Integer bno) throws Exception {
		return commentDao.count(bno);
	}
	
	@Override
	public int remove(Integer cno, Integer bno, String commenter) throws Exception {
		int rowCnt = boardDao.updateCommentCnt(bno, -1);
		rowCnt = commentDao.delete(cno, commenter);
		return rowCnt;
	}
	
	@Override
	public int write(CommentDto commentDto) throws Exception {
		boardDao.updateCommentCnt(commentDto.getBno(), 1);
		return commentDao.insert(commentDto);
	}
	
	@Override
	public List<CommentDto> getList(Integer bno) throws Exception {
		return commentDao.selectAll(bno);
	}
	
	@Override
	public CommentDto read(Integer cno) throws Exception {
		return commentDao.select(cno);
	}
	
	@Override
	public int modify(CommentDto commentDto) throws Exception {
		return commentDao.update(commentDto);
	}
}








































