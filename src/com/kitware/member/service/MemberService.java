package com.kitware.member.service;

public class MemberService {
	static private MemberService service;

	public MemberService() {

	}

	public static MemberService getInstance() {
		if (service == null) {
			service = new MemberService();
		}
		return service;
	}
}
