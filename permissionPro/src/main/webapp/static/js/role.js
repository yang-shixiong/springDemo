$(function () {
    //角色数据列表
    $("#dg").datagrid({
        url: "/role/list",
        columns: [[
            {field: 'number', title: '角色编号', width: 100, align: 'center'},
            {field: 'name', title: '角色名称', width: 100, align: 'center'},
        ]],
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        singleSelect: true,
        striped: true,
        toolbar: "#toolbar",
    });

    // 编辑／新增的对话框
    $('#dialog').dialog({
        width: 600,
        height: 650,
        buttons: [{
            text: "保存",
            handler: function () {
                // 判断当前操作是编辑还是新增
                let id = $("[name = 'id']").val();
                let url = id ? "/role/update" : "/role/add";
                // 提交表单
                $("#role_form").form("submit", {
                    url:url,
                    // 提交前补充参数
                    onSubmit:function (param) {
                        // 获取以选择权限
                        let rows = $('#select_permission').datagrid("getRows");
                        // 遍历所有权限，并添加到请求参数中
                        for(let i =0; i< rows.length; i++){
                            param["permissions["+i+"].id"] = rows[i].id;
                        }
                    },
                    success:function (data) {
                        data = $.parseJSON(data);
                        if (data.success){
                            $.messager.alert("提示",data.msg);
                            // 关闭对话框
                            $("#dialog").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        } else {
                            $.messager.alert("温馨提示",data.msg);
                        }
                    }
                })
            }
        }, {
            text: '关闭',
            handler: function () {
                $("#dialog").dialog("close");
            }
        }],
        closed: true,
    });

    // 权限列表需要放在下边，否则上面那个就会把样式覆盖
    // 展示是所有权限列表
    $('#all_permission').datagrid({
        title: "全部权限",
        width: 250,
        height: 400,
        fitColumns: true,
        singleSelect: true,
        url: "/permission/list",
        columns: [[
            {field: "name", title: "权限名称", width: 100, align: "center"},
        ]],
        // 点击一行时的回调
        onClickRow: function (rowIndex, rowData) {
            // 取出所有已经选择的权限
            let rows = $('#select_permission').datagrid("getRows");
            // 对每一个row进行判断
            for (let i = 0; i < rows.length; i++) {
                // 取出一行
                let row = rows[i];
                // 查看是否存在该权限
                if (rowData.id === row.id) {
                    // 在已选权限中获取该权限下标
                    let index = $("#select_permission").datagrid('"getRowIndex', row);
                    // 选中该行
                    $('#select_permission').datagrid("selectRow", index);
                    return
                }
            }
            // 没有就添加
            $('#select_permission').datagrid("appendRow", rowData);
        }
    });

    // 选中权限列表
    $("#select_permission").datagrid({
        title: "已选权限",
        width: 250,
        height: 400,
        singleSelect: true,
        fitColumns: true,
        columns: [[
            {field: 'name', title: '权限名称', width: 100, align: 'center'},
        ]],
        onClickRow: function (rowIndex, rowData) {
            // 删除当中选中的一行
            $("#select_permission").datagrid("deleteRow", rowIndex);
        }
    });

    // 添加角色
    $("#add").click(function () {
        // 清空表单
        $("#role_form").form("clear");
        // 清空已选权限
        $("#select_permission").datagrid("loadData", {rows: []});
        // 设置标题
        $("#dialog").dialog("setTitle", "添加角色");
        // 打开对话框
        $("#dialog").dialog("open");
    });

    // 修改角色
    $('#edit').click(function () {

        // 获取当前选中的数据
        let rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示", "请选择一条数据进行编辑！");
            return
        }
        // 获取当前角色的所有权限
        let options = $('#select_permission').datagrid("options");
        options.url = "/permission/show?id=" + rowData.id;

        // 重新加载数据
        $('#select_permission').datagrid("load");

        // 选中数据回显
        $('#role_form').form("load", rowData);

        // 设置标题
        $('#dialog').dialog("setTitle", "编辑角色");

        // 打开对话框
        $('#dialog').dialog("open");
    })

    // 删除角色
    $('#remove').click(function () {
        // 获取当前选中的数据
        let rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示", "请选择一条数据删除！");
            return
        }
        // get方法默认会把数据反序列化
        $.get("/role/delete?id=" + rowData.id, function (data) {
            if (data.success){
                $.messager.alert("提示",data.msg);
                // 关闭对话框
                $("#dialog").dialog("close");
                /*重新加载数据表格*/
                $("#dg").datagrid("reload");
            } else {
                $.messager.alert("温馨提示",data.msg);
            }
        })
    })
});