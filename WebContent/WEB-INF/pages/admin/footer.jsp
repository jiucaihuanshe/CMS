<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<script src="${basePath}/static/plugins/jQuery/jQuery-2.2.0.min.js"></script>
<script src="${basePath}/static/plugins/jQueryUI/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button);
</script>
<script src="${basePath}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<!-- Sparkline -->
<script src="${basePath}/static/plugins/sparkline/jquery.sparkline.min.js"></script>
<!-- jvectormap -->
<script src="${basePath}/static/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="${basePath}/static/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- daterangepicker -->
<script src="${basePath}/static/plugins/daterangepicker/daterangepicker.js"></script>
<!-- datepicker -->
<script src="${basePath}/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- Slimscroll -->
<script src="${basePath}/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="${basePath}/static/plugins/fastclick/fastclick.js"></script>
<!-- DataTables -->
<script src="${basePath}/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="${basePath}/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- layer -->
<script src="${basePath}/static/plugins/layer/layer.js"></script>
<script src="${basePath }/static/plugins/webuploader/0.1.5/webuploader.min.js"></script>
<script
	src="${basePath}/static/plugins/jquery.validation/1.14.0/jquery.validate.js"></script>
<script
	src="${basePath}/static/plugins/jquery.validation/1.14.0/validate-methods.js"></script>
<script
	src="${basePath}/static/plugins/jquery.validation/1.14.0/messages_zh.js"></script>
<!-- AdminLTE App -->
<script src="${basePath}/static/plugins/adminlte/js/app.min.js"></script>
<!-- admin js -->
