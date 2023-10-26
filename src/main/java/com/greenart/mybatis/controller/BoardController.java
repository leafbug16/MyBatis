package com.greenart.mybatis.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.greenart.mybatis.model.BoardDto;
import com.greenart.mybatis.model.PageHandler;
import com.greenart.mybatis.model.SearchCondition;
import com.greenart.mybatis.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	private boolean loginCheck(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return session.getAttribute("id")!=null;
	}
	
	@GetMapping("/list")
	public String list(SearchCondition sc, HttpServletRequest request, Model m) {	
		if(!loginCheck(request))
			return "redirect:/login/form?toURL="+request.getRequestURL();
		
		try {
			int totalCnt = boardService.getSearchResultCnt(sc);
			PageHandler pageHandler = new PageHandler(totalCnt, sc);
			
			Map map = new HashMap();
			List<BoardDto> list = boardService.getSearchResultPage(sc);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);	
			
			Date now = new Date();
			m.addAttribute("now", now);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, SearchCondition sc, HttpServletRequest request, Model m) {
		BoardDto boardDto;
		HttpSession session = request.getSession();
		String sessionId = session.getAttribute("id")+"";
		try {
			boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("sessionId", sessionId);
		} catch (Exception e) {
			System.out.println("board/read 오류");
			e.printStackTrace();
			return "redirect:/board/list"+sc.getQueryString();
		}
		return "view";
	}
	
	@PostMapping("/remove")
	public String remove(Integer bno, SearchCondition sc, HttpSession session, RedirectAttributes redatt) {
		try {
			String writer = (String)session.getAttribute("id");
			int rowCnt = boardService.remove(bno, writer);
			if(rowCnt==1) {
				redatt.addFlashAttribute("msg", "del");
				return "redirect:/board/list";
			} else {
				throw new Exception("board remove error");
			}
		} catch (Exception e) {
			System.out.println("board/remove 오류");
			e.printStackTrace();
			redatt.addFlashAttribute("msg", "error");
		}
		return "redirect:/board/list"+sc.getQueryString();
	}
	
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
	@PostMapping("/write")
	public String save(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes redatt) {
		String writer = session.getAttribute("id")+"";
		boardDto.setWriter(writer);
		try {
			int rowCnt = boardService.write(boardDto);
			if(rowCnt!=1) {
				throw new Exception("Write Error");
			}
			redatt.addFlashAttribute("msg", "write_ok");
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "write_error");
			return "write";
		}
	}
	
	@GetMapping("/modify")
	public String modify(Integer page, Integer pageSize, Integer bno, Model m, HttpSession session, RedirectAttributes redatt) {
		try {
			BoardDto boardDto = boardService.read(bno);
			if (session.getAttribute("id") == null || !boardDto.getWriter().equals(session.getAttribute("id"))) {
				redatt.addFlashAttribute("msg", "modify_error");
				return "redirect:/board/list";
			}
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	
	@PostMapping("/modify")
	public String modifyy(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes redatt) {
		String writer = session.getAttribute("id")+"";
		boardDto.setWriter(writer);
		try {
			int rowCnt = boardService.modify(boardDto);
			if(rowCnt!=1) throw new Exception("modify_Error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "modify_error");
			return "edit";
		}
	}
	
}
































