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
			onclick="role_add()">添加角色</button>
		<button id="btnRefreshList" type="button"
			class="btn btn-success pull-right"
			onclick="loadMainPage('${basePath }/Admin/sysRoleList.do')">刷新</button>
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
					<th width="100">角色名</th>
					<th width="90">权限</th>
					<th width="150">描述</th>
					<th width="130">添加时间</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${sysRoles}" var="sysrole" varStatus="varSta">
					<tr>
						<td>${varSta.count }</td>
						<td>${sysrole.name }</td>
						<td></td>
						<td></td>
						<td><fmt:formatDate value="${sysrole.addon }"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td class="operate"><i class="fa fa-edit"
							onclick="role_edit(${sysrole.id})"></i>
							<i class="fa fa-trash-o" onclick="role_del(${sysrole.id })"></i>
						</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

</div>

<script>
	function role_add() {
		$("#myModal").modal('show');
		$("#myModalTitle").html('添加角色');
		
		$("#myModalContent").load(sitePath+"/Admin/sysRole.do?id=0");
	}
	
	function role_edit(id){
		$("#myModal").modal('show');
		$("#myModalTitle").html('修改角色');
		$("#myModalContent").load(sitePath+"/Admin/sysRole.do?id="+id);
	}
	
	function role_del(id){
		layer.confirm('确认要删除吗？', function (index) {
	        $.ajax({
	            type: 'POST',
	            url: sitePath+'/Admin/deleteSysRole.do?id=' + id,
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