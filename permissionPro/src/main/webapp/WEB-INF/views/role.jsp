<%--
  Created by IntelliJ IDEA.
  User: yangshixiong
  Date: 2020/2/8
  Time: 上午12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色</title>
    <%--导入公共包以及role的js文件--%>
    <%@include file="/static/common/common.jsp"%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/role.js"></script>
</head>
<body>
<%--工具栏--%>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="remove">删除</a>
</div>

<%--数据表格--%>
<div id="dg"></div>

<%--添加/编辑对话框 --%>
<div id="dialog">
    <form id="role_form">
        <table align="center" style="border-spacing: 20px 30px">
            <input type="hidden" name="id">
            <tr align="center">
                <td>角色编号: <input type="text" name="number" ></td>
                <td>角色名称: <input type="text" name="name"></td>
            </tr>
            <tr>
                <td><div id="all_permission"></div></td>
                <td><div id="select_permission"></div></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
