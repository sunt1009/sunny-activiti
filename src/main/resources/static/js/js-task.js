layui.use(['form','table'],function () {
    var $ = layui.jquery,
        form = layui.form,
        table = layui.table;

    var dataTable = table.render({
        elem: '#myTaskTable',
        url: '/task/queryMyTask',
        toolbar: '#toolbarDemo',
        cols: [[
            {field: 'vacationId', title: '请假单'},
            {field: 'userId', title: '请假人'},
            {field: 'startTime', title: '请假开始时间'},
            {field: 'endTime', title: '请假结束时间'},
            {field: 'vacationContext', title: '请假原因'},
            {field: 'taskName', title: '任务名称'},
            {field: 'createTime',templet:'<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>', title: '任务创建时间'},
            {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
        ]],
        limits: [10, 15, 20, 25, 50, 100],
        limit: 10,
        page: true,
        skin: 'line'
    });


    /**
     * toolbar 头部监听事件
     */
    table.on('toolbar(currentTableFilter)', function (obj) {
        if (obj.event === 'add') {  // 监听删除操作
            var index = layer.open({
                title: '新建流程规则',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['100%', '100%'],
                content: '/flow/addFlowRule',
            });
            $(window).on("resize", function () {
                layer.full(index);
            });
        }
    });


    /**
     * 监听表格选择
     */
    table.on('tool(currentTableFilter)', function (obj) {
        var data = obj.data;
        if (obj.event === 'edit') {
            openWin('/modeler.html?modelId=' + data.id,'编辑流程',null);
        } else if (obj.event === 'copy') {  // 监听复制操作
            openWin("/model/copyModel?modelId="+ data.id,'复制流程',null);
        }else if (obj.event === 'deploy') {  // 监听部署操作
            layer.confirm('确定部署么', function (index) {
                var modelId = data.id;
                $.ajax({
                    beforeSend: function() {
                        layer.load(2);
                    },
                    type: 'GET',
                    url: '/model/deployModel',
                    data: {
                        modelId: modelId
                    },
                    dataType: 'json',
                    success: function (res) {
                        if(res.code == 200) {
                            layer.msg(res.msg, { icon: 1 ,time: 1000});
                            table.reload('currentTableId');
                        }else {
                            layer.msg(res.msg, {icon: 5, time: 2000});
                        }
                    },
                    complete: function() {
                        layer.closeAll("loading");
                    },
                    error: function() {
                        layer.msg('系统繁忙请稍后重试', {icon: 5, time: 2000});
                    }
                });
            });
        } else if (obj.event === 'delete') {
            layer.confirm('确定删除行么', function (index) {
                var modelId = data.id;
                $.ajax({
                    beforeSend: function() {
                        layer.load(2);
                    },
                    type: 'GET',
                    url: '/model/delModel',
                    data: {
                        modelId: modelId
                    },
                    dataType: 'json',
                    success: function (res) {
                        if(res.code == 200) {
                            layer.msg(res.msg, { icon: 1 ,time: 1000});
                            table.reload('currentTableId');
                        }else {
                            layer.msg(res.msg, {icon: 5, time: 2000});
                        }
                    },
                    complete: function() {
                        layer.closeAll("loading");
                    },
                    error: function() {
                        layer.msg('系统繁忙请稍后重试', {icon: 5, time: 2000});
                    }
                });
            });
        }
    });

});


function addModel() {
   openWin('/model/createModel?key=&name=name&description=description','创建流程',null);
}


/**
 * 监听打开的弹窗，关闭后刷新页面
 */
function openWin(url,text,winInfo) {
    var winObj = window.open(url, text, winInfo);
    var loop = setInterval(function () {
        if (winObj.closed) {
            clearInterval(loop);
            //alert('closed');
            parent.location.reload();
        }
    }, 1);
}