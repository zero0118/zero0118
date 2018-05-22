<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
/* 	 $(function(){
	 $.ajax({
	 url:"../doclist.do",
	 method:'post',
	 success:function(data){
	 console.log("mainview");
	 }
	 }); 
	 return false;
	 });
 */
	 
</script>
<div id="list"></div>
<div class="container">
	<p>
	<h2>전자결재</h2>
	<h4>결재대기문서</h4>
	<%-- <table class="table table-striped table-hover">
		<thead>
			<tr class="active">
				<th width="15%" class="gian_day">기안일</th>
				<th width="45%" class="title">제목</th>
				<th width="10%" class="yangsik">결재양식</th>
				<th width="20%" class="doc_num">문서번호</th>
				<th width="10%" class="status">결재상태</th>
			</tr>
		</thead>
	</tbody>
	</table>
	<table>
	<c:set var="list" value="${requestScope.docvo_list}"/>
		<c:forEach var="doclist" items="${list}">
		<tr>
				<td>${list.start_date}</td>
				<td>${list.doc_title}</td>
				<td>${list.doc_name}</td>
				<td>${list.doc_num}</td>
				<td>${list.doc_state}</td>
			</tr>
			<!-- 이 안에 들어간게 출력이 안됨 -->
		</c:forEach>
		</table>
	 --%>
	<c:set var="list" value="${requestScope.docvo_list}" />
	<c:set var="result" value="${requestScope.result}" />
	<%-- ${requestScope.docvo_list}
	 ${result} --%>
	 <table class="table table-border">
					<tr>
						<td>기안일</td>
						<td>문서제목</td>
						<td>문서번호</td>
						<td>문서상태</td>
						<td>문서 이름</td>
					</tr>
					
					<c:forEach var="doc" items="${list}">
					<%-- 	 <c:set var="dockind" value="${docvo_list.kindlist}" />  --%>
					<tr>
								<td>${doc.start_date}</td>
								<td>${doc.doc_title}</td>
								<td>${doc.doc_num}</td>
								<td>${doc.doc_state}</td>
							<%-- 	<td>${dockind.doc_name}</td> --%>
					</tr>
					</c:forEach>
					
				</table>
	
<!-- 	아래껀 예전꺼 -->
	<h4>기안진행문서</h4>
	<table class="table table-striped table-hover">
		<thead>
			<tr class="active">
				<th width="15%">기안일</th>
				<th width="45%">제목</th>
				<th width="10%">결재양식</th>
				<th width="20%">문서번호</th>
				<th width="10%">결재상태</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글1제목</a></td>
				<td>기안서</td>
				<td>1805-0001</td>
				<td>진행중</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글2제목</a></td>
				<td>기안서</td>
				<td>1805-0002</td>
				<td>완료</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글3제목</a></td>
				<td>외근</td>
				<td>1805-0003</td>
				<td>진행중</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글4제목</a></td>
				<td>병가</td>
				<td>1805-0004</td>
				<td>반려</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글5제목</a></td>
				<td>기안서</td>
				<td>1805-0005</td>
				<td>진행중</td>
			</tr>



		</tbody>
	</table>
	<h4>완료 문서</h4>
	<table class="table table-striped table-hover">
		<thead>
			<tr class="active">
				<th width="15%">기안일</th>
				<th width="45%">제목</th>
				<th width="10%">결재양식</th>
				<th width="20%">문서번호</th>
				<th width="10%">결재상태</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글1제목</a></td>
				<td>기안서</td>
				<td>1805-0001</td>
				<td>진행중</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글2제목</a></td>
				<td>기안서</td>
				<td>1805-0002</td>
				<td>완료</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글3제목</a></td>
				<td>외근</td>
				<td>1805-0003</td>
				<td>진행중</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글4제목</a></td>
				<td>병가</td>
				<td>1805-0004</td>
				<td>반려</td>
			</tr>
			<tr>
				<td>2018-05-11</td>
				<td><a href="#">글5제목</a></td>
				<td>기안서</td>
				<td>1805-0005</td>
				<td>진행중</td>
			</tr>



		</tbody>
	</table>


</div>
<style>
.container {
	padding-right: 350px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
}

button {
	background-color: #337ab7; /* Green */
	border: none;
	color: white;
	padding: 8px 10px;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>

<script>
	var className = 'home';
	$('div#menutab li.' + className).addClass('active');
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>