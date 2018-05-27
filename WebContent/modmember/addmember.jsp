<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(function() {
		$('form#modmember').submit(function() {

			$.ajax({
				url : '${pageContext.request.contextPath}/modmember.do',
				method : 'post',
				data : $('form').serialize(),
				success : function(data) {
					data = data.trim();
					console.log("연결성공")
					if (data == '1') {
						alert('입력성공');
						console.log("ajax입력성공")
					} else if (data == '-1') {
						alert('입력실패');
					}
				}
			});
			return false;
		});
		$('#mdetailinput').click(function() {
			if ($('div.memberdetail').css('display') == 'none') {
				$('div.memberdetail').show();
			} else {
				$('div.memberdetail').hide();
			}
		})
	});
</script>


<body>
	<form id=modmember>
		<input name="emp_num">사번 <input name="dept_num">부서번호 <input
			name="position_num">직급 <input name="id">아이디 <input
			name="pwd">비밀번호 <input name="name">이름 <input
			name="gender">성별 <input name="email1">이메일1 <input
			name="email2">이메일2 <input name="tel1">tel1 <input
			name="tel2">tel2 <input name="tel3">tel3
		<button id="mdetailinput">상세정보입력</button>
		<div class="memberdetail" style="display: none">
			<input name="birthyear"><input name="birthmonth"><input
				name="birthday">생년월일<input name="hireyear"><input
				name="hiremonth"> <input name="hireday">고용년월일<input
				name="outyear"><input name="outmonth"><input
				name="outday">퇴사년월일 <input name="zip1"><input
				name="zip2">우편번호<input name="addr1">주소<input
				name="addr2">상세주소<input name="marriage">결혼여부
		</div>
		<input type="submit">전송
	</form>
</body>
</html>
<%@include file="../container/footer.jsp" %>