<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>后台管理</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${basePath}/static/plugins/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/adminlte/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/adminlte/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/iCheck/flat/blue.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/daterangepicker/daterangepicker-bs3.css">
  <link rel="stylesheet" href="${basePath}/static/plugins/datatables/dataTables.bootstrap.css">
  <link rel="stylesheet" href="${basePath }/static/plugins/webuploader/0.1.5/webuploader.css">
  <link rel="stylesheet" href="${basePath}/static/css/admin.css">
<script>
	var sitePath = '${basePath }';
</script>
