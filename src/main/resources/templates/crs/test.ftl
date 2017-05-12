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
                <h2><a href="/user/tea/les_mgr?crs_id=${crs_id!}&chp_id=${chp_id!}"><small>返回课时管理</small></a></h2>
                <br>
                <button class="btn btn-default btn-lg" id="post_chp"><i class="fa fa-plus"></i> <span class="network-name">添加习题</span></button>
                <br><br><br>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal" id="post_file_form">
                    <div class="form-group">
                        <label class="col-md-2 control-label">习题</label>
                        <div class="col-md-9">
                            <input name="name" type="text" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">选项A</label>
                        <div class="col-md-9">
                            <input name="a" type="text" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">选项B</label>
                        <div class="col-md-9">
                            <input name="b" type="text" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">选项C</label>
                        <div class="col-md-9">
                            <input name="c" type="text" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">选项D</label>
                        <div class="col-md-9">
                            <input name="d" type="text" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">答案</label>
                        <div class="col-md-9">
                            <select class="form-control" name="answer">
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">解析</label>
                        <div class="col-md-9">
                            <input name="analysis" type="text" class="form-control input-md">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">类型</label>
                        <div class="col-md-9">
                            <select class="form-control" name="type">
                                <option value="c">c</option>
                                <option value="cp">c++</option>
                                <option value="java">java</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-2 control-label">难度系数</label>
                        <div class="col-md-9">
                            <select class="form-control" name="type">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                    </div>
                </form>
             </div>
        </div>

</div><!-- /#page-wrapper -->

</div><!-- /#wrapper -->

<!-- JavaScript -->
<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>