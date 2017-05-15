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
                        <li><a href="about-us.htm"><span>代码运行</span></a></li>
                        <li><a href="/exercise/list"><span>习题中心</span></a></li>

                    </ul>
                    <a class="btn btn-theme navbar-btn btn-default sign-in" href="/login">登录</a>
                </div>
            </div>
        </nav>
    </div>
</header>

<div class="page-header">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <h1>${crs.name!}</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9">
                <p>${crs.desp!}
                </p>
                <p>
                    <#if join>
                        <a class="btn btn-warning disabled" href="#">已参加</a>
                        <#else>
                            <a class="btn btn-warning" href="/user/all/crs/join/${id!}">参  与</a>
                    </#if>
                </p>
                <p>
                    <a class="btn btn-default disabled" href="#">章  节</a>
                    <a class="btn btn-default" href="/course/${id!}?c=cmt">评  论</a>
                </p>


            </div>
        </div>
    </div>
</div>

<div class="container content content-light">
        <div class="row">
            <div class="col-lg-9">
                <div class="panel panel-info">
                    <#list crs.chapters as chp>
                        <div class="panel-heading">第${chp.pos}章</div>
                        <div class="table-responsive">
                            <table class="table table-hover tablesorter">
                                <thead>
                                <tr>
                                    <th>课时 <i class="fa fa-sort"></i></th>
                                    <th>名称 <i class="fa fa-sort"></i></th>
                                    <th>观看视频 <i class="fa fa-sort"></i></th>
                                    <th>课后习题 <i class="fa fa-sort"></i></th>
                                    <th>文件资料 <i class="fa fa-sort"></i></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>java基础</td>
                                    <td><a class="fa fa-play-circle" href="/video/1"></a></td>
                                    <td>0</td>
                                    <td>0</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>java多线程</td>
                                    <td><a class="fa fa-play-circle" href="/video/1"></a></td>
                                    <td>2</td>
                                    <td>2</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </#list>
                </div>
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