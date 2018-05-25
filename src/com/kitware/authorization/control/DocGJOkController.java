package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocVO;
import com.kitware.authorization.vo.PageBean;

public class DocGJOkController {
	DocSelectService service = new DocSelectService();

	public DocGJOkController() {
		super();
	}

	public DocGJOkController(DocSelectService service) {
		super();
		this.service = service;
	}

	public DocSelectService getService() {
		return service;
	}

	public void setService(DocSelectService service) {
		this.service = service;
	}

	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		String id = request.getParameter("id");

		int intPage = 1;
		if (page != null) {
			intPage = Integer.parseInt(page);
		}
		try {
			// 게시물 총목록수
			int totalCount = service.findCount();

			// 총페이지수계산
			int totalPage = 0;
			int cntPerPage = 3;// 1페이지별 3건씩 보여준다
			totalPage = (int) Math.ceil((double) totalCount / cntPerPage);

			// 페이지그룹에서 쓰일 시작페이지값, 끝페이지값계산
			int cntPerPageGroup = 5; // 페이지그룹별 5페이지씩 보여준다
			int startPage = (int) Math.floor((double) (intPage) / (cntPerPageGroup + 1)) * cntPerPageGroup + 1;
			int endPage = startPage + cntPerPageGroup - 1;
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			List<DocVO> list = service.findOk(id, intPage);	
			PageBean<DocVO> pb = new PageBean<>();
			pb.setCurrentPage(intPage);// 현재페이지
			pb.setTotalPage(totalPage); // 총페이지
			pb.setList(list); // 목록
			pb.setStartPage(startPage); // 시작페이지
			pb.setEndPage(endPage); // 끝페이지

			request.setAttribute("pagebean", pb);
			System.out.println(list.size());
			System.out.println(pb.getList());
			

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("result", e.getMessage());
		}
		String forwardURL = "/authorization/gj_ok.jsp";
		return forwardURL;
	}
}
