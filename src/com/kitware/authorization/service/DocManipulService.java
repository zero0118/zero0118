package com.kitware.authorization.service;

public class DocManipulService {
	static private DocManipulService service;

	public DocManipulService() {

	}

	public static DocManipulService getInstance() {
		if (service == null) {
			service = new DocManipulService();
		}
		return service;
	}
}
