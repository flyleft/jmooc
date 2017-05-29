<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>参与课程</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="/css/per-center.css"/>
    <link rel="stylesheet" href="/css/crs_style.css"/>
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
            <a class="navbar-brand" href="/">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="/user/all/notice?r=s"><i class="fa fa-bell"></i> 消息中心</a></li>
                <li><a href="/user/stu/exe/col"><i class="fa fa-plus"></i> 习题收藏</a></li>
                <li class="active-bg"><a href="#"><i class="fa fa-edit"></i> 参加课程</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;">参与课程</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
            <#list crs as item>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">${item_index + 1}.&#8194;${item.name!}</h3>
                    </div>
                    <div class="panel-body">
                        <p>
                            老师: <b>${(item.user.name)!}</b>
                        </p>
                        <p>
                            参与: <b>${item.parNum}</b>
                        </p>
                        <p>
                            Date: <b>${(item.createdAt)?string('yyyy-MM-dd')}</b>
                        </p>
                        <p>
                            <a class="btn btn-default" href="/course/${item.id!}">继续学习...</a>
                        </p>
                    </div>
                </div>
            </#list>
            </div>
        </div><!-- /.row -->
    </div><!-- /#page-wrapper -->

</div><!-- /#wrapper -->

<!-- JavaScript -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>