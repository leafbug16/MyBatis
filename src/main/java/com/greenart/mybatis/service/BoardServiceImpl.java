package com.greenart.mybatis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.mybatis.dao.BoardDao;
import com.greenart.mybatis.model.BoardDto;

// 실질적인 로직 처리 
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao; // BoardDao 주입 

    // 게시판 글의 수
		@Override
	public int getCount() throws Exception { 
        return boardDao.count();
    }

    // 게시판 글 삭제
		@Override
	public int remove(Integer bno, String writer) throws Exception {
        return boardDao.delete(bno, writer);
    }

    // 게시판 쓰기
		@Override
	public int write(BoardDto boardDto) throws Exception {
        // 게시판 쓰기 에러 확인용 
    	//throw new Exception("write error");
    	return boardDao.insert(boardDto);
    }
    
    // 게시판 목록 출력
		@Override
	public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    // 게시판 읽기 = 게시판 조회 + 조회수 1증가
		@Override
	public BoardDto read(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.select(bno); // 조회 후 읽었으므로 조회수 1 증가
        boardDao.increaseViewCnt(bno);
        return boardDto;
    }

    // 게시판 페이지로 조회
		@Override
	public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    // 수정
		@Override
	public int modify(BoardDto boardDto) throws Exception {
        return boardDao.update(boardDto);
    }
		
//	// 검색 결과 게시물 수
//    @Override
//	public int getSearchResultCnt(SearchCondition sc) throws Exception {
//        return boardDao.searchResultCnt(sc);
//    }
//    // 검색 결과 조회
//    @Override
//	public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception {
//        return boardDao.searchSelectPage(sc);
//    }
    
}