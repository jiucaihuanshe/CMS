<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>

<div class="box">
	<div class="box-header">
		<input type="hidden" id="hidMId" value="${mId }">
		<div class="row">
			<div class="col-lg-3 col-xs-6">
				<label for="title" class="control-label"> 标题： </label> <input
					type="text" class="form-control" id="searchTitle">
			</div>
			<div class="col-lg-3 col-xs-6">
				<label for="title" class="control-label">关键词</label> <input
					type="text" class="form-control" id="searchKeywords">
			</div>
			<div class="col-lg-3 col-xs-6">
				<label for="title" class="control-label"> 作者： </label> <input
					type="text" class="form-control" id="searchAuthor">
			</div>
			<div class="col-lg-3 col-xs-6">
				<label for="title" class="control-label"> 内容： </label> <input
					type="text" class="form-control" id="searchContent">
			</div>
		</div>

		<div class="row" style="margin-top: 15px">
			<div class="col-lg-3 col-xs-6">
				<button type="button" class="btn btn-primary"
					onclick="article_add('${basePath }/Admin/webNews.do?id=0&mId=${mId }')">添加资讯</button>
				<button id="btnMultiDelete" type="button" class="btn btn-danger">批量删除</button>
			</div>
			<div class="col-lg-6 col-xs-12">
				<button id="btnSearch" type="button" class="btn btn-primary">查询</button>
			</div>
			<div class="col-lg-3 col-xs-6">
				<button id="btnRefreshList" type="button"
					class="btn btn-success pull-right"
					onclick="loadMainPage('${basePath }/Admin/webNewsList.do?mId=${mId }')">刷新</button>
			</div>
		</div>

	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 1100px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalTitle"></h4>
				</div>
				<div class="modal-body" id="myModalContent"
					style="height: 850px; overflow-y: auto;"></div>
			</div>
		</div>
	</div>

	<div class="box-body">
		<table id="newList" class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="2%"><input id="chkAll" type="checkbox" value=""></th>
					<th width="2%">序号</th>
					<th width="20%">标题</th>
					<th width="8%">图片</th>
					<th width="15%">关键词</th>
					<th width="10%">作者</th>
					<th width="15%">简介</th>
					<th width="10%">更新时间</th>
					<th width="5%">浏览次数</th>
					<th width="8%">发布状态</th>
					<th width="5%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${webNewsList}" var="webNews" varStatus="varSta">
					<tr>
						<td><input type="checkbox" value="${webNews.id }" name="chkWebNews"></td>
						<td>${varSta.count }</td>
						<td>${webNews.title }</td>
						<td>
						<c:if test="${webNews.files!=null && webNews.files.size()>0 }">
							<img src="${basePath }/Admin/download.do?fileId=${webNews.files[0].id}" style="width:50px;height: 50px">
						</c:if>
						</td>
						<td>${webNews.keywords }</td>
						<td>${webNews.author }</td>
						<td>${webNews.brief }</td>
						<td><fmt:formatDate value="${webNews.addon }"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${webNews.readCount }</td>
						<td>已发布</td>
						<td class="operate"><i class="fa fa-edit"
							onclick="article_edit('${webNews.id }','${mId }')"></i> <i
							class="fa fa-trash-o" onclick="article_del('${webNews.id }')"></i>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</div>
<script src="${basePath}/static/scripts/webnews.js"></script>
