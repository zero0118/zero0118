<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<script>
	var className = 'modmember';
	$('div#menutab li.' + className).addClass('active');
	console.log($('div#menutab li.' + className));
	$('ul#side-menu').find('li.' + className).show();
</script>

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
					if (data == '1') {
						alert('정보수정완료');
						location.href="memberstatus.jsp";
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
		$('#cancel').click(function(){
			location.href="memberstatus.jsp";		
		})
	});
</script>


<body>
<h2>사원정보수정</h2>
	<form id=modmember>
		<table>
			<tr>
				<td><label>사번</label></td>
				<td><input name="emp_num"></td>
				<td><label>부서번호</label></td>
				<td><input name="dept_num"></td>
				<td><label>직급</label></td>
				<td><input name="position_num"></td>
			</tr>
			<tr>
				<td><label>아이디</label></td>
				<td><input name="id"></td>
				<td><label>비밀번호</label></td>
				<td><input name="pwd"></td>
				<td><label>이름</label></td>
				<td><input name="name"></td>
			</tr>
			<tr>
				<td><label>성별</label></td>
				<td><input name="gender"></td>
				<td><label>이메일1</label></td>
				<td><input name="email1"></td>
				<td><label>이메일2</label></td>
				<td><input name="email2"></td>
			</tr>
			<tr>
				<td><label>tel</label></td>
				<td colspan="4"><input name="tel1" placeholder="010"> <input
					name="tel2" placeholder="xxxx"> <input name="tel3"
					placeholder="xxxx"></td>
			</tr>
			<tr>
				<td><input type="button" id="mdetailinput" value="상세정보입력">
				</td>
			</tr>
		</table>

		<div class="memberdetail" style="display: none">
			<table>
				<tr>
					<td><label>생년월일</label></td>
					<td><input name="birthyear" placeholder="yyyy"><input name="birthmonth" placeholder="mm"><input
						name="birthday" placeholder="dd"></td>
				</tr>
				<tr>
					<td><label>고용년월일</label></td>
					<td><input name="hireyear" placeholder="yyyy"><input name="hiremonth" placeholder="mm"><input
						name="hireday" placeholder="dd"></td>
				</tr>
				<tr>
					<td><label>퇴사년월일</label></td>
					<td><input name="outyear" placeholder="yyyy"><input name="outmonth" placeholder="mm"><input
						name="outday" placeholder="dd"></td>
				</tr>
				<tr>
					<td><label>우편번호</label></td>
					<td><input name="zip1"><input name="zip2"></td>
				</tr>
				<tr>
					<td><label>주소</label></td>
					<td colspan="3"><input name="addr1" style="width:500px"></td>
				</tr>
				<tr>
					<td><label>상세주소</label></td>
					<td colspan="3"><input name="addr2" style="width:500px"></td>
				</tr>
				<tr><td><label>결혼여부</label></td>
					<td><input name="marriage"></td>
				</tr>
			</table>
		</div>

		<input type="submit" value="저장">


	</form>
	<button id="cancel">취소</button>

</body>
</html>
<%@include file="../container/footer.jsp"%>