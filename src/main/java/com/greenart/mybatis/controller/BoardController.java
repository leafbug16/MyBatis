package com.greenart.mybatis.controller;

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
	public String list(Integer page, Integer pageSize, HttpServletRequest request, Model m) {
		if(page==null) page=1;
		if(pageSize==null) pageSize=10;
		
		if(!loginCheck(request))
			return "redirect:/login/form?toURL="+request.getRequestURL();
		
		try {
			int totalCnt = boardService.getCount();
			PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
			
			Map map = new HashMap();
			map.put("offset", (page-1)*pageSize);
			map.put("pageSize", pageSize);
			List<BoardDto> list = boardService.getPage(map);
			m.addAttribute("list", list);
			m.addAttribute("ph", pageHandler);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "board";
	}
	
	@GetMapping("/read")
	public String read(Integer bno, Integer page, Integer pageSize, HttpServletRequest request, Model m) {
		BoardDto boardDto;
		HttpSession session = request.getSession();
		String sessionId = session.getAttribute("id")+"";
		try {
			boardDto = boardService.read(bno);
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("page", page);
			m.addAttribute("pageSize", pageSize);
			m.addAttribute("sessionId", sessionId);
		} catch (Exception e) {
			System.out.println("board/read 오류");
			e.printStackTrace();
		}
		return "view";
	}
	
	@PostMapping("/remove")
	public String remove(Integer bno, Integer page, Integer pageSize, HttpSession session, RedirectAttributes redatt) {
		try {
			redatt.addAttribute("page", page);
			redatt.addAttribute("pageSize", pageSize);
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
		return "redirect:/board/list?page="+page+"&pageSize="+pageSize;
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
	public String modifyy(Integer page, Integer pageSize, BoardDto boardDto, Model m, HttpSession session, RedirectAttributes redatt) {
		System.out.println("modify에서 page, pageSize : " + page + pageSize);
		String writer = session.getAttribute("id")+"";
		boardDto.setWriter(writer);
		try {
			int rowCnt = boardService.modify(boardDto);
			if(rowCnt!=1) throw new Exception("modify_Error");
			redatt.addFlashAttribute("msg", "modify_ok");
			return "redirect:/board/list?page="+page+"&pageSize="+pageSize;
		} catch (Exception e) {
			e.printStackTrace();
			m.addAttribute("boardDto", boardDto);
			m.addAttribute("msg", "modify_error");
			return "edit";
		}
	}
	
}
































