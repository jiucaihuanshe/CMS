<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<div class="box box-info">
	<div class="box-header with-border">
		<h3 class="box-title">编辑内容</h3>
	</div>

	<form class="form-horizontal" id="form-news-add">
		<div class="box-body">
			<input type="hidden" name="id" id="id" value="${webNews.id }" /> 
			<input type="hidden" name="mId" id="mId" value="${mId }" />
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label"> 标题： </label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title"
						value="${webNews.title }">
				</div>
			</div>

			<div class="form-group">
				<label for="keywords" class="col-sm-2 control-label"> 关键词： </label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="keywords"
						name="keywords" value="${webNews.keywords }">
				</div>
			</div>

			<div class="form-group">
				<label for="content" class="col-sm-2 control-label"> 文章内容： </label>
				<div class="col-sm-10">
					<textarea name="content" id="content">${webNews.content }</textarea>
				</div>
			</div>

		</div>
		
		<div class="box-footer" style="text-align: center">
			<button type="submit" class="btn btn-primary">保存</button>
			<button type="reset" class="btn btn-default">清空</button>
		</div>

	</form>
</div>

<script type="text/javascript">
	window.UEDITOR_HOME_URL = "${basePath}/static/plugins/ueditor/1.4.3/";
</script>
<script
	src="${basePath}/static/plugins/jquery.validation/1.14.0/jquery.validate.js"></script>
<script
	src="${basePath}/static/plugins/jquery.validation/1.14.0/validate-methods.js"></script>
<script
	src="${basePath}/static/plugins/jquery.validation/1.14.0/messages_zh.js"></script>
<script src="${basePath}/static/plugins/ueditor/1.4.3/ueditor.config.js"></script>
<script
	src="${basePath}/static/plugins/ueditor/1.4.3/ueditor.all.min.js"></script>
<script
	src="${basePath}/static/plugins/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	$(function() {

		var uEditor = UE.getEditor('content', {
			autoHeightEnabled : true,
			autoFloatEnabled : true,
			initialFrameWidth : '100%',
			initialFrameHeight : 400
		});

		//表单验证
		$("#form-news-add").validate({
			rules : {
				title : {
					required : true,
				},
			},
			onkeyup : false,
			focusCleanup : true,
			success : "valid",
			submitHandler : function(form) {
				var webnews = {};
				webnews.id = $("#id").val();
				webnews.menuId = $("#mId").val();
				webnews.title = $("#title").val();
				webnews.keywords = $("#keywords").val();
				webnews.content = uEditor.getContent();
				webnews.fileIds = $("#hidUploadFileIds").val();

				$.ajax({
					type : 'post',
					url : '${basePath }/Admin/addAndEditWebNews.do',
					data : webnews,
					success : function(ret) {
						 layer.msg('保存成功', {icon: 1,time: 1000,});
					}
				});
			}
		});

	});
</script>
</body>
</html>