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
		<button type="button" class="btn btn-primary"
			onclick="user_add('${basePath }/Admin/sysUser.do?id=0')">添加用户</button>
		<button id="btnRefreshList" type="button"
			class="btn btn-success pull-right"
			onclick="loadMainPage('${basePath }/Admin/sysUserList.do')">刷新</button>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 600px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalTitle"></h4>
				</div>
				<div class="modal-body" id="myModalContent"
					style="height: 550px; overflow-y: auto;"></div>
			</div>
		</div>
	</div>

	<div class="box-body">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th width="80">ID</th>
					<th width="100">用户名</th>
					<th width="90">手机</th>
					<th width="150">邮箱</th>
					<th width="130">加入时间</th>
					<th width="70">角色</th>
					<th width="70">状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${sysUserList}" var="sysuser" varStatus="varSta">
					<tr>
						<td>${varSta.count }</td>
						<td>${sysuser.account }</td>
						<td>${sysuser.phone}</td>
						<td>${sysuser.email}</td>
						<td><fmt:formatDate value="${sysuser.addon }"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${sysuser.roleNames}</td>
						<td>已启用</td>
						<td class="operate"><i class="fa fa-edit"
							onclick="user_edit('${basePath }/Admin/sysUser.do?id=${sysuser.id}')"></i>
							<i class="fa fa-trash-o" onclick="user_del('${sysuser.id }')"></i>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

</div>

<script>
	function user_add(url) {
		$("#myModal").modal('show');
		$("#myModalTitle").html('添加用户');
		$("#myModalContent").load(url);
	}
	
	function user_edit(url){
		$("#myModal").modal('show');
		$("#myModalTitle").html('修改用户');
		$("#myModalContent").load(url);
	}
	
	function user_del(id){
		layer.confirm('确认要删除吗？', function (index) {
	        $.ajax({
	            type: 'POST',
	            url: sitePath+'/Admin/deleteSysUser.do?id=' + id,
	            success: function (data) {
	                layer.msg('已删除!', {
	                    icon: 1,
	                    time: 1000
	                },function(){
	                	$("#btnRefreshList").trigger('click');
	                });
	                
	            },
	            error: function (data) {
	                console.log(data);
	            },
	        });
	    });
	}
</script>

