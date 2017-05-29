<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>习题收藏</title>

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
            <a class="navbar-brand" href="/">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="/user/all/notice?r=s"><i class="fa fa-bell"></i> 消息中心</a></li>
                <li class="active-bg"><a href="#"><i class="fa fa-plus"></i> 习题收藏</a></li>
                <li><a href="/user/stu/crs"><i class="fa fa-edit"></i> 参加课程</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;"> 习题收藏</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-6 col-lg-offset-3">
            <#list exe as item>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title">${item_index + 1}.&#8194;${item.title!}</h3>
                    </div>
                    <div class="panel-body">
                    ${(item.content)!}
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
                        <p>
                            <a class="btn btn-default" href="/exercise/${item.id!}">详情...</a>
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