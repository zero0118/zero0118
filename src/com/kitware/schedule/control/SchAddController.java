package com.kitware.schedule.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.schedule.vo.Schedule;
import com.kitware.schedule.service.SchDMLService;

public class SchAddController implements Controller {
	private SchDMLService service;
	
	public SchAddController() {
	
	}

	public SchDMLService getService() {
		return service;
	}


	public void setService(SchDMLService service) {
		this.service = service;
	}




	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String title= request.getParameter("title");
		String schsp= request.getParameter("schsp");
		String startdate= request.getParameter("startdate");
		String starthour= request.getParameter("starthour");
		String startminute= request.getParameter("startminute");
		String enddate= request.getParameter("enddate");
		String endhour= request.getParameter("endhour");
		String endminute= request.getParameter("endminute");
		String repeatbl=request.getParameter("repeatbl");
		String repeatterm=request.getParameter("repeatterm");
		String contents=request.getParameter("contents");
		Schedule schedule = new Schedule();
		schedule.setSch_name(title);
		schedule.setSch_type(schsp);
		schedule.setSch_startdate(startdate);
		schedule.setSch_starthour(starthour);
		schedule.setSch_startmin(startminute);
		schedule.setSch_enddate(enddate);
		schedule.setSch_endhour(endhour);
		schedule.setSch_endmin(endminute);
		schedule.setSch_repeat(repeatbl);
		schedule.setSch_repeatcycle(repeatterm);
		schedule.setSch_contents(contents);
		
		try {
			service.scheduleinput(schedule);
			request.setAttribute("result", 1);
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("result", -1);
		}
		String forwardURL = "scheduleaddresult.jsp";
		return forwardURL;
	}

}
