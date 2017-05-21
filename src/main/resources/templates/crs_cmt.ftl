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
    <style type="text/css">
        a:{text-decoration:none;}
        a:link,a:visited{text-decoration:none;}
        a:hover{text-decoration:none;}
        a:active{text-decoration:none;}
        .jmooc-date{color: #9d9fc4}
        .jmooc-cmt{float: right}
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
            <div class="col-md-9">
                <h1>${(crs.name)!}</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9">
                <p>${(crs.desp)!}
                </p>
                <p>
                    <a class="btn btn-warning" href="/user/all/crs/join/${(crs.id)!}">参  与</a>
                </p>
                <p>
                    <a class="btn btn-default" href="/course/${(crs.id)!}">章  节</a>
                    <a class="btn btn-default disabled" href="#">留  言</a>
                </p>


            </div>
        </div>
    </div>
</div>

<div class="container content content-light">
    <div class="row">
        <div class="col-lg-9">
            <div class="panel panel-danger">
                <#list cmt as item>
                    <div class="panel-heading">
                        <h3 class="panel-title">用户：${item.fromUserName!}</h3>
                    </div>
                    <div class="panel-body">
                        <p>
                        ${item.content!}
                        </p>
                        <small class="jmooc-date">
                            时间: ${(item.createdAt)!?string('yyyy-MM-dd')}
                        </small>
                    </div>
                </#list>
            </div>
            <form class="form-horizontal" action="/user/all/cmt/crs/add" method="post" name="cmt">
                <textarea class="form-control" rows="3" placeholder="留言" name="content"></textarea>
                <input type="hidden" name="fromInfo" value="${(crs.name)!}"/>
                <input type="hidden" name="fromInfoId" value="${(crs.id)!}"/>
                <input type="hidden" name="frontOwnerId" value="${crs.user.id!}"/>
                <input type="submit" class="btn btn-success jmooc-cmt" value="提交留言"/>
            </form>
        </div>
    </div><!-- /.row -->
</div>

<footer class="main bg-dark-img">
    <section class="copyright">
        <div class="container"> &copy; Copyright 2016
        </div>
    </section>
</footer>
<!-- JavaScript Files -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/animate.js"></script>
<script src="/js/jquery.cuteTime.min.js"></script>
<script src="/js/script.js"></script>
<!-- / JavaScript Files -->
</body>
</html>