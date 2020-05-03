layui.use('form', function () {//密码重置传值
    var form = layui.form;
    //监听提交
    form.on('submit(formDemo)', function (data) {
        layer.msg(JSON.stringify(data.field));
        return false;
    });
    form.on('submit(sumbit)', function (data) {
        var index = layer.load(1);
        var data = data.field;
        $.ajax({
            url: "GoToReset",
            type: "post",
            data: data,
            success: function (result) {
                layer.close(index);
                if (result == 1) {
                    layer.msg("修改成功",{
                        icon: 1,
                        time: 1000,
                        end: function () {
                            Animal();
                        }
                    });
                }
                else{
                    $("#LoginName").val("");
                    $("#Phone").val("");
                    $("#LoginPassword").val("");
                    layer.msg("无此账户或信息填写错误");
                }
            }, error: function (err) {
                layer.close(index);
                $("#LoginName").val("");
                $("#Phone").val("");
                $("#LoginPassword").val("");
                layer.msg("有必要信息未填写");
            }
        })
        return false;
    });
});

var Animal = function () {
    window.location.href = "login";
}