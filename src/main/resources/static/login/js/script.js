document.querySelector('.img__btn').addEventListener('click', function() {
    document.querySelector('.content').classList.toggle('s--signup')
})

$("#registered").click(function () {
    var loginName = $("#RegisteredName").val();
    var LoginPassword = $("#RegisteredPassword").val();
    var Phone = $("#Phone").val();
    if(loginName ==''){
        alert("用户名未填写");
    }else if(LoginPassword == ''){
        alert("请填写密码");
    }else if(Phone == ''){
        alert("请填写手机号");
    } else{
        $.ajax({
            dataType:"json",
            url: "registered",
            type: "post",
            data:{'loginName' : loginName,'LoginPassword' : LoginPassword,'Phone' : Phone},
            success: function (result) {
                if(result==1){
                    alert("注册成功");
                }else {
                    $("#RegisteredName").val("");
                    $("#RegisteredPassword").val("");
                    $("#Phone").val("");
                    alert("注册失败，用户名已存在")
                }
            }, error: function (err) {
                $("#RegisteredName").val("");
                $("#RegisteredPassword").val("");
                $("#Phone").val("");
                alert("注册失败，用户名已存在");
            }
        })
    }
})


$("#forget").on("click", function() {
    layer.open({
        type: 2,
        title: '忘记密码',
        area: ['450px', '400px'],
        fixed: false, //不固定
        maxmin: true,
        content: '/reset'
    });
});
