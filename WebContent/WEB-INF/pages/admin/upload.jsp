<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);	
%>

<div id="uploader" class="wu-example">
    <div id="fileList" class="uploader-list"></div>
    <div class="btns">
        <div id="filePicker">选择文件</div>
        <button type="button" id="ctlBtn" class="btn btn-primary" style="margin-left:10px">开始上传</button>
    </div>
</div>

