<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<jsp:include page="container/header.jsp"></jsp:include>
<style>
.active{
	display: block;
}
</style>
<script>
	$(function() {
		var className = '<%=request.getParameter("page")%>';
		
		if(className=='schedule'){
			location.href="${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list=개인�정";
		}
		else if(className=='authorization'){
			location.href="${pageContext.request.contextPath}/doclist.do";
		}
		else if(className=='attendance'){
			location.href="${pageContext.request.contextPath}/attendance/mymonth.jsp";
		}
		else if(className=='board'){
			location.href="${pageContext.request.contextPath}/schedule/schedulecalendar.jsp?list=개인�정";
		}
		else if(className=='home'){
			location.href="${pageContext.request.contextPath}/home/home.jsp";
		}else{
			 location.href=className+"/"+className+".jsp"; 
		}
	});
</script>
<%@include file="container/footer.jsp" %>
