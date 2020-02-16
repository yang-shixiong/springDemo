$(function () {
    // 数据表格
    $('#dg').datagrid({
        url: "menu/list",
        columns: [[
            {field: "text", title: "名称", width: 100, align: "center"},
            {field: "url", title: "跳转地址", width: 100, align: "center"},
            {
                field: "parent", title: "父菜单", width: 100, align: "center", formatter: function (value, row, index) {
                    return value ? value.text : "";
                }
            },
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: "#tb",
    });

    // 初始化增加／编辑对话框
    $('#dialog').dialog({
        width: 300,
        height: 240,
        closed: true,
        buttons: "#menu_dialog_bt",
    });

    // 加载父级菜单
    $('#parentMenu').combobox({
        width: 150,
        panelHeight: "auto",
        editable: false,
        url: "menu/parent/list",
        textField: "text",  // 显示内容
        valueField: "id",  // 传递内容
        onLoadSuccess: function () {
            /*数据加载完成之后的回调*/
            $('#parentMenu').each(function (i) {
                let span = $(this).siblings("span")[i];
                let targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"))
                }
            })
        }

    });
    /*添加功能*/
    $('#add').click(function () {
        $('#dialog').dialog("setTitle", "增加菜单");
        $('#menuForm').form("clear");
        $('#dialog').dialog("open")
    });

    /*编辑功能*/
    $('#edit').click(function () {
        // 清空表格内容
        $('#menuForm').form("clear");
        let rowData = $("#dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示", "至少需要选择一条数据");
            return false;
        } else {
            /*回显的placeholder*/
            $("#parentMenu").each(function (i) {
                let span = $(this).siblings("span")[i];
                let targetInput = $(span).find("input:first");
                if (targetInput) {
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
        // 设置菜单回显
        if (rowData.parent) {
            rowData["parent.id"] = rowData.parent.id;
        }
        $("#dialog").dialog("setTitle", "编辑菜单");
        $('#dialog').dialog("open");
        $('#menuForm').form("load", rowData)
    })

    /*点击保存按钮*/
    $('#save').click(function () {
        let id = $("[name='id']").val();
        let url;
        if (id) {
            // 判断父级菜单是否等于本季点
            let parentId = $("[name='parent.id']").val();
            if (parentId === id) {
                $.messager.alert("温馨提示", "父菜单是自身！")
                return false;
            }
            url = "menu/edit"
        } else {
            url = "menu/add"
        }
        // 提交表单
        $('#menuForm').form("submit", {
            url: url,
            success: function (data) {
                data = $.parseJSON(data);
                if (data.success) {
                    $.messager.alert("温馨提示", data.msg);
                    /*关闭对话框 */
                    $("#dialog").dialog("close");
                    $("#parentMenu").combobox("reload");
                    $("#dg").datagrid("reload");
                } else {
                    $.messager.alert("温馨提示", data.msg);
                }
            }
        })
    });

    /*取消操作*/
    $('#cancel').click(function () {
        $('#dialog').dialog("close");
    })

    /*删除操作*/
    $('#delete').click(function () {
        // 清空表格内容
        let rowData = $("#dg").datagrid("getSelected");
        if (!rowData) {
            $.messager.alert("温馨提示", "至少需要选择一条数据");
            return false;
        }
        // 提醒用户,是否做删除操作
        $.messager.confirm("确认","是否做删除操作",function (res) {
            if(res){
                /*做离职操作*/
                $.get("/menu/delete?id="+rowData.id,function (data) {
                    if (data.success){
                        $.messager.alert("温馨提示",data.msg);
                        /*重新加载下拉列表数据*/
                        $("#parentMenu").combobox("reload");
                        /*重新加载数据表格*/
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示",data.msg);
                    }

                });
            }
        });
    })

});