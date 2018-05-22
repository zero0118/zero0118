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
	public List<DocVO> selectIng(String id) throws Exception { //진행문서(내가올린거) table list 뿌려주는 select
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
	public List<DocVO> selectOk(String emp_num) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select

		// 에러처리 catch로 안함
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String selectIngSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state"
					+ " from document d, doc_kind dk" + " where d.doc_kind = dk.doc_kind" + " and d.emp_num = ?"
					+ " and d.doc_state = 2";

			List<DocVO> list = new ArrayList<>(); // 사이즈 변경 가능하며 null허용하는 arraylist selectIng이랑 list 같아도되나
			con = MyConnection.getConnection();
			pstmt = con.prepareStatement(selectIngSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new DocVO(rs.getString("START_DATE"), rs.getString("DOC_NAME"), rs.getString("DOC_TITLE"),
						rs.getString("DOC_NUM"), rs.getString("DOC_STATE")));
			}
			return list;
		} finally {
			MyConnection.close(rs, pstmt, con);
		}
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
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
