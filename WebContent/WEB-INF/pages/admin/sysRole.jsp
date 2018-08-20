<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<div class="box box-info">
	<div class="box-header with-border"></div>

	<form class="form-horizontal" id="form-role-add">
		<div class="box-body">
			<input type="hidden" name="id" id="id" value="${sysrole.id }" />

			<div class="form-group">
				<label for="account" class="col-sm-2 control-label"> 角色名： </label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="name" name="name"
						value="${sysrole.name }">
				</div>
			</div>
			
		</div>

		<div class="box-footer" style="text-align: center">
			<button id="btnSave" type="button" class="btn btn-primary">保存</button>
			<button id="btnCancel" type="button" class="btn btn-default">取消</button>
		</div>

	</form>
</div>

<script type="text/javascript">
	$(function(){
		
		$('#btnCancel').click(function(){
			$('#myModal').modal("hide");
		});
		
		$('#btnSave').click(function(){
			var name=$('#name').val();
			if(!name)
			{
				layer.msg('名称不能为空', {icon: 5});
				return;
			}
			
			var id=$('#id').val();
			$.ajax({
				type:'post',
				url:sitePath+'/Admin/doAddUpdateSysRole.do',
				data:{id:id,name:name},
				success:function(ret)
				{
					if(ret=="1")
					{
						layer.msg('保存成功', 
							{
								icon: 1,
								time:1000
							},function(){
								$('#myModal').modal("hide");
								
								setTimeout(function(){
									$('#btnRefreshList').trigger('click');
								},500);
							});
					}
				}
			});
		});
		
	})
</script>