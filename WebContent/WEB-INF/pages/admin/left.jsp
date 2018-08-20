<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	pageContext.setAttribute("basePath", basePath);
%>
<section class="sidebar">
    
    <div class="user-panel">
        <div class="pull-left image">
            <img src="${basePath }/static/images/user2-160x160.jpg" class="img-circle"
                 alt="User Image">
        </div>
        <div class="pull-left info">
            <p>${staff_name }</p>
            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
    </div>
    
    <ul class="sidebar-menu">
        <li class="active treeview"><a href="#"> <i
                class="fa fa-dashboard"></i> <span>公司介绍</span> <i
                class="fa fa-angle-left pull-right"></i>
        </a>
            <ul class="treeview-menu">
                <li><a href="javascript:loadMainPage('${basePath }/Admin/webInfo.do?id=1&mId=2')"><i
                        class="fa fa-circle-o"></i> 公司概况</a></li>
                <li style="display:none"><a href="javascript:loadMainPage('${basePath }/Admin/webInfo.do?id=2&mId=3')"><i
                        class="fa fa-circle-o"></i> 核心团队</a></li>
                <li style="display:none"><a href="javascript:loadMainPage('${basePath }/Admin/webInfo.do?id=3&mId=4')"><i
                        class="fa fa-circle-o"></i> 发展历程</a></li>
                <li style="display:none"><a href="javascript:loadMainPage('${basePath }/Admin/webInfo.do?id=4&mId=5')"><i
                        class="fa fa-circle-o"></i> 资质荣誉</a></li>
            </ul>
        </li>
        <li class="treeview"><a href="#"> <i
                class="fa fa-dashboard"></i> <span>资讯管理</span> <i
                class="fa fa-angle-left pull-right"></i>
        </a>
            <ul class="treeview-menu">
                <li><a href="javascript:loadMainPage('${basePath }/Admin/webNewsList.do?mId=7')"><i
                        class="fa fa-circle-o"></i> 公司新闻</a></li>
                <li style="display:none"><a href="javascript:loadMainPage('${basePath }/Admin/webNewsList.do?mId=8')"><i
                        class="fa fa-circle-o"></i> 行业新闻</a></li>
            </ul>
        </li>
         <li class="treeview"><a href="#"> <i
                class="fa fa-dashboard"></i> <span>系统管理</span> <i
                class="fa fa-angle-left pull-right"></i>
        </a>
            <ul class="treeview-menu">
                <li><a href="javascript:loadMainPage('${basePath }/Admin/sysUserList.do')"><i
                        class="fa fa-circle-o"></i> 管理员列表</a></li>
                <li><a href="javascript:loadMainPage('${basePath }/Admin/sysRoleList.do')"><i
                        class="fa fa-circle-o"></i> 角色列表</a></li>
               
            </ul>
        </li>
    </ul>
</section>