package com.kitware.authorization.service;

import java.util.List;

import com.kitware.authorization.dao.DocDAO;
import com.kitware.authorization.dao.DocDAOOracle;
import com.kitware.authorization.vo.DocVO;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

public class DocSelectService {
	
	static private DocSelectService service;
	private DocDAO dao = new DocDAOOracle();
	
	public List<DocVO> findIng(String id) throws Exception{
		System.out.println(dao.selectIng("kim"));
		return dao.selectIng("kim");
	}
	public List<DocVO> findOk(String id) throws Exception{
		return dao.selectOk("kim");
	}

	public DocSelectService() {

	}

	public static DocSelectService getInstance() {
		if (service == null) {
			service = new DocSelectService();
		}
		return service;
	}
}
