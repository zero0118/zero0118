package com.kitware.authorization.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocKindVO;
import com.kitware.authorization.vo.DocVO;
import com.kitware.authorization.vo.PageBean;




public class DocDAOOracle implements DocDAO {
	
	public int selectCount() throws Exception{
		//totalcnt 가져오기
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String selectCountSQL = 
				"SELECT COUNT(*) doc_num FROM document";
		try {
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectCountSQL);
			rs = pstmt.executeQuery();
			rs.next();
			int totalCount = rs.getInt("doc_num");
			return totalCount;
		}finally {
			MyConnection.close(rs, pstmt, con);
		}
	}

	@Override
	public List<DocVO> selectAll() throws Exception {
		return null;
	}

	@Override
	public List<DocVO> selectIng(String id) throws Exception { 
		//Main의 진행문서(내가올린거) table list 뿌려주는 select
		//매개변수 id 보여주는 갯수 제한 필요
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectIngSQL = "select d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
							+" from document d, doc_kind dk, members m"
							+" where d.doc_kind = dk.doc_kind"
							+" and m.id = ?";
		
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
		List<DocKindVO> kindlist = new ArrayList<>(); //doc_kind arraylist
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectIngSQL);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setDoc_kind(rs.getInt("doc_kind"));
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_state(rs.getString("doc_state"));
			DocKindVO dock = new DocKindVO(docvo.getDoc_kind(),rs.getString("doc_name"));
			kindlist.add(dock);
			docvo.setDoc_kindvo(kindlist);
			doclist.add(docvo);	
		}
		
		
		
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return doclist;
	}
	
	@Override
	public List<DocVO> selectIng(String id, int page) throws Exception {
		//사이드 메뉴 내가올린 기안 전부 보기 , 매개변수 id, page 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectIngSQL = "select d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
							+" from document d, doc_kind dk, members m"
							+" where d.doc_kind = dk.doc_kind"
							+" and m.id = ?";
		
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
		List<DocKindVO> kindlist = new ArrayList<>(); //doc_kind arraylist
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectIngSQL);
		pstmt.setString(1, id);
		/*int cntPerPage=5;//1페이지별 5건씩 보여준다
		int endRow=cntPerPage * page;
		int startRow=endRow-cntPerPage+1; 			
		pstmt.setInt(1, startRow);	pstmt.setInt(2, endRow);*/
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setDoc_kind(rs.getInt("doc_kind"));
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_state(rs.getString("doc_state"));
			DocKindVO dock = new DocKindVO(docvo.getDoc_kind(),rs.getString("doc_name"));
			kindlist.add(dock);
			docvo.setDoc_kindvo(kindlist);
			doclist.add(docvo);	
		}
		
		
		
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return doclist;
	}
	
	

	@Override
	public List<DocVO> selectOk(String id) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			String selectOkSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state"
								+" from document d, doc_kind dk, members m"
								+" where d.doc_kind = dk.doc_kind"
								+" and d.doc_state = 2"
								+" and m.id = ?";
			List<DocVO> doclist2 = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
			DocVO docvo2 = null;	//doc 데이터 담음
			List<DocKindVO> kindlist2 = new ArrayList<>(); //doc_kind arraylist
			DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				kindlist2.add(dock2);
				docvo2.setDoc_kindvo(kindlist2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}	
		
			

	@Override
	public List<DocDetailVO> selectExpected(String conf_num) throws Exception {
		return null;

	}
	
	public static void main(String[] args) {
		DocDAOOracle test = new DocDAOOracle();
		try {
			List<DocVO> list = test.selectIng("kim");	
			System.out.println("리스트사이즈:"+list.size());
			List<DocVO> list2 = test.selectOk("kim");
			System.out.println("ㅁㅁㅁㅁ"+list2.size());
			List<DocVO> list3 = test.selectIng("kim",0);
			System.out.println(list3.size());

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
