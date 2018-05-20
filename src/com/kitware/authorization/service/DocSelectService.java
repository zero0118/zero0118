package com.kitware.authorization.service;

public class DocSelectService {
	static private DocSelectService service;

	public DocSelectService() {

	}

	public static DocSelectService getInstance() {
		if (service == null) {
			service = new DocSelectService();
		}
		return service;
	}
}
