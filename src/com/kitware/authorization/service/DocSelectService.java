package com.kitware.authorization.service;

import java.util.List;

import com.kitware.authorization.dao.DocDAO;
import com.kitware.authorization.dao.DocDAOOracle;
import com.kitware.authorization.vo.DocVO;

public class DocSelectService {

	static private DocSelectService service;
	private DocDAO dao = new DocDAOOracle();
	
	public int findCount() throws Exception{
		return dao.selectCount();
	}

	public List<DocVO> findIng(String id) throws Exception {
		System.out.println(dao.selectIng("kim"));
		return dao.selectIng("kim");
	}
	public List<DocVO> findIng(String id, int page) throws Exception{
		System.out.println("service"+dao.selectIng("kim", page));
		return dao.selectIng("kim", page);

	}

	public List<DocVO> findOk(String id) throws Exception {
		System.out.println(dao.selectOk("kim"));
		return dao.selectOk("kim");
	}
	
	public List<DocVO> findOk(String id, int page) throws Exception {
		return dao.selectOk("kim", page);
	}
	
	public List<DocVO> selectExpected(String conf_num, int page) throws Exception{
		return dao.selectExpected("3", page);
		
	}
	public List<DocVO> selectGJOk(String conf_num, int page) throws Exception{
		return dao.selectGJOk("3", page);
		
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
