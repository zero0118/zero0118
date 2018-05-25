package com.kitware.authorization.dao;

import java.util.List;

import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocVO;

public interface DocDAO {
	public int selectCount() throws Exception;//totalcnt값 가져옴
	public List<DocVO> selectAll() throws Exception; //Document 내용 뿌려줌
	public List<DocVO> selectIng(String id) throws Exception; //진행문서 select (내가 올린거)
	public List<DocVO> selectIng(String id, int page) throws Exception; //내가 올린 기안 다 보기(page 매개변수 추가)
	public List<DocVO> selectOk(String id) throws Exception; //완료문서 select (내가 올린거)  
	public List<DocDetailVO> selectExpected(String conf_num) throws Exception; //결재예정 select  
	//다 구현한거 아니고 추가해야됩니다
	
}
