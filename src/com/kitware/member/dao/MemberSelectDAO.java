package com.kitware.member.dao;

import com.kitware.member.vo.Members;
import com.kitware.member.vo.MembersDetailInfo;

public interface MemberSelectDAO {
	//로그인한 사원의 정보 가져오기
	Members selectMemberInfo(String id) throws Exception;
		
	void insertMembers(Members members) throws Exception;
	void insertMembers(MembersDetailInfo mdetail) throws Exception;
}
