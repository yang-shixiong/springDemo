$(function () {
    // 员工数据列表
    $('#dg').datagrid({
        // 数据请求路径
        url: "/employee/list",
        // 加载列表
        columns: [[{field: 'username', title: '姓名', width: 100, align: 'center'},
            {field: 'hiredate', title: '入职时间', width: 100, align: 'center'},
            {field: 'phone', title: '电话', width: 100, align: 'center'},
            {field: 'email', title: '邮箱', width: 100, align: 'center'},
            // formatter 接受三个参数  value该字段的值， row该行的内容， index 下标
            {
                field: 'department', title: '部门', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                }
            },
            {
                field: 'state', title: '状态', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (value) {
                        return "在职";
                    } else {
                        return "<span style='color:red'>离职</span>"
                    }
                }
            },
            {
                field: 'admin', title: '管理员', width: 100, align: 'center', formatter: function (value, row, index) {
                    if (row.admin) {
                        return "是";
                    }else {
                        return "否";
                    }
                }
            },
        ]],
        // 是否铺满屏幕
        fit: true,
        // 数据列是否自适应
        fitColumns: true,
        // 显示行号
        rownumbers: true,
        // 是否显示翻页
        pagination: true,
        // 只允许选中一条数据
        singleSelect: true,
        // 工具栏
        toolbar:"#tb",
        onClickRow:function (rowIndex,rowData) {
            /*判断当前行是否是离职状态*/
            if(!rowData.state){
                /*离职,把离职按钮禁用*/
                $("#delete").linkbutton("disable");
            }else {
                /*离职,把离职按钮启用*/
                $("#delete").linkbutton("enable");
            }
        }

    });

    // 对话框
    $('#dialog').dialog({
        width:350,
        height:370,
        // 默认关闭状态
        closed:true,
        // 设置按钮
        buttons:[{
            text:'保存',
            handler:function () {
                // 判断当前是保存还是添加
                let id = $("[name='id']").val();
                let url;
                if(id){
                    url = '/employee/update'
                }else{
                    url = '/employee/add'
                }
                // 提交表单
                $("#employeeForm").form("submit",{
                    url:url,
                    success:function (data) {
                        data = $.parseJSON(data);
                        if(data.success){
                            $.messager.alert("提示", data.msg);
                            $("#dialog").dialog("close");
                            $("#dg").datagrid("reload")
                        }else{
                            $.messager.alert("警告", data.msg);
                        }
                    }
                })
            }
        },{
            text: '关闭',
            handler:function () {
                $('#dialog').dialog("close");
            }
        }]
    });

    // 监听添加按钮点击
    $("#add").click(function () {
        // 设置dialog的标题
        $("#dialog").dialog("setTitle", "添加员工");
        $("#password").show();
        // 清空对话框的数据
        $("#employeeForm").form("clear");
        // 添加密码验证
        $("[name='password']").validatebox({required:true});
        // 打开对话框
        $("#dialog").dialog("open")
    });

    // 监听编辑按钮点击
    $("#edit").click(function () {
        // 获取当前选中的数据
        let rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示", "请选择一条数据进行编辑！");
            return
        }
        // 取消密码验证并将密码隐藏
        $("[name='password']").validatebox({required: false});
        $("#password").hide();
        // 弹出对话框
        $("#dialog").dialog("setTitle", "编辑员工");
        $("#dialog").dialog("open");
        // 设置部门回显
        rowData["department.id"] = rowData["department"].id;
        // 回显管理员
        rowData["admin"] = rowData["admin"] + "";
        // 将数据回显
        $("#employeeForm").form("load", rowData)
    });

    /*部门选择 下拉列表*/
    $("#department").combobox({
        width:150,
        panelHeight:'auto',
        editable:false,
        url:'/department/list',
        textField:'name',
        valueField:'id',
        onLoadSuccess:function () { /*数据加载完毕之后回调*/
            $("#department").each(function(i){
                let span = $(this).siblings("span")[i];
                let targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }
    });

    /*是否为管理员选择*/
    $("#state").combobox({
        width:150,
        panelHeight:'auto',
        textField:'label',
        valueField:'value',
        editable:false,
        data:[{
            label:'是',
            value:'true'
        },{
            label:'否',
            value:'false'
        }],
        onLoadSuccess:function () { /*数据加载完毕之后回调*/
            $("#state").each(function(i){
                let span = $(this).siblings("span")[i];
                let targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder", $(this).attr("placeholder"));
                }
            });
        }

    });

    /*设置离职按钮点击*/
    $("#delete").click(function () {
        /*首先获取当前选中行*/
        let rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行编辑");
            return;
        }
        /*提醒用户,是否做离职操作*/
        $.messager.confirm("确认","是否做离职操作",function (res) {
            if(res){
                /*做离职操作*/
                $.get("/employee/state?id="+rowData.id,function (data) {
                    if (data.success){
                        $.messager.alert("温馨提示",data.msg);
                        /*重新加载数据表格*/
                        $("#dg").datagrid("reload");
                    } else {
                        $.messager.alert("温馨提示",data.msg);
                    }

                });
            }
        });
    })

    /*监听搜索按钮点击*/
    $("#searchbtn").click(function () {
        /*获取搜索的内容*/
        let keyword =  $("[name='keyword']").val();
        /*重新加载列表  把参数keyword传过去*/
        $("#dg").datagrid("load",{keyword:keyword});
    });

    /*监听刷新点击*/
    $("#reload").click(function () {
        /*清空搜索内容*/
        let keyword =  $("[name='keyword']").val('')
        /*重新加载数据*/
        $("#dg").datagrid("load",{});
    });
});