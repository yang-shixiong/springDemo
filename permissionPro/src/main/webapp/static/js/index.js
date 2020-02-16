$(function () {
    $('#tabs').tabs({
        fit:true,
    });

    $('#tree').tree({
        // 发送请求获取文件
        url:"menu/tree",
        lines:true,
        // 选中之后操作
        onSelect:function (node) {
            // 首先判断这个标签是否打开，如果开开就让他选中，否则重新打开
            let exists = $('#tabs').tabs("exists", node.text);
            if(exists){
                $('#tabs').tabs("select", node.text);
            }else{
                if(node.url !== '' && node.url !== null){
                    $('#tabs').tabs("add",{
                        title:node.text,  // 设置标签名称
                        // 这里使用iframe进行引入，如果使用href只会帮我们导入body中内容
                        content:"<iframe src=" + node.url +" frameborder='0' width='100%' height='100%'></iframe>",
                        closable:true,  // 设置是否可关闭
                    })
                }
            }
        },

        // 初始加载一个标签
        onLoadSuccess:function (node, data) {
            if(data.length > 0){
                // 找到第一个元素
                let nd = $('#tree').tree('find', data[0].children[0].id);
                // 调用选中事件
                $('#tree').tree('select', nd.target)
            }
        }


    })

});