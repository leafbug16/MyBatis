package com.greenart.mybatis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.mybatis.dao.BoardDao;
import com.greenart.mybatis.model.BoardDto;

// �������� ���� ó�� 
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDao boardDao; // BoardDao ���� 

    // �Խ��� ���� ��
		@Override
	public int getCount() throws Exception { 
        return boardDao.count();
    }

    // �Խ��� �� ����
		@Override
	public int remove(Integer bno, String writer) throws Exception {
        return boardDao.delete(bno, writer);
    }

    // �Խ��� ����
		@Override
	public int write(BoardDto boardDto) throws Exception {
        // �Խ��� ���� ���� Ȯ�ο� 
    	//throw new Exception("write error");
    	return boardDao.insert(boardDto);
    }
    
    // �Խ��� ��� ���
		@Override
	public List<BoardDto> getList() throws Exception {
        return boardDao.selectAll();
    }

    // �Խ��� �б� = �Խ��� ��ȸ + ��ȸ�� 1����
		@Override
	public BoardDto read(Integer bno) throws Exception {
        BoardDto boardDto = boardDao.select(bno); // ��ȸ �� �о����Ƿ� ��ȸ�� 1 ����
        boardDao.increaseViewCnt(bno);
        return boardDto;
    }

    // �Խ��� �������� ��ȸ
		@Override
	public List<BoardDto> getPage(Map map) throws Exception {
        return boardDao.selectPage(map);
    }

    // ����
		@Override
	public int modify(BoardDto boardDto) throws Exception {
        return boardDao.update(boardDto);
    }
		
//	// �˻� ��� �Խù� ��
//    @Override
//	public int getSearchResultCnt(SearchCondition sc) throws Exception {
//        return boardDao.searchResultCnt(sc);
//    }
//    // �˻� ��� ��ȸ
//    @Override
//	public List<BoardDto> getSearchResultPage(SearchCondition sc) throws Exception {
//        return boardDao.searchSelectPage(sc);
//    }
    
}