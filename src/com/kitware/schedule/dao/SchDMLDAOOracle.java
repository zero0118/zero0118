package com.kitware.schedule.dao;

import com.kitware.schedule.vo.Schedule;

public class SchDMLDAOOracle implements SchDMLDAO {
	//SchDMLDAO에서 오버라이딩한 메소드 구현.
	
	@Override
	public String insertSchedule(Schedule schedule) throws Exception {
		// insert 성공하면 1 실패하면 -1 return.
		return null;
	}

}
