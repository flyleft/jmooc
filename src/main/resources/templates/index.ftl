<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8" />
    <title>首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- CSS Files -->
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/style.css"/>
    <link rel="stylesheet" href="/css/animate.min.css"/>

</head>
<body>
<header class="main bg-dark-img home-1">
    <div class="container">
        <nav class="navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"><img id="logo" src="../src/main/resources/static/img/logo.png" th:src="@{/img/logo.png}" alt="eLearn" /></a>
            </div>
            <div class="collapse navbar-collapse">
                <div class="navbar-right menu-main">
                    <ul class="nav navbar-nav">

                        <li><a href="/"><span>首页</span></a></li>
                        <!--<li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span>课程中心</span> <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/courses">信息</a></li>
                                <li><a href="/courses">电气</a></li>
                                <li><a href="/courses">土木</a></li>
                                <li><a href="/courses">政治</a></li>
                            </ul>
                        </li>-->
                        <li><a href="/courses"><span>课程中心</span></a></li>
                        <li><a href="about-us.htm"><span>代码运行</span></a></li>


                        <li><a href="features.htm"><span>习题中心</span></a></li>

                        <li><a href="contact.htm"><span>联系人</span></a></li>
                    </ul>
                    <a class="btn btn-theme navbar-btn btn-default sign-in" href="#">登录</a>
                </div>
            </div>
        </nav>

        <p class="header text-center text-white">在线学习，与老师在线交流，c/c++/java代码在线运行</p>

        <!-- Screens -->
        <div class="screens animation-domready">
            <p>
                <img src="/img/home1_tab3.png" data-animation-delay="0.5s" data-animation="fadeInUp" class="animated" alt="" />
                <img src="/img/home1_tab2.png" data-animation-delay="1.5s" data-animation="fadeInUp" class="animated" alt="" />
                <img src="/img/home1_tab1.png" data-animation-delay="2.5s" data-animation="fadeInUp" class="animated" alt="" />
            </p>
        </div>
    </div>
</header>

<div class="container content content-light home-1">
    <section class="row animation-scroll">
        <figure class="col-md-6 animated" data-animation="bounceInLeft"><img src="/img/home1_img1.png" alt="" /></figure>
        <article class="col-md-6 animated" data-animation="bounceInRight">
            <h3><strong>远程学习</strong></h3>
            <p>开设网络第二课堂，随时随地学习；不懂，在线向老师咨询；学习检测，课后习题等你来答</p>
        </article>
    </section>
    <section class="row animation-scroll">
        <article class="col-md-6 animated" data-animation="bounceInLeft">
            <h3><strong>手机学习</strong></h3>
            <p>不必需要电脑，针对移动端进行适配，手机也可观看学习视频</p>
        </article>
        <figure class="col-md-6 animated" data-animation="bounceInRight"><img src="/img/home1_img2.png" alt="" /></figure>
    </section>
    <section class="row animation-scroll">
        <figure class="col-md-6 animated" data-animation="bounceInLeft"><img src="/img/home1_img3.png" alt="" /></figure>
        <article class="col-md-6 animated" data-animation="bounceInRight">
            <h3><strong>海量学习资源</strong></h3>
            <p>不仅仅只有学习视频，还有海量的学习文件可以下载</p>
        </article>
    </section>
</div>

<section class="content content-dark testimonial">
    <div class="container">
        <p class="header text-center text-white">我们已经有 <strong>31</strong> 堂课</p>
        <a href="plans.htm" class="btn btn-theme btn-green">开始学习</a>
    </div>
</section>

<section class="content content-light">
    <div class="container">
        <p class="header text-center"><strong>与教务网无缝对接</strong></p>
        <p class="text-center">老师可以自定义课堂，课后题所占分数。课堂观看程度，习题完成的程度都有可能影响最终成绩</p>

        <hr class="invisible" />

        <div class="row">
            <div class="col-md-6">
                <h3>个人学习中心</h3>
                <ul class="fa-ul list-special">
                    <li><i class="fa-li fa fa-2x fa-check text-green"></i>习题收藏</li>
                    <li><i class="fa-li fa fa-2x fa-check text-green"></i>错题回顾</li>
                    <li><i class="fa-li fa fa-2x fa-check text-green"></i>选择网络课堂</li>
                    <li><i class="fa-li fa fa-2x fa-check text-green"></i>与课堂老师在线交流</li>
                </ul>
            </div>
            <div class="col-md-6">
                <h3>观看视频，制作自己的课堂</h3>
                <a class="video-prev video-play-here" style="height:240px;"></a>
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
<script src="/js/bootstrap.min.js"></script>
<script src="/js/animate.js"></script>
<script src="/js/jquery.cuteTime.min.js"></script>
<script src="/js/script.js"></script>
<!-- / JavaScript Files -->
</body>
</html>