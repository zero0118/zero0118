<%@page import="java.util.List"%>
<%@page import="com.kitware.schdule.vo.Schedule"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("utf-8");%>
{
<%List<Schedule> listSchedule = (List)request.getAttribute("schdule");
if(listSchedule != null){
%>"schedule":[
<%
int i=0;
for(Schedule sc : listSchedule){
%><%=i==0?"":","%>
{"title":"<%=sc.getSch_name()%>",
 "type":"<%=sc.getSch_type() %>",
 "startdate":"<%=sc.getSch_startdate()%>",
 "starthour":"<%=sc.getSch_starthour() %>",
 "startmin":"<%=sc.getSch_startmin() %>",
 "enddate":"<%=sc.getSch_enddate() %>",
 "endhour":"<%=sc.getSch_endhour()%>",
 "endmin":"<%=sc.getSch_endmin()%>",
 "contents":"<%=sc.getSch_contents() %>",
 "code":"<%=sc.getSch_code() %>"
}
<%
i++;
}//end for
%>
]
<%
}//end if
%>
}
