package com.kitware.schedule.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitware.A.control.Controller;
import com.kitware.schedule.vo.Schedule;

import com.kitware.schedule.service.SchCodeService;

public class SchPersonalController implements Controller {
	private SchCodeService service;
	
	public SchPersonalController() {
	}
	
	public SchPersonalController(SchCodeService service) { 
		this.service = service;
	}

	public SchCodeService getService() {
		return service;
	}


	public void setService(SchCodeService service) {
		this.service = service;
	}


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Schedule> listSchedule = service.findSchPersonal("1");
			request.setAttribute("schedule", listSchedule);
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			e.printStackTrace();
		}
		String forwardURL = "schedule/schfindresult.jsp";
		return forwardURL;
	}

}
