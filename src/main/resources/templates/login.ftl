<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link rel="stylesheet" href="/css/login-page.min.css">
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
</head>
<body class="login-page">
<div class="login-avatar" id="login-avatar" onmouseover="this.style.cursor='pointer'" onclick="document.location='/'">
    <img src="${avatar}">
</div>
<div class="login-form">
    <div class="login-content">
        <form name="loginForm">
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </div>
                    <input type="text" class="form-control" name="username" id="username" placeholder="Username" autocomplete="off" />
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-key"></i>
                    </div>
                    <input type="password" class="form-control" name="passBefore" id="passBefore" placeholder="Password" autocomplete="off" />
                    <input type="hidden" name="password" id="password"/>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary btn-block btn-login" id="login-admin">
                    <i class="fa fa-sign-in"></i>
                    Login In
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>