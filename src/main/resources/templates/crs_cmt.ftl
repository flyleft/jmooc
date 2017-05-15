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
                <h1>轻松愉快之玩转SpringData</h1>
            </div>
        </div>
        <div class="row">
            <div class="col-md-9">
                <p>简介：在企业级JavaEE应用开发中，对数据库的访问和操作是必须的。Spring Data作为SpringSource的其中一个子项目，旨在统一和简化对各类型持久化存储和访问，而不拘泥于是关系型数据库还是NoSQL数据存储，使得对数据库的访问变得方便快捷，并支持MapReduce框架及云计算服务
                </p>
                <p>
                    <a class="btn btn-warning" href="/user/all/crs/join/${id!}">参  与</a>
                </p>
                <p>
                    <a class="btn btn-default" href="/course/${id!}">章  节</a>
                    <a class="btn btn-default disabled" href="#">评  论</a>
                </p>


            </div>
        </div>
    </div>
</div>

<div class="container content content-light">
    <div class="row">
        <div class="col-lg-9">
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