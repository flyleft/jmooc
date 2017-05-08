<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>课程管理</title>

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
                <li><a href="index.html"><i class="fa fa-plus"></i> 添加课程</a></li>
                <li><a href="index.html"><i class="fa fa-minus-circle"></i> 删除课程</a></li>
                <li><a href="index.html"><i class="fa fa-edit"></i> 修改课程</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;"> 章节管理</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <button class="btn btn-default btn-lg" id="add_project"><i class="fa fa-plus"></i> <span class="network-name">添加章节</span></button>
                <br><br><br>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>章节</th>
                            <th>名称</th>
                            <th>删除</th>
                            <th>课时管理</th>
                        </tr>
                        </thead>
                        <tbody>
                        #foreach($item in ${proList})
                        #liClass($velocityCount)
                        <td>$!{item.Name}</td>
                        <td>$!{item.Url}</td>
                        <td>
                            <a class="delete" role="button" href="/admin/deletePro/${item.Id}"  data-title="Delete this project!" data-confirm-button="Yes"><i class="fa fa-trash-o" name="del_project"></i></a>
                        </td>
                        <td>
                            <a href='/user/tea/'><i class="fa fa-pencil"></i></a>
                        </td>
                        </tr>
                        #end
                        </tbody>
                    </table>
                </div>

            </div>
        </div><!-- /.row -->
    </div><!-- /#page-wrapper -->

</div><!-- /#wrapper -->

<!-- JavaScript -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>