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

<script type='text/javascript'>
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	$(document).ready(function() {
		$('#calendar').fullCalendar({
			weekends: false,
			
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,basicWeek,agendaDay,basicDay'
			},
			editable: true,
			weekMode: 'variable',
			
			viewDisplay: function(view) {
				console.log('viewDisplay');
				console.log(view.start + ' - ' + view.end);
				console.log(view.visStart + ' - ' + view.visEnd);
				//console.log(view);
				//console.log(this);
			},
			
			//loading: // see sources.html
			
			windowResize: function(view) {
				console.log('windowResize - ' + view.title);
				//console.log(this);
			},
			
			dayClick: function(dayDate, allDay, ev, view) {
				alert(dayDate);
				//console.log('dayClick - ' + dayDate + ', allDay:' + allDay + ' - ' + view.title);
				//console.log(ev);
				//console.log(this);
			},
			
			eventRender: function(event, element, view) {
				if (event.id == 888) {
					return false;
				}
				else if (event.id == 777) {
					return $("<div style='background:green'/>").text(event.title);
				}
				else if (event.id == 999) {
					element.css('border-color', 'red');
					//console.log('renderEvent (' + event.title + ') - ' + view.title);
				}
			},
			
			eventClick: function(event, jsEvent, view) {
				console.log('EVENT CLICK ' + event.title);
				//console.log(jsEvent);
				//console.log(view);
				//console.log(this);
				//return false;
			},
			
			eventMouseover: function(calEvent, domEvent) {
	var layer =	"<div id='events-layer' class='fc-transparent' style='position:absolute; width:100%; height:100%; top:-1px; text-align:right; z-index:100'></div>";
	$(this).append(layer);
}, 
 
			eventMouseout: function(calEvent, domEvent) {
}, 
			
			
			eventDragStart: function(event, jsEvent, ui, view) {
				console.log('DRAG START ' + event.title);
				//console.log(this);
			},
			eventDragStop: function(event, jsEvent, ui, view) {
				console.log('DRAG STOP ' + event.title);
				//console.log(this);
			},
			eventDrop: function(event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
				console.log('DROP ' + event.title);
				console.log(dayDelta + ' days');
				console.log(minuteDelta + ' minutes');
				console.log('allday: ' + allDay);
				/*setTimeout(function() {
					revertFunc();
				}, 2000);*/
				//console.log(jsEvent);
				//console.log(ui);
				//console.log(view.title);
				//console.log(this);
			},
			
			eventResizeStart: function(event, jsEvent, ui, view) {
				console.log('RESIZE START ' + event.title);
				//console.log(this);
			},
			eventResizeStop: function(event, jsEvent, ui, view) {
				console.log('RESIZE STOP ' + event.title);
				//console.log(this);
			},
			eventResize: function(event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view) {
				console.log('RESIZE!! ' + event.title);
				console.log(dayDelta + ' days');
				console.log(minuteDelta + ' minutes');
				/*setTimeout(function() {
					revertFunc();
				}, 2000);*/
				//console.log(jsEvent);
				//console.log(ui);
				//console.log(view.title);
				//console.log(this);
			},
			
			events: [
				{
					title: 'All Day Event',
					start: new Date(y, m, 1)
				},
				{
					title: 'Long Event',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-2)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+4, 16, 0),
					allDay: false
				},
				{
					id: 888,
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false
				},
				{
					id: 777,
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false
				},
				{
					title: 'Click for Google',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/'
				}
			]
		});
	});
</script>
</head>
<body style='font-size:12px'>
<div id='calendar' style='width:75%;margin:20px auto 0;font-family:arial'></div>
</body>
</html>
<%@include file="../container/footer.jsp"%>

