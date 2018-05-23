package com.kitware.schedule.dao;

import java.util.List;

import com.kitware.schedule.vo.Schedule;

public interface SchDMLDAO {
	//오버라이딩 될 추상메소드 적기
	

	//로그인한 사원의 개인 일정 가져오기 [개인일정]
	String insertSchedule(Schedule schedule) throws Exception;
	
}
