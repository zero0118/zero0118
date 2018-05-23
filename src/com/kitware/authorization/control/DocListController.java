package com.kitware.authorization.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kitware.A.control.Controller;
import com.kitware.authorization.service.DocSelectService;
import com.kitware.authorization.vo.DocVO;

public class DocListController implements Controller {
	private DocSelectService service;

	public DocListController() {
		super();
	}
	public DocListController(DocSelectService service) {
		super();
		this.service = service;
	}
	
	public DocSelectService getService() {
		return service;
	}
	public void setService(DocSelectService service) {
		this.service = service;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*String idVal = request.getParameter("id");*/
		HttpSession session = request.getSession();
		session.invalidate();
		
		try {
			List<DocVO> docvo_list = service.findIng("kim"); //id 들어가야함 지금은 임시값 kim
			System.out.println("docvo_list size:"+docvo_list.size());
			request.setAttribute("docvo_list", docvo_list);
			
			List<DocVO> docvo_list2 = service.findOk("kim"); //id 들어가야함 지금은 임시값 kim
			System.out.println("docvo_list size2:"+docvo_list2.size());
			request.setAttribute("docvo_list2", docvo_list2);
			
		} catch (Exception e) {
			request.setAttribute("result", e.getMessage());
			e.printStackTrace();
		}
		
		String forwardURL = "authorization/approveMain.jsp";
		return forwardURL;
	}

}
