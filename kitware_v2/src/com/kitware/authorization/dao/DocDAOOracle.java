package com.kitware.authorization.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.vo.DocDetailVO;
import com.kitware.authorization.vo.DocKindVO;
import com.kitware.authorization.vo.DocVO;



public class DocDAOOracle implements DocDAO {

	@Override
	public List<DocVO> selectAll() throws Exception {
		return null;
	}

	@Override
	public List<DocVO> selectIng(String id) throws Exception { //Main의 진행문서(내가올린거) table list 뿌려주는 select
		//에러처리 catch로 안함
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectIngSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state"
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
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_state(rs.getString("doc_state"));
			DocKindVO dock = new DocKindVO();
			dock.setDoc_name(rs.getString("doc_name"));
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
				dock2.setDoc_name(rs.getString("doc_name"));
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

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
