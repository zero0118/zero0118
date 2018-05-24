<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../container/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
</script>
<c:set var="pb" value="${requestScope.pagebean }"/> 
<div id="div1"></div>
<div class="container">
	<p>
	<h2>공지사항</h2>
	<table class="table table-striped table-hover table-bordered">
		<thead>
			<tr>
				<th width="10%">글 번호</th>
				<th width="55%">제목</th>
				<th width="10%">작성자</th>
				<th width="15%">작성일</th>
				<th width="10%">조회수</th>
			</tr>
		</thead>
			
			<c:set var="list" value="${pb.list}"/>
			<c:forEach var="b" items="${list}" >
			<%-- <c:forEach begin="1" end="${b.level}">▷</c:forEach> --%>
								<td>${b.start_date}</td>
								<td>${b.doc_title}</td>
								<td>${b.doc_num}</td>
							    <td>${b.doc_state}</td>
			</c:forEach>
		
		</tbody>
	</table>
	<button class="btn_write" id="writeform">글쓰기</button>
	<div class="text-center">
		<button type="button">1</button>
		<button type="button">2</button>
		<button type="button">3</button>
		<button type="button">4</button>
		<button type="button">5</button>
	</div>
</div>
<style>
.container {
	padding-right: 350px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

p {
	padding-top: 50px;
	padding-right: 30px;
	padding-bottom: 50px;
	padding-left: 80px;
}

body {
	width: 100%;
	margin: 0;
	padding: 0;
}

pd {
	padding-right: 200px;
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
$(function(){
	$('.pagination a').click(function(){
		var page;
		if($(this).text() == '«'){
			page=${pb.startPage}-1;
		}else if($(this).text() == '»'){
			page=${pb.endPage}+1;
		}else{
			page = $(this).text();
		}
		 $.ajax({
			url:"giwaitlist.do",
			method:'get',
			data:'page='+page,
			success:function(data){
				$('section').empty();
				$('section').html(data.trim());
			}
		}); 
		return false;
	});


	var className = '"authorization"';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
</script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<%@include file="../container/footer.jsp"%>