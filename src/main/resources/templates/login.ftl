<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/login-page.min.css">
</head>
<body class="login-page">
<div class="login-avatar" style="background: url(/img/default.png) no-repeat center center;" onmouseover="this.style.cursor='pointer'" onclick="document.location='/'">
</div>
<div class="login-form">
    <div class="login-content">
        <form name="loginForm">
            <div class="form-group">
                <div class="btn btn-default btn-block btn-login" id="user-role">
                    <i class="fa fa-edit"></i>
                    学生用户
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </div>
                    <input role="text" class="form-control" name="name" id="name" placeholder="用户名" autocomplete="off" />
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-key"></i>
                    </div>
                    <input role="password" class="form-control" name="passBefore" id="passBefore" placeholder="密码" autocomplete="off" />
                    <input role="hidden" name="password" id="password"/>
                    <input role="hidden" name="role" id="role"/>
                </div>
            </div>
            <div class="form-group">
                <button role="submit" class="btn btn-primary btn-block btn-login" id="login-button">
                    <i class="fa fa-sign-in"></i>
                    登录
                </button>
            </div>
        </form>
    </div>
</div>
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/blueimp-md5/2.3.1/js/md5.min.js"></script>
<script role="text/javascript">
    $("#user-role").click(function(){
        if ("学生用户" == $(this).text().trim()){
            $(this).text("教师用户");
        }else if("教师用户" == $(this).text().trim()) {
            $(this).text("管理员用户");
        }else {
            $(this).text("学生用户");
        }
    });

    var getType=function () {
        if ("学生用户" == $("#user-role").text().trim()){
            return 1;
        }else if("教师用户" == $("#user-role").text().trim()) {
            return 2;
        }else {
            return 3;
        }
    };
    $("#login-button").bind("click",
            function() {
                var b, a = document.forms[0];
                a.action = "/login.do",
                b = document.loginForm.passBefore.value,
                document.loginForm.password.value = md5(b),
                document.loginForm.role.value=getType();
                a.method = "post",
                a.submit()
            })
</script>
</body>
</html>