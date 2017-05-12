<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>习题管理</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/per-center.css"/>
</head>

<body>

<div id="wrapper">

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top navbar-bg" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">个人中心</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="charts.html"><i class="fa fa-bell"></i> 消息中心</a></li>
                <li><a href="index.html"><i class="fa fa-info-circle"></i> 个人信息</a></li>
                <li><a href="/user/tea/crs_mgr?do=add"><i class="fa fa-plus"></i> 添加课程</a></li>
                <li><a href="/user/tea/crs_mgr?do=mod"><i class="fa fa-edit"></i> 课程管理</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;"> 习题管理</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <button class="btn btn-default btn-lg" id="post_chp"><i class="fa fa-plus"></i> <span class="network-name">添加习题</span></button>
                <br><br><br>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">

                   <#list exe as item>
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                           <h3 class="panel-title">${item.title}</h3>
                       </div>
                       <div class="panel-body">
                           <div class="form-group">
                               <#list item.chooseList!?keys as key>
                                   <div class="radio">
                                       <label>
                                           <input type="radio" name="optionsRadios">
                                       ${key}:${item.chooseList[key]}
                                       </label>
                                   </div>
                               </#list>
                           </div>
                           <hr/>
                           <h3 class="panel-title">难度系数：${item.difficulty!}</h3>
                           <hr/>
                           <h3 class="panel-title">分值：${item.score!}</h3>
                           <hr/>
                           <h3 class="panel-title">类型：${item.type!}</h3>
                           <hr/>
                           <h3 class="panel-title">正解：${item.answer!}</h3>
                           <hr/>
                           <h3 class="panel-title">解析：</h3>
                           <br>
                           <p>${item.analysis!}</p>
                       </div>
                   </div>
                   </#list>
                </div>
            </div>
        </div><!-- /.row -->
    </div><!-- /#page-wrapper -->

</div><!-- /#wrapper -->

<!-- JavaScript -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
</body>
</html>