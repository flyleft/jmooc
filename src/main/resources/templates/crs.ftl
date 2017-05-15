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
                        <li><a href="about-us.htm"><span>代码运行</span></a></li>
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
                <p>方向：</p>
                <ul class="">
                    <li class="course-nav-item on">
                        <a href="/course/list">全部</a>
                    </li>
                    <li class="course-nav-item">
                        <a href="/course/list?c=fe" >前端开发</a>
                    </li>
                    <li class="course-nav-item">
                        <a href="/course/list?c=be" >后端开发</a>
                    </li>
                    <li class="course-nav-item">
                        <a href="/course/list?c=mb" >移动开发</a>
                    </li>
                    <li class="course-nav-item">
                        <a href="/course/list?c=db" >数据库</a>
                    </li>
                </ul>
               </div>
            </div>
                <div class="row">
                <div class="test col-md-9">
                    <p>类型：</p>
                    <ul class="">
                        <li class="course-nav-item on">
                            <a href="/course/list">全部</a>
                        </li>
                        <li class="course-nav-item">
                            <a href="/course/list?c=c" >C</a>
                        </li>
                        <li class="course-nav-item">
                            <a href="/course/list?c=cp" >C++</a>
                        </li>
                        <li class="course-nav-item">
                            <a href="/course/list?c=java" >JAVA</a>
                        </li>
                    </ul>
                 </div>
                </div>

        </div>
</div>

<!-- Video list boxes: grid -->
<section class="content content-light  videos-list videos-list-grid">
    <div class="container">
       <#-- <div class="filter">
            <a href="#" class="btn btn-theme navbar-btn btn-orange">最热门</a>
            <a href="#" class="btn btn-theme navbar-btn btn-lightblue">最新更新</a>
        </div>

        <hr class="invisible" />-->

        <div class="row">
           <#list crs as item>
            <article class="col-md-4 video-item">
                <a href="/course/${item.id!}" class="video-prev video-prev-small"></a>
                <h3 class="video-title"><a href="video.htm">${item.name!}</a></h3>
                <div class="row video-params">
                    <div class="col-md-7">
                        老师: <b>${(item.user.name)!}</b>
                    </div>
                    <div class="col-md-5 text-right">
                        参与: <b>${item.parNum}</b>
                        <a href="/user/all/crs/join/${item.id!}" class="participate"  data-text="确定参与本课程?" data-confirm-button="是" data-cancel-button="否">
                            <i class="fa fa-heart"></i>
                        </a>
                    </div>
                </div>
                <div class="row video-params">
                    <div class="col-md-12">
                        Date: <b>${(item.createdAt)?string('yyyy-MM-dd')}</b>
                    </div>
                </div>
            </article>
           </#list>
        </div>

        <!-- Pagination -->
        <ul class="pagination">
            <#if (cur > 1)>
                  <li><a href="/course/list?c=${c!"all"}&page=${cur - 1}"><i class="fa fa-angle-left"></i></a></li>
               <#else>
                   <li class="disabled"><a href="/course/list&c=${c!"all"}?page=${cur - 1}"><i class="fa fa-angle-left"></i></a></li>
            </#if>
           <#list 1..count as t>
                <#if cur == t>
                        <li class="active"><a href="/course/list?c=${c!"all"}&page=${t - 1}">${t}</a></li>
                    <#else>
                        <li><a href="/course/list?c=${c!"all"}&page=${t - 1}"></a></li>
                </#if>
           </#list>
            <#if (cur < count)>
                <li><a href="/course/list?c=${c!"all"}&page=${cur + 1}"><i class="fa fa-angle-right"></i></a></li>
            <#else>
                <li class="disabled"><a href="/course/list?c=${c!"all"}&page=${cur + 1}"><i class="fa fa-angle-right"></i></a></li>
            </#if>
        </ul>
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
<script src="/js/bootstrap.min.js"></script>
<script src="/js/animate.js"></script>
<script src="/js/jquery.cuteTime.min.js"></script>
<script src="/js/jquery.confirm.min.js"></script>
<script src="/js/script.js"></script>
<script type="text/javascript">
    $(".participate").confirm();
</script>
<!-- / JavaScript Files -->
</body>
</html>