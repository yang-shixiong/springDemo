<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/2/8
  Time: 上午12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>员工</title>
    <%--导入公共包以及employee的js文件--%>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/employee.js"></script>
</head>
<body>
<%--工具栏--%>
<div id="tb">
    <shiro:hasPermission name="employee:add">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:edit">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:delete">
        <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">离职</a>
    </shiro:hasPermission>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>

    <input type="text" name="keyword" style="width: 200px; height: 30px;padding-left: 5px;">
    <a class="easyui-linkbutton" iconCls="icon-search" id="searchbtn">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" id="import">导入</a>
    <a class="easyui-linkbutton" iconCls="icon-edit" id="export">导出</a>
</div>
<%--数据表格承接--%>
<table id="dg"></table>
<%--对话框--%>
<div id="dialog">
    <form id="employeeForm">
        <%--添加一个隐藏域  编辑--%>
        <input type="hidden" name="id">
        <table align="center" style="border-spacing: 0px 10px">
            <tr>
                <td>用户名:</td>
                <td><input type="text" name="username" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr id="password">
                <td>密码:</td>
                <td><input type="text" name="password" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>手机:</td>
                <td><input type="text" name="phone" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td><input type="text" name="email" class="easyui-validatebox" data-options="required:true,validType:'email'"></td>
            </tr>
            <tr>
                <td>入职日期:</td>
                <td><input type="text" name="hiredate" class="easyui-datebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>部门:</td>
                <td><input id="department" name="department.id" placeholder="请选择部门" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>是否管理员:</td>
                <td><input id="state" name="admin" placeholder="是否为管理员" data-options="required:true"/></td>
            </tr>
            <tr>
                <td>选择角色:</td>
                <td><input id="role" name="role.id" placeholder="请选择角色"/></td>
            </tr>
        </table>
    </form>

</div>
<%--设置上传界面--%>
<div id="upload">
    <form method="post" enctype="multipart/form-data" id="employUpload">
        <table>
            <tr>
                <td>
                    <input type="file" name="excel" style="width: 180px; margin-top: 20px; margin-left: 5px;">
                </td>
            </tr>
            <tr>
                <td><a href="javascript:void(0)" id="downloadTemp" style="font-size: 14px; margin-left: 5px;">下载模版</a></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
