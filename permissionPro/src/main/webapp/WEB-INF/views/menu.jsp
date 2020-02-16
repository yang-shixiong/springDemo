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
    <title>菜单</title>
    <%--导入公共包以及menu的js文件--%>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/menu.js"></script>
</head>
<body>
<%--工具栏--%>
<div id="tb">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" id="add">添加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" id="edit">编辑</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" id="delete">删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" id="reload">刷新</a>
</div>
<%--数据表格承接--%>
<table id="dg"></table>
<%--对话框--%>
<div id="dialog">
    <form id="menuForm" method="post">
        <%--添加一个隐藏域  编辑--%>
        <input type="hidden" name="id">
        <table align="center" style="border-spacing: 0px 10px">
            <tr>
                <td>名称:</td>
                <td><input type="text" name="text" class="easyui-validatebox" data-options="required:true"></td>
            </tr>
            <tr>
                <td>跳转地址:</td>
                <td><input type="text" name="url" class="easyui-validatebox"></td>
            </tr>
            <tr>
                <td>父菜单:</td>
                <td><input id="parentMenu" name="parent.id" class="easyui-combobox" placeholder="请选择父菜单"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- 对话框保存取消按钮 -->
<div id="menu_dialog_bt">
    <a id="save" class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a id="cancel" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
