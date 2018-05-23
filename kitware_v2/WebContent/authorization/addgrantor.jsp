<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close" aria-hidden="true">x</button>
	<h5 class="smaller lighter blue no-margin modal-title">결재자 선택</h5>
</div>
<div class="modal-body">
	<div class="easy-tree">
		<ul>
			<li>인사부
				<ul>
					<li>팀장
						<ul>
							<li>타노스</li>
							<li>가모라</li>
						</ul>
					</li>
					<li>과장
						<ul>
							<li>울트론</li>
							<li>네뷸라</li>
						</ul>
					</li>
					<li>대리
						<ul>
							<li>김성길</li>
							<li>드랙스</li>
						</ul>
					</li>
					<li>사원
						<ul>
							<li>너구리</li>
							<li>그루트</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>기획부
				<ul>
					<li>팀장
						<ul>
							<li>타노스</li>
							<li>가모라</li>
						</ul>
					</li>
					<li>과장
						<ul>
							<li>울트론</li>
							<li>네뷸라</li>
						</ul>
					</li>
					<li>대리
						<ul>
							<li>김성길</li>
							<li>드랙스</li>
						</ul>
					</li>
					<li>사원
						<ul>
							<li>너구리</li>
							<li>그루트</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>개발부
				<ul>
					<li>팀장
						<ul>
							<li>타노스</li>
							<li>가모라</li>
						</ul>
					</li>
					<li>과장
						<ul>
							<li>울트론</li>
							<li>네뷸라</li>
						</ul>
					</li>
					<li>대리
						<ul>
							<li>김성길</li>
							<li>드랙스</li>
						</ul>
					</li>
					<li>사원
						<ul>
							<li>너구리</li>
							<li>그루트</li>
						</ul>
					</li>
				</ul>
			</li>
			<li>경영부
				<ul>
					<li>팀장
						<ul>
							<li>타노스</li>
							<li>가모라</li>
						</ul>
					</li>
					<li>과장
						<ul>
							<li>울트론</li>
							<li>네뷸라</li>
						</ul>
					</li>
					<li>대리
						<ul>
							<li>김성길</li>
							<li>드랙스</li>
						</ul>
					</li>
					<li>사원
						<ul>
							<li>너구리</li>
							<li>그루트</li>
						</ul>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
<div class="modal-footer">
	<button class="btn btn-sm btn-deafult pull-right" data-dismiss="modal" id="btnClose">
		<i class="ace-icon fa fa-times"></i>닫기
	</button>
	<button class="btn btn-sm btn-default pull-right" data-dismiss="modal" id="btnChoice">
		<i class="ace-icon fa fa-check"></i>선택
	</button>
</div>



<link rel="stylesheet" href="../css/easyTree/easyTree.css">
<script src="../js/easyTree/easyTree.js"></script>
<script>
	$(function() {
		$('.easy-tree').EasyTree({
		});
		
		$('#btnChoice').click(function(){
			var selected = getSelectedItems();
		});
	});
</script>
<style>
.easy-tree {
	min-height: 20px;
	margin-bottom: 20px;
	background-color: #337ab7;
	color: #fff;
	border: none;
	border-top: none;
	padding-bottom: 15px;
}

.easy-tree li>span>a {
	color: #fff;
	text-decoration: none;
}

.easy-tree li>span>a:hover {
	color: #8A4500;
	text-decoration: none;
	background: #FFCB97;
}

.easy-tree li.parent_li>span:hover, .easy-tree li.parent_li>span:hover+ul li span
	{
	background: #004040;
	color: #8A4500;
}
/* 인사부,개발부 ,경영부 , 기획부  아래 다 display:none  */
.easy-tree>ul>li ul {
	display: none;
}
</style>