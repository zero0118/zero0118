package com.kitware.schedule.service;

public class SchCodeService {
	static private SchCodeService service;
	public SchCodeService() {
		
	}
	public static SchCodeService getInstance() {
		if(service==null) {
			service = new SchCodeService();
		}
		return service;
	}
}
