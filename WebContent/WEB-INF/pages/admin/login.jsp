<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet"
	href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"
	href="${basePath}/static/plugins/adminlte/css/AdminLTE.min.css">
<link rel="stylesheet"
	href="${basePath}/static/plugins/iCheck/square/blue.css">
<title>后台登录</title>
</head>
<body class="hold-transition login-page">

	<div class="login-box">
		<div class="login-logo">
			<a href="javascript:;"><b>Admin</b>LTE</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in to start your session</p>
			<form id="login-form" method="post">
				<div class="row">
					<div class="col-xs-12">
						<div id="divErrorMsg" style="color:#ff0000;"></div>
					</div>
				</div>
				<div class="form-group has-feedback">
					<input type="text" class="form-control" name="account" id="account">
					<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" class="form-control" name="password"
						id="password"> <span
						class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<input class="form-control" type="text" name="validateCode"
							id="validateCode" placeholder="验证码"
							style="width: 150px; display: inline;"> <img
							id="validateImg" src="${basePath }/Account/validateCode.do">
						<a id="kanbuq" href="javascript:refreshValidateCode();">换一张</a>
					</div>
				</div>
				<div class="row" style="margin-top: 20px;">
					<div class="col-xs-8">
						<div class="checkbox icheck">
							<label> <input type="checkbox"> Remember Me
							</label>
						</div>
					</div>
					<div class="col-xs-4">
						<button type="button" id="btnLogin"
							class="btn btn-primary btn-block btn-flat">Sign In</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="${basePath}/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
	<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="${basePath}/static/plugins/iCheck/icheck.min.js"></script>
	<script>
		$(function() {
			$('input').iCheck({
				checkboxClass : 'icheckbox_square-blue',
				radioClass : 'iradio_square-blue',
				increaseArea : '20%' // optional
			});
		});
	</script>


	<script>
		function refreshValidateCode() {
			var imgObj = document.getElementById('validateImg');
			var src = imgObj.src;
			imgObj.src = src + "?rnd" + Math.random();
		}

		$(function() {
			$("#btnLogin").click(function() {
				var account = $("#account").val();
				var password = $("#password").val();
				var validateCode = $("#validateCode").val();

				$.ajax({
					type : 'post',
					url : '${basePath }/Account/dologin.do',
					data : {
						account : account,
						password : password,
						validateCode : validateCode
					},
					success : function(ret) {
						if (ret != 0) {
							var message = "";
							
							switch(ret)
							{
							case 1:
								message = "用户名为空";
								break;
							case 2:
								message = "密码为空";
								break;
							case 3:
								message = "用户名不正确";
								break;
							case 4:
								message = "密码不正确";
								break;
							case 5:
								message = "验证码不正确";
								break;
							}					
						
							refreshValidateCode();
							$('#divErrorMsg').html(message);
						} else {
							location.href = '${basePath }/Admin/main.do';
						}
					}

				});

			});

		});
	</script>
</body>
</html>