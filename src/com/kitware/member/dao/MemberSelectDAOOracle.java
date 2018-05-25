package com.kitware.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;


public class MemberSelectDAOOracle implements MemberSelectDAO{
	
	//로그인한 사원의 개인정보 가져오기.
	@Override
	public Members selectMemberInfo(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<GradeInfo> gradeList = new ArrayList<>();
		List<DeptInfo> deptList = new ArrayList<>();
		String selectMemberInfoSQL = "select m.* , g.position_name , d.dept_name\r\n" + 
				"from members m join grade_info g\r\n" + 
				"on m.position_num = g.position_num\r\n" + 
				"join dept_info d\r\n" + 
				"on m.dept_num = d.dept_num\r\n" + 
				"where m.id = ?";
		
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) { //아이디가 없는경우
				return null;
			}else {
				Members mb = new Members();
				mb.setEmp_num(rs.getString("EMP_NUM"));
				mb.setDept_num(rs.getString("DEPT_NUM"));
				mb.setPosition_num(rs.getString("POSITION_NUM"));
				mb.setId(id);
				mb.setPwd(rs.getString("PWD"));
				mb.setName(rs.getString("NAME"));
				mb.setGender(rs.getString("GENDER"));
				mb.setEmail1(rs.getString("EMAIL1"));
				mb.setEmail2(rs.getString("EMAIL2"));
				mb.setTel1(rs.getString("TEL1"));
				mb.setTel2(rs.getString("TEL2"));
				mb.setTel3(rs.getString("TEL3"));
				
				GradeInfo gi = new GradeInfo(mb.getPosition_num(),rs.getString("POSITION_NAME"));
				gradeList.add(gi);
				mb.setGradeinfo(gradeList);
				
				DeptInfo di = new DeptInfo(mb.getDept_num(),rs.getString("DEPT_NAME"));
				deptList.add(di);
				mb.setDeptinfo(deptList);
				return mb;
			}
		} catch (SQLException e) {
			e.printStackTrace(); //톰캣콘솔
			throw e;			
		} finally {	
			com.kitware.sql.MyConnection.close(rs, pstmt, con);			
		}		
	}
	
	//DB TEST
	public static void main(String[] args) {
		MemberSelectDAOOracle test = new MemberSelectDAOOracle();
		try {
			Members mbtest = test.selectMemberInfo("kim");
			System.out.println(mbtest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
