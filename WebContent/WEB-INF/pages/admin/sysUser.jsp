<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<div class="box box-info">
	<div class="box-header with-border"></div>

	<form class="form-horizontal" id="form-account-add">
		<div class="box-body">
			<input type="hidden" name="id" id="id" value="${sysUser.id }" />
			<input type="hidden" id="hidExistedRoles" value="${existedRoleIds }">
			<div class="form-group">
				<label for="account" class="col-sm-3 control-label"> 用户名： </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="account" name="account"
						value="${sysUser.account }">
				</div>
			</div>

			<c:if test="${sysUser.id==0 }">
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label"> 密码： </label>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="password"
						name="password">
				</div>
			</div>

			<div class="form-group">
				<label for="repassword" class="col-sm-3 control-label">
					确认密码： </label>
				<div class="col-sm-9">
					<input type="password" class="form-control" id="repassword"
						name="repassword">
				</div>
			</div>
			</c:if>
			<div class="form-group">
				<label for="phone" class="col-sm-3 control-label"> 手机号： </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="phone" name="phone"
						value="${sysUser.phone }">
				</div>
			</div>

			<div class="form-group">
				<label for="email" class="col-sm-3 control-label"> 邮箱： </label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="email" name="email"
						value="${sysUser.email }">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">角色</label>
				<div class="col-sm-9">
					<c:forEach items="${roles}" var="role">
						<label style="margin-right:10px;"><input type="checkbox" name="roleIds" value="${role.id }">${role.name }</label>
					</c:forEach>
				</div>
			</div>
		</div>

		<div class="box-footer" style="text-align: center">
			<button type="submit" class="btn btn-primary">保存</button>
			<button id="btnCancel" type="button" class="btn btn-default">取消</button>
		</div>

	</form>
</div>

<script type="text/javascript">
	$(function() {
		checkedRoles();
		
		function checkedRoles()
		{
			var existedRoleIds=$('#hidExistedRoles').val();
			if(!existedRoleIds)
			{
				return;
			}
			
			var roleIds=existedRoleIds.split(",");
			for(var i=0,len=roleIds.length;i<len;i++)
			{
				var roleId=roleIds[i];
				$('input[name=roleIds][value="'+roleId+'"]').prop('checked',true);
			}
		}
		
		$('#btnCancel').click(function(){
			$("#myModal").modal('hide'); 
		});
		
		$("#form-account-add").validate({
			rules : {
				account : {
					required : true,
				},
				password : {
					required : true,
				},
				repassword : {
					equalTo : "#password",
				},
				phone : {
					required : true,
				},
				email : {
					required : true,
				},
				roleIds:{
					required : true,
				}
			},
			onkeyup : false,
			focusCleanup : true,
			success : "valid",
			submitHandler : function(form) {
							
				$.ajax({
					type : 'post',
					url : '${basePath }/Admin/addOrUpdateUser.do',
					data : $(form).serialize(),
					success : function(ret) {
						layer.msg('保存成功', {
							icon : 1,
							time : 1000,
						},function(){
							$("#myModal").modal('hide'); 
							
							setTimeout(function(){							
								$("#btnRefreshList").trigger('click');	
							},500);
						});							
						
					}
				});
			}
		});

	})
</script>