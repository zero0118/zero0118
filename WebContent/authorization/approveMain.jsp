<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
 	  /* $(function(){
	 $.ajax({
	 url:"../doclist.do",
	 method:'post',
	 success:function(data){
	 console.log("mainview");
	 }
	 }); 
	 return false;
	 });  */
 
	 
</script>
<div id="list"></div>
<div class="container">
	<p>
	<h2>전자결재</h2>
	<h4>결재대기문서</h4>
	
	<table class="table table-border">
	<tr>
						<td>기안일</td>
						<td>문서제목</td>
						<td>문서번호</td>
						<td>문서상태</td>
						<td>문서 이름</td>
					</tr>
	<tr>
								<td>${doc.start_date}</td>
								<td>${doc.doc_title}</td>
								<td>${doc.doc_num}</td>
								<td>${doc.doc_state}</td>
							<%-- <td>${doc.doc_kindvo.doc_name}</td> --%>
					</tr>
	</table>
	
					
	<c:set var="list" value="${requestScope.docvo_list}" />
	<c:set var="result" value="${requestScope.result}" />
	<%-- ${requestScope.docvo_list}
	 ${result} --%>
	 <h4>기안진행문서</h4>
	 <table class="table table-border">
					<tr>
						<td>기안일</td>
						<td>문서제목</td>
						<td>문서번호</td>
						<td>문서상태</td>
						<td>문서 이름</td>
					</tr>
					
					<c:forEach var="doc" items="${list}">
					<tr>
								<td>${doc.start_date}</td>
								<td>${doc.doc_title}</td>
								<td>${doc.doc_num}</td>
								<td>${doc.doc_state}</td>
								<%-- </tr>
								<c:forEach var="doc2" items ="${doc.doc_kindvo}">
								
								<td>${doc2.doc_name}</td>
								
								</c:forEach> --%>
					</tr>
				</c:forEach>
				</table>
	
<!-- 	아래껀 예전꺼 -->
	
	
	<h4>완료 문서</h4>
	<c:set var="list2" value="${requestScope.docvo_list2}" />
		 <table class="table table-border">
					<tr>
						<td>기안일</td>
						<td>문서제목</td>
						<td>문서번호</td>
						<td>문서상태</td>
						<td>문서 이름</td>
					</tr>
					
					<c:forEach var="doc2" items="${list2}">
					<%--  	 <c:set var="dockind" value="${requestScope.docvo_list.kindlist}"/> --%>
					<tr>
								<td>${doc2.start_date}</td>
								<td>${doc2.doc_title}</td>
								<td>${doc2.doc_num}</td>
							    <td>${doc2.doc_state}</td>
								<%-- <td>${dockind.doc_name}</td> --%>
					</tr>
					</c:forEach>
					
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
	var className = 'authorization';
	$('div#menutab li.' + className).addClass('active');
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>