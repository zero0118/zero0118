<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset='utf-8' />
<%@include file="../container/header.jsp"%>

<style>
body {
	margin: 0;
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

.articleTop {
	padding-top: 15;
}

#calendar {
	/* max-width: 800px; */
	margin: 0 auto;
}

.articleTop>#print {
	float: right;
}

.articleTop>#print {
	float: right;
}

.articleTop>#add {
	float: right;
}
</style>
<div class="articleTop">
	<i class="fa fa-calendar-check-o" style="font-size: 25px"></i>
	<button class="btn btn-default" id="print">인쇄</button>
	<a href="scheduleadd.jsp" class="btn btn-default" data-toggle="modal"
		data-target="#myModal" id="add">일정추가</a>

	<div id="myModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- Content will be loaded here from "scheduleform1.html" file -->
			</div>
		</div>

	</div>
</div>

<hr>
<div id='calendar'></div>
<script>
	
	//개인일정,회사일정 articleTop 부분 글씨
	//인쇄버튼 클릭이벤트
	$(function(){
		<%String listText = (String) request.getParameter("list").trim();%>  <!-- 받아온 listText는 개인일정,부서일정 ~~이라는 TEXT -->
		$('div.articleTop >i').text('<%=listText%>');
		$("#print").click(function() {
			window.print();
		});
		
		
		
	});
		
	//달력 부분 function
	jQuery(document)
			.ready(
					function() {
						$('#calendar')
								.fullCalendar(
										{
											/* bootstrap4 테마사용 */
											theme : true,
											themeSystem : 'bootstrap4',											
											/* 사이즈지정 */
											height : 'parent', /* 상위 컨테이너 요소의 높이와 캘린더 전체높이를일치 */
											contentHeight : 600,
											/* 기본뷰를 month로 지정*/
											defaultView : 'month',
											/* customButton 만들기 */
											customButtons : {
												myCustomButton : {
													text : 'custom!',
													click : function() {
														var moment = $(
																'#calendar')
																.fullCalendar(
																		'getDate');
														alert("현재 날짜는"
																+ moment
																		.format('YYYY-MM-DD'));
													}

												},
												// 버튼 누르면 일정으로 추가 된다.
												addEventButton: {
											          text: 'add event...',
											          click: function() {
											            var dateStr = prompt('Enter a date in YYYY-MM-DD format');
											            var date = moment(dateStr);

											            if (date.isValid()) {
											              $('#calendar').fullCalendar('renderEvent', {
											                title: 'dynamic event',
											                start: date,
											                allDay: true
											              });
											              alert('Great. Now, update your database...');
											            } else {
											              alert('Invalid date.');
											            }
											          }
											    }
											      
											},
											/* agenda 형식의 시간 단위 조정 default값 30분  15분으로 조정. */
											slotDuration : '00:15:00',
											/* agenda 형식의 시간 포맷 지정  a(오전,오후) h(시간):mm(분) */
											slotLabelFormat : 'a h:mm ',
											/* agenda 스크롤 타임 지정 9시가 default */
											scrollTime : '09:00:00',
											/* 아이콘 지정 */
											bootstrapFontAwesome : {
												close : 'fa-times',
												prev : 'fa-chevron-left',
												prevYear : 'fa-angle-double-left',
												nextYear : 'fa-angle-double-right',
												myCustomButton : 'fas fa-smile'
											},
											/* 헤더 */
											header : {
												left : 'prevYear,prev,next,nextYear,today',
												center : 'title',
												right : 'month,agendaWeek,agendaDay,listMonth'
											},
											/* view별로 상세 조정이 가능하다. */
											views : {
												month : { // name of view
													titleFormat : 'YYYY년MM월',
													selectable : false,
													editable:false,
													displayEventEnd : true
												// other view-specific options here
												
												
												}
											},
											/* 선택이 가능하게 할 수 있다. */
											selectable : true,
											/* Agenda view에서 선택이 되도록 한다. */
											selectHelper : true,
											dayClick: function(date) {
										          alert('clicked ' + date.format());
										          console.log('dayclick');
										        },
											select: function(startDate, endDate) {
										          alert('selected ' + startDate.format() + ' to ' + endDate.format());
										          console.log("abc");
										    },

											navLinks : true, // can click day/week names to navigate views
											editable : true, //옮길 수 있느냐 없느냐.
											eventLimit : true, // allow "more" link when too many events
											googleCalendarApiKey : "AIzaSyDcnW6WejpTOCffshGDDb4neIrXVUA1EAE",
											eventSources : [ {
												googleCalendarId : "ko.south_korea#holiday@group.v.calendar.google.com",
												className : "koHolidays",
												color : "#FF0000",
												textColor : "#FFFFFF",
												editable: false,
											} ],
											  
											eventClick: function(event, jsEvent, view) {
												console.log('EVENT CLICK ' + event.title);
												//console.log(jsEvent);
												//console.log(view);
												//console.log(this);
												//return false;
											},  
											
											/* 이벤트 마우스 갖다대면 상세일정 나타내기. */
											eventRender: function(eventObj, element) {
												
												element.popover({
										          title: eventObj.title,
										          content: eventObj.description,
										          trigger: 'hover',
										          placement: 'bottom',
										          container: 'body'
										        });
										    },

										    /* 모든 일정들 */
											events: function(start, end, timezone, callback) {
												  var schTypeObj = $('div.articleTop >i').text();
												  if(schTypeObj =='개인일정'){ /* 개인일정 눌렀을 때는 개인일정만 뜰 수 있도록 설정 */
												    $.ajax({
												      url: '../schpersonal.do',
												      dataType: 'json',
												      data: {
												        start: start.unix(),
												        end: end.unix()
												      },
												      success: function(data) {
												        var events = [];
												        
												        
												        $.each(data.schedule, function(index,sc){
												        	contents = sc.contents;
											        		if (contents == "null" ){/* contents 상세일정이 null 이면 없음으로 찍히도록 설정. */
											        			contents = "없음";
											        		}
												        	
												        	if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
																events.push({
														            title: sc.title,
														            start: sc.startdate,
														            color : "#41a6f4",
														            description: "[일정상세] "+contents
													         	 });
												        	}else{
																events.push({
														            title: sc.title,
														            start: sc.startdate+'T'+sc.starthour+':'+sc.startmin,
														            end: sc.enddate+'T'+sc.endhour+':'+sc.endmin,
														            color : "#41a6f4",
														            description: "[일정상세] "+contents
													         	 });
												        	}
																
														});
												        	callback(events);
												      	
												      }
												    });
												}else if(schTypeObj =='부서일정'){
													$.ajax({
													      url: '../schdept.do',
													      dataType: 'json',
													      data: {
													        start: start.unix(),
													        end: end.unix()
													      },
													      success: function(data) {
													        var events = [];
													        
													        
													        $.each(data.schedule, function(index,sc){
													        	contents = sc.contents;
													        	if (contents == "null"){/* contents 상세일정이 null 이면 없음으로 찍히도록 설정. */
												        			contents = "없음";
												        		}
													        	if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
																	events.push({
															            title: sc.title,
															            start: sc.startdate,
															            color : "#ff9000",
															            description: "[일정상세]  "+contents
														         	 });
													        	}else{
																	events.push({
															            title: sc.title,
															            start: sc.startdate+'T'+sc.starthour+':'+sc.startmin,
															            end: sc.enddate+'T'+sc.endhour+':'+sc.endmin,
															            color : "#ff9000",
															            description: "[일정상세]  "+contents
												        			
														         	 });
													        	}
																	
															});
													        	callback(events);
													      	
													      }
													    });
												}else if(schTypeObj == '회사일정'){
													$.ajax({
													      url: '../schcompany.do',
													      dataType: 'json',
													      data: {
													        start: start.unix(),
													        end: end.unix()
													      },
													      success: function(data) {
													        var events = [];
													        
													        
													        $.each(data.schedule, function(index,sc){
													        	contents = sc.contents;
												        		if (contents == "null" ){/* contents 상세일정이 null 이면 없음으로 찍히도록 설정. */
												        			contents = "없음";
												        		}
													        	if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
																	events.push({
															            title: sc.title,
															            start: sc.startdate,
															            color : "#56d61b",
															            description: "[일정상세]  "+contents
														         	 });
													        	}else{
																	events.push({
															            title: sc.title,
															            start: sc.startdate+'T'+sc.starthour+':'+sc.startmin,
															            end: sc.enddate+'T'+sc.endhour+':'+sc.endmin,
															            color : "#56d61b",
															            description: "[일정상세]  "+contents
												        			
														         	 });
													        	}
																	
															});
													        	callback(events);
													      	
													      }
													    });
												}else if(schTypeObj == '전체일정'){
													$.ajax({
													      url: '../schtotal.do',
													      dataType: 'json',
													      data: {
													        start: start.unix(),
													        end: end.unix()
													      },
													      success: function(data) {
													        var events = [];
													        
													        
													        $.each(data.schedule, function(index,sc){
													        	contents = sc.contents;
												        		if (contents == "null" ){/* contents 상세일정이 null 이면 없음으로 찍히도록 설정. */
												        			contents = "없음";
												        		}
													        	/* switch 문은 일정마다 색을 달리하기 위함. */
													        	switch(sc.code){
													        	case '0':
													        		if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
																		events.push({
																            title: sc.title,
																            start: sc.startdate,
																            color : "#41a6f4",
																            description: "[일정상세]  "+contents
															         	 });
														        	}else{
																		events.push({
																            title: sc.title,
																            start: sc.startdate+'T'+sc.starthour+':'+sc.startmin,
																            end: sc.enddate+'T'+sc.endhour+':'+sc.endmin,
																            color : "#41a6f4",
																            description: "[일정상세]  "+contents
													        			
															         	 });
														        	}
													        		break;
													        	case '1':
													        		if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
																		events.push({
																            title: sc.title,
																            start: sc.startdate,
																            color : "#ff9000",
																            description: "[일정상세]  "+contents
															         	 });
														        	}else{
																		events.push({
																            title: sc.title,
																            start: sc.startdate+'T'+sc.starthour+':'+sc.startmin,
																            end: sc.enddate+'T'+sc.endhour+':'+sc.endmin,
																            color : "#ff9000",
																            description: "[일정상세]  "+contents
													        			
															         	 });
														        	}
													        		break;
													        	case '2' :
													        		if(sc.starthour == "00" && sc.endhour == "00"){ /* 종일 일정일 때는 시작시간과 종료시간이 "00" */
																		events.push({
																            title: sc.title,
																            start: sc.startdate,
																            color : "#56d61b",
																            description: "[일정상세]  "+contents
															         	 });
														        	}else{
																		events.push({
																            title: sc.title,
																            start: sc.startdate+'T'+sc.starthour+':'+sc.startmin,
																            end: sc.enddate+'T'+sc.endhour+':'+sc.endmin,
																            color : "#56d61b",
																            description: "[일정상세]  "+contents
													        			
															         	 });
														        	}
													        		break;
													       
													        	}
													        	
																	
															});
													        	callback(events);
													      	
													      }
													    });
												}
											 }
												
												
												/* [ {
												title : 'All Day Event',
												start : '2018-05-01',
											}, {
												title : 'Long Event',
												start : '2018-05-07',
												end : '2018-05-10'
											}, {
												id : 999,
												title : 'Repeating Event',
												start : '2018-05-09T16:00:00'
											}, {
												id : 999,
												title : 'Repeating Event',
												start : '2018-05-16T16:00:00'
											}, {
												title : 'Conference',
												start : '2018-05-11',
												end : '2018-05-13'
											}, {
												title : 'Meeting',
												start : '2018-05-12T10:30:00',
												end : '2018-05-12T12:30:00'
											}, {
												title : 'Lunch',
												start : '2018-05-12T12:00:00'
											}, {
												title : 'Meeting',
						
						
												start : '2018-05-12T14:30:00'
											}, {
												title : 'Happy Hour',
												start : '2018-05-12T17:30:00'
											}, {
												title : 'Dinner',
												start : '2018-05-12T20:00:00'
											}, {
												title : 'Birthday Party',
												start : '2018-05-13T07:00:00'
											}, {
												title : 'Click for Google',
												url : 'http://google.com/',
												start : '2018-05-28'
											} ] */
											

										});
					});
	var className = 'schedule';
	$('div#menutab li.'+className).addClass('active');
	console.log($('div#menutab li.'+className));
	$('ul#side-menu').find('li.' + className).show();
	
	
	
</script>
<%@include file="../container/footer.jsp"%>

