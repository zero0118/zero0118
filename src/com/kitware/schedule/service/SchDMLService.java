package com.kitware.schedule.service;

import com.kitware.schedule.vo.Schedule;
import com.kitware.schedule.dao.SchDMLDAO;
import com.kitware.schedule.dao.SchDMLDAOOracle;

public class SchDMLService {
	static private SchDMLService service;
	private SchDMLDAO dao = new SchDMLDAOOracle();

	public SchDMLService() {

	}
	public static SchDMLService getInstance() {
		if (service == null) {
			service = new SchDMLService();
		}
		return service;
	}
	
	public void scheduleinput(Schedule schedule) throws Exception{
		
	}
}
