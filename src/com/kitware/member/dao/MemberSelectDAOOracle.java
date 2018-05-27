package com.kitware.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kitware.authorization.dao.MyConnection;
import com.kitware.member.vo.DeptInfo;
import com.kitware.member.vo.GradeInfo;
import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;

public class MemberSelectDAOOracle implements MemberSelectDAO {

	// 로그인한 사원의 개인정보 가져오기.
	@Override
	public Members selectMemberInfo(String id) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<GradeInfo> gradeList = new ArrayList<>();
		List<DeptInfo> deptList = new ArrayList<>();
		String selectMemberInfoSQL = "select m.* , g.position_name , d.dept_name\r\n"
				+ "from members m join grade_info g\r\n" + "on m.position_num = g.position_num\r\n"
				+ "join dept_info d\r\n" + "on m.dept_num = d.dept_num\r\n" + "where m.id = ?";

		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(selectMemberInfoSQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next()) { // 아이디가 없는경우
				return null;
			} else {
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

				GradeInfo gi = new GradeInfo(mb.getPosition_num(), rs.getString("POSITION_NAME"));
				mb.setGradeinfo(gi);

				DeptInfo di = new DeptInfo(mb.getDept_num(), rs.getString("DEPT_NAME"));
				mb.setDeptinfo(di);
				return mb;
			}
		} catch (SQLException e) {
			e.printStackTrace(); // 톰캣콘솔
			throw e;
		} finally {
			com.kitware.sql.MyConnection.close(rs, pstmt, con);
		}
	}

	// DB TEST
	public static void main(String[] args) {
		MemberSelectDAOOracle test = new MemberSelectDAOOracle();
		try {
			Members mbtest = test.selectMemberInfo("kim");
			System.out.println(mbtest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void insertMembers(Members members) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		String modmemberSQL = "INSERT INTO members (emp_num, dept_num,position_num,id,pwd,name,gender,"
				+ "email1,email2,tel1,tel2,tel3)\r\n" + "VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(modmemberSQL);
			pstmt.setString(1, members.getEmp_num());
			pstmt.setString(2, members.getDept_num());
			pstmt.setString(3, members.getPosition_num());
			pstmt.setString(4, members.getId());
			pstmt.setString(5, members.getPwd());
			pstmt.setString(6, members.getName());
			pstmt.setString(7, members.getGender());
			pstmt.setString(8, members.getEmail1());
			pstmt.setString(9, members.getEmail2());
			pstmt.setString(10, members.getTel1());
			pstmt.setString(11, members.getTel2());
			pstmt.setString(12, members.getTel3());
			pstmt.executeUpdate();
		} finally {
			MyConnection.close(pstmt, con);
		}
	}	

	@Override
	public void insertMembers(MembersDetailInfo mdetail) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		String mdetailSQL ="insert into members_detail\r\n" + 
				"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			con = com.kitware.sql.MyConnection.getConnection();
			pstmt = con.prepareStatement(mdetailSQL);
			pstmt.setString(1, mdetail.getEmp_num());
			pstmt.setString(2, mdetail.getBirth1());
			pstmt.setString(3, mdetail.getBirth2());
			pstmt.setString(4, mdetail.getBirth3());
			pstmt.setString(5, mdetail.getHire_date1());
			pstmt.setString(6, mdetail.getHire_date2());
			pstmt.setString(7, mdetail.getHire_date3());
			pstmt.setString(8, mdetail.getOut_date1());
			pstmt.setString(9, mdetail.getOut_date2());
			pstmt.setString(10, mdetail.getOut_date3());
			pstmt.setString(11, mdetail.getZip1());
			pstmt.setString(12, mdetail.getZip2());
			pstmt.setString(13, mdetail.getAddr1());
			pstmt.setString(14, mdetail.getAddr2());
			pstmt.setString(15, mdetail.getMarriage());
			pstmt.executeUpdate();			
		} finally {
			MyConnection.close(pstmt, con);
		}
		
	}
}
