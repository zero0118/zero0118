package com.kitware.schedule.service;

public class SchDMLService {
	static private SchDMLService service;

	public SchDMLService() {

	}

	public static SchDMLService getInstance() {
		if (service == null) {
			service = new SchDMLService();
		}
		return service;
	}
}
