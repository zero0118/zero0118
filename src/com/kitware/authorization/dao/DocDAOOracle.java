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
	public List<DocVO> selectIng(String emp_num) throws Exception { 
		//Main의 진행문서(내가올린거) table list 뿌려주는 select
		//매개변수 id 보여주는 갯수 제한 필요
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String selectIngSQL = "select d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
							+" from document d, doc_kind dk"
							+" where d.doc_kind = dk.doc_kind"
							+" and d.emp_num = ?";
		
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectIngSQL);
		pstmt.setString(1, emp_num);
		rs = pstmt.executeQuery();
		while(rs.next()) {
			docvo = new DocVO();
			docvo.setStart_date(rs.getString("start_date"));
			docvo.setDoc_kind(rs.getInt("doc_kind"));
			docvo.setDoc_title(rs.getString("doc_title"));
			docvo.setDoc_num(rs.getString("doc_num"));
			docvo.setDoc_state(rs.getString("doc_state"));
			DocKindVO dock = new DocKindVO(docvo.getDoc_kind(),rs.getString("doc_name"));
			docvo.setDoc_kindvo(dock);
			doclist.add(docvo);	
		}
		
		
		
	}finally{
		MyConnection.close(rs,pstmt,con);
	}
		return doclist;
	}
	
	@Override
	public List<DocVO> selectIng(String emp_num, int page) throws Exception {
		//사이드 메뉴 내가올린 기안 전부 보기 , 매개변수 id, page 
		Connection con = null;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;

		String selectIngSQL = "select b.*" 
						+" from ("
						+" select rownum r, d.start_date, d.doc_kind, d.doc_title, d.doc_num, d.doc_state, dk.doc_name"
						+" from document d, doc_kind dk"
						+" where d.doc_kind = dk.doc_kind"
						+" and d.emp_num = ?"
						+" order by doc_num desc) b"
						+" where r between ? and ?";
		
		List<DocVO> doclist = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo = null;	//doc 데이터 담음
		List<DocKindVO> kindlist = new ArrayList<>(); //doc_kind arraylist
		
	try {
		con= MyConnection.getConnection();
		pstmt = con.prepareStatement(selectIngSQL);
		pstmt.setString(1, emp_num);
		int cntPerPage=5;//1페이지별 5건씩 보여준다
		int endRow=cntPerPage * page;
		int startRow=endRow-cntPerPage+1; 			
		pstmt.setInt(2, startRow);	
		pstmt.setInt(3, endRow);
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
	public List<DocVO> selectOk(String emp_num) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
			String selectOkSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state"
								+" from document d, doc_kind dk"
								+" where d.doc_kind = dk.doc_kind"
								+" and d.doc_state = 2"
								+" and d.emp_num = ?";
			List<DocVO> doclist2 = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
			DocVO docvo2 = null;	//doc 데이터 담음
			DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, emp_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}	
	@Override
	public List<DocVO> selectOk(String emp_num, int page) throws Exception { // 내가올린거 상태 완료인 문서 list로 뿌려주는 select
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectOkSQL = "select d.start_date, dk.doc_name, d.doc_title, d.doc_num, d.doc_state"
				+" from document d, doc_kind dk"
				+" where d.doc_kind = dk.doc_kind"
				+" and d.doc_state = 2"
				+" and d.emp_num = ?";
		List<DocVO> doclist2 = new ArrayList<>(); //사이즈 변경 가능하며 null허용하는 arraylist
		DocVO docvo2 = null;	//doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectOkSQL);
			pstmt.setString(1, emp_num);
			/*int cntPerPage=5;//1페이지별 5건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1; 			
			pstmt.setInt(1, startRow);	
			pstmt.setInt(2, endRow);
			위에 쿼리 수정해야 먹음*/
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setStart_date(rs.getString("start_date"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}	
		
			

	@Override
	public List<DocVO> selectExpected(String conf_num, int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectExpectedSQL = "select b.*"
				+" from("
				+" select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name"
				+" from document d, doc_detail dd, doc_kind dk"
				+" where d.doc_num = dd.doc_num"
				+" and d.doc_kind = dk.doc_kind"
				+" and conf_num = ?"
				+" and acs_yn = 0) b"
				+" where r between ? and ?";
		List<DocVO> doclist2 = new ArrayList<>(); //이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null;	//doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectExpectedSQL);
			pstmt.setString(1, conf_num);
			int cntPerPage=5;//1페이지별 5건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1; 			
			pstmt.setInt(2, startRow);	
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;

	}
	
	
	@Override
	public List<DocVO> selectGJOk(String conf_num, int page) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String selectGJOkSQL = "select b.*"
				+" from("
				+" select  rownum r, d.doc_num, d.doc_title, d.doc_state, d.start_date, dk.doc_name"
				+" from document d, doc_detail dd, doc_kind dk"
				+" where d.doc_num = dd.doc_num"
				+" and d.doc_kind = dk.doc_kind"
				+" and conf_num = ?"
				+" and acs_yn = 1) b"
				+" where r between ? and ?";
		List<DocVO> doclist2 = new ArrayList<>(); //이부분부터 수정들어가야함 0525 오후 4:43
		DocVO docvo2 = null;	//doc 데이터 담음
		DocKindVO dock2 = new DocKindVO();//dockind 데이터 담음
		try {
			con= MyConnection.getConnection();
			pstmt = con.prepareStatement(selectGJOkSQL);
			pstmt.setString(1, conf_num);
			int cntPerPage=5;//1페이지별 5건씩 보여준다
			int endRow=cntPerPage * page;
			int startRow=endRow-cntPerPage+1; 			
			pstmt.setInt(2, startRow);	
			pstmt.setInt(3, endRow);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				docvo2 = new DocVO();
				docvo2.setDoc_num(rs.getString("doc_num"));
				docvo2.setDoc_title(rs.getString("doc_title"));
				docvo2.setDoc_state(rs.getString("doc_state"));
				docvo2.setStart_date(rs.getString("start_date"));
				dock2 = new DocKindVO(docvo2.getDoc_kind(),rs.getString("doc_name"));
				docvo2.setDoc_kindvo(dock2);
				doclist2.add(docvo2);	
			}
			
		}finally{
			MyConnection.close(rs,pstmt,con);
		}
		return doclist2;
	}
	
	
	public static void main(String[] args) {
		DocDAOOracle test = new DocDAOOracle();
		try {
			List<DocVO> list = test.selectIng("1");	
			System.out.println("리스트사이즈:"+list.size());
			List<DocVO> list2 = test.selectOk("kim");
			System.out.println("ㅁㅁㅁㅁ"+list2.size());
			List<DocVO> list3 = test.selectIng("1",1);
			System.out.println("3333aa"+list3.size());
			List<DocVO> list4 = test.selectOk("kim",1);
			System.out.println("44aa"+list4.size());
			Integer sc = test.selectCount();
			System.out.println("listsc"+sc.SIZE);
			List<DocVO> list5 = test.selectExpected("3", 1);
			System.out.println(list5.size());
			List<DocVO> list6 = test.selectGJOk("3", 1);
			System.out.println("list6:"+list6.size());
			
			

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
