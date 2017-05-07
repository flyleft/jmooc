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
                <li class="active-bg"><a href="index.html"><i class="fa fa-plus"></i> 添加课程</a></li>
                <li><a href="index.html"><i class="fa fa-minus-circle"></i> 删除课程</a></li>
                <li><a href="index.html"><i class="fa fa-edit"></i> 修改课程</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;"> 课程管理 - 添加</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <form role="form">
                    <div class="form-group">
                        <label for="name">名称</label>
                        <input type="text" class="form-control" id="name"
                               placeholder="请输入课程的名称">
                    </div>
                    <div class="form-group">
                        <label for="name">描述</label>
                        <input type="text" class="form-control" id="name"
                               placeholder="请输入关于本课程的描述">
                    </div>
                </form>
                <br>

                <div class="panel panel-primary">
                    <div class="panel-heading">第一章</div>
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-hover tablesorter">
                                <thead>
                                <tr>
                                    <th>课时 <i class="fa fa-sort"></i></th>
                                    <th>名称 <i class="fa fa-sort"></i></th>
                                    <th>视频地址 <i class="fa fa-sort"></i></th>
                                    <th>习题 <i class="fa fa-sort"></i></th>
                                    <th>文件 <i class="fa fa-sort"></i></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>java基础</td>
                                    <td>/video/2334</td>
                                    <td>0</td>
                                    <td>0</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>java多线程</td>
                                    <td>/video/2334</td>
                                    <td>2</td>
                                    <td>2</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>java并发</td>
                                    <td>/video/2334</td>
                                    <td>2</td>
                                    <td>3</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>大数据</td>
                                    <td>/video/2334</td>
                                    <td>2</td>
                                    <td>4</td>
                                </tr>
                                </tbody>
                            </table>
                            <button type="submit" class="btn btn-default">添加课时</button>
                        </div>
                    </div>
                    <div class="panel-heading">第二章</div>
                    <div class="table-responsive">
                        <table class="table table-hover tablesorter">
                            <thead>
                            <tr>
                                <th>课时 <i class="fa fa-sort"></i></th>
                                <th>名称 <i class="fa fa-sort"></i></th>
                                <th>视频地址 <i class="fa fa-sort"></i></th>
                                <th>习题 <i class="fa fa-sort"></i></th>
                                <th>文件 <i class="fa fa-sort"></i></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>1</td>
                                <td>java基础</td>
                                <td>/video/2334</td>
                                <td>0</td>
                                <td>0</td>
                            </tr>
                            <tr>
                                <td>2</td>
                                <td>java多线程</td>
                                <td>/video/2334</td>
                                <td>2</td>
                                <td>2</td>
                            </tr>
                            <tr>
                                <td>3</td>
                                <td>java并发</td>
                                <td>/video/2334</td>
                                <td>2</td>
                                <td>3</td>
                            </tr>
                            <tr>
                                <td>4</td>
                                <td>大数据</td>
                                <td>/video/2334</td>
                                <td>2</td>
                                <td>4</td>
                            </tr>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-default">添加课时</button>
                    </div>
                    <div class="panel-heading">添加章节</div>
                </div>
                <br>
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div><!-- /.row -->

    </div><!-- /#page-wrapper -->

</div><!-- /#wrapper -->

<!-- JavaScript -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

</body>
</html>