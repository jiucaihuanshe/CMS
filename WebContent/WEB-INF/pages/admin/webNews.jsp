<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<div class="box box-info">
	<div class="box-header with-border">
		
	</div>

	<form class="form-horizontal" id="form-news-add">
		<div class="box-body">
			<input type="hidden" name="id" id="id" value="${webNews.id }" /> 
			<input type="hidden" name="mId" id="mId" value="${mId }" />
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label"> 文章标题： </label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="title" name="title"
						value="${webNews.title }">
				</div>
			</div>

			<div class="form-group">
				<label for="subTitle" class="col-sm-2 control-label"> 简略标题：
				</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="subTitle"
						name="subTitle" value="${webNews.subTitle }">
				</div>
			</div>

			<div class="form-group">
				<label for="keywords" class="col-sm-2 control-label"> 图片： </label>
				<div class="col-sm-10">
				<input type="hidden" id="fileIds" name="fileIds" />
					<jsp:include page="upload.jsp"></jsp:include>
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
				<label for="brief" class="col-sm-2 control-label"> 文章摘要： </label>
				<div class="col-sm-10">
					<textarea name="brief" id="brief" class="form-control" rows="3">${webNews.brief }</textarea>
				</div>
			</div>

			<div class="form-group">
				<label for="sortOrder" class="col-sm-2 control-label"> 排序值：
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="sortOrder"
						name="sortOrder" value="${webNews.sortOrder }">
				</div>
				<label for="author" class="col-sm-2 control-label"> 文章作者： </label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="author" name="author"
						value="${webNews.author }">
				</div>
			</div>

			<div class="form-group">
				<label for="linkUrl" class="col-sm-2 control-label"> 文章来源： </label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="linkUrl" name="linkUrl"
						value="${webNews.linkUrl }">
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
<script src="${basePath}/static/plugins/ueditor/1.4.3/ueditor.config.js"></script>
<script
	src="${basePath}/static/plugins/ueditor/1.4.3/ueditor.all.min.js"></script>
<script
	src="${basePath}/static/plugins/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script src="${basePath }/static/scripts/newsfileupload.js"></script>
<script>
$(function(){
	var uEditor = UE.getEditor('content', {
	    autoHeightEnabled : true,
	    autoFloatEnabled : true,
	    initialFrameWidth : '100%',
	    initialFrameHeight : 400
	});
	
	
	$(function() {

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
	            webnews.brief = $("#brief").val();
	            webnews.subTitle = $("#subTitle").val();
	            webnews.keywords = $("#keywords").val();
	            webnews.author = $("#author").val();
	            webnews.linkUrl = $("#linkUrl").val();
	            webnews.content = uEditor.getContent();
	            webnews.fileIds = $("#fileIds").val();

	            $.ajax({
	                type : 'post',
	                url : sitePath+'/Admin/addAndEditWebNews.do',
	                data : webnews,
	                success : function(ret) {
	                    layer.msg('保存成功', {icon: 1,time: 1000,},function(){
	                    	 $("#myModal").modal('hide');
	                    	
	                    	 setTimeout(function(){
	 	                        $("#btnRefreshList").trigger('click');
	 	                    },500);
	                    });
	                   
	                    

	                }
	            });
	        }
	    });

	});
});

</script>