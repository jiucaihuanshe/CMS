<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="box box-info">
	<div class="box-header with-border">
		
	</div>

	<form class="form-horizontal" id="form-show">
		<div class="box-body">
			
			<div class="form-group">
				<label for="title" class="col-sm-2 control-label"> 用户名： </label>
				<div class="col-sm-10">
					${user.account }
				</div>
			</div>

			<div class="form-group">
				<label for="keywords" class="col-sm-2 control-label"> 邮箱： </label>
				<div class="col-sm-10">
					${user.email }
				</div>
			</div>
			
			<div class="form-group">
				<label for="keywords" class="col-sm-2 control-label"> 电话： </label>
				<div class="col-sm-10">
					${user.phone }
				</div>
			</div>
			
		</div>
	</form>
</div>
