<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8" />
    <title>courses</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- CSS Files -->
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/animate.min.css"/>
    <link rel="stylesheet" href="/css/codemirror.min.css">
    <link rel="stylesheet" href="/css/theme/rubyblue.css">
    <style type="text/css">
        a:{text-decoration:none;}
        a:link,a:visited{color:#5e5e5e;text-decoration:none;}
        a:hover{color:#F97307;text-decoration:none;}
        a:active{color:#666;text-decoration:none;}
        .test p { position: absolute; left: 0; top 0; }
        .course-nav-item{display:inline-block;margin:0 4px}
        .course-nav-item a{display:block;line-height:14px;margin-bottom:10px;padding:10px;font-size:14px}
        .course-nav-item.on a{background:#F97307;color:#fff;border-radius:2px}
    </style>
</head>
<body>
<header class="main">
    <div class="container">
        <nav class="navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/"><img id="logo" src="/img/logo.png" alt="jmooc" /></a>
            </div>
            <div class="collapse navbar-collapse">
                <div class="navbar-right menu-main">
                    <ul class="nav navbar-nav">

                        <li><a href="/"><span>首页</span></a></li>
                        <li><a href="/course/list"><span>课程中心</span></a></li>
                        <li><a href="/code"><span>代码运行</span></a></li>
                        <li><a href="/exercise/list"><span>习题中心</span></a></li>
                    <#if type == 1>
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>${name!"jmooc"}<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/user/stu/crs"><i class="fa fa-user"></i> 学习课程</a></li>
                                <li><a href="/user/all/notice?r=s"><i class="fa fa-envelope"></i> 消息 <span class="badge">${num!0}</span></a></li>
                                <li><a href="/user/stu/exe/col"><i class="fa fa-gear"></i> 习题收藏</a></li>
                                <li class="divider"></li>
                                <li><a href="/user/logout"><i class="fa fa-power-off"></i> 登出</a></li>
                            </ul>
                        </li>
                    <#elseif type == 2>
                        <li class="dropdown user-dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>${name!"jmooc"}<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/user/tea/crs_mgr?do=add"><i class="fa fa-user"></i> 课程管理</a></li>
                                <li><a href="/user/all/notice?r=t"><i class="fa fa-envelope"></i> 消息 <span class="badge">${num!0}</span></a></li>
                                <li class="divider"></li>
                                <li><a href="/user/logout"><i class="fa fa-power-off"></i> 登出</a></li>
                            </ul>
                        </li>
                    <#else>
                        <li><a class="btn" href="/login">登录</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </nav>
    </div>
</header>

<div class="page-header">
    <div class="container">
        <div class="row">
            <div class="test col-md-9">
                <ul class="">
                    <li class="course-nav-item">
                        <a href="/code" >c语言</a>
                    </li>
                    <li class="course-nav-item">
                        <a href="/code?lau=cpp" >c++</a>
                    </li>
                    <li class="course-nav-item on">
                        <a href="#" >Java</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- Video list boxes: grid -->
<section class="content content-light  videos-list videos-list-grid">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <form role="form" name="code" action="/code/run" method="post">
                    <div class="form-group">
                        <label for="source">代码</label>
                        <textarea class="form-control" rows="20" name="source" id="code">
public class Main {
   public static void main(String[] args){
      System.out.println("JAVA");
   }
}
                        </textarea>
                    </div>
                    <input name="language" type="hidden" value="5"/>
                    <div class="form-group">
                        <input type="submit" class="form-control" value="提交">
                    </div>
                </form>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">运行结果:</h3>
                    </div>
                    <div class="panel-body">
                        <h4 id="re-info"></h4>
                        <p id="re-data"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<footer class="main bg-dark-img">
    <section class="copyright">
        <div class="container"> &copy; Copyright 2016
        </div>
    </section>
</footer>
<!-- JavaScript Files -->
<script src="/js/jquery.min.js"></script>
<script src="/js/codemirror.min.js"></script>
<script src="/js/clike.min.js"></script>
<script type="text/javascript">
var editor = CodeMirror.fromTextArea(document.getElementById("code"), {
    mode: 'text/x-java',//text/x-csrc text/x-c++src
    tabSize: 4,
    tabMode: 'indent',
    theme: 'rubyblue',
    lineNumbers: true,
    lineWrapping: true,
    styleActiveLine: true,
    matchBrackets: true
});
editor.setSize('auto','500px');

var soId=${so_id!-1};
var re_id=0;
var exe_num=0;
var t1;

if (soId>0){
    t1 = window.setInterval(getResult,2000);
}

function getResult(){
    if(exe_num++ > 2 || re_id !== 0){window.clearInterval(t1);return;}
    $.getJSON("/code/result?id="+soId,function(data){
        console.log(data);
        re_id=data.id;
        $("#re-info").text(data.info);
        $("#re-data").text(data.data);
    });
}
</script>
</body>
</html>