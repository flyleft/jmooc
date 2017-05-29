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
            <a class="navbar-brand" href="/">首页</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav side-nav">
                <li><a href="/user/all/notice?r=t"><i class="fa fa-bell"></i> 消息中心</a></li>
                <li><a href="/user/tea/crs_mgr?do=add"><i class="fa fa-plus"></i> 添加课程</a></li>
                <li class="active-bg"><a href="#"><i class="fa fa-edit"></i> 课程管理</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;"> 课程管理</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <div class="table-responsive">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th>课程名称</th>
                            <th>方向</th>
                            <th>类型</th>
                            <th>删除</th>
                            <th>章节管理</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list courses as item>
                        <td>${item.name!}</td>
                        <td>
                        <#if  item.dir== "fe">
                            前端开发
                        <#elseif item.dir== "be">
                            后端开发
                        <#elseif item.dir== "md">
                            移动开发
                        <#else>
                            数据库开发
                        </#if>

                        </td>
                        <td>${item.type!}</td>
                        <td>
                            <a class="delete" role="button" href="/user/tea/crs_mgr/del?crs_id=${item.id}"
                               data-title="删除课程" data-text="确定删除?" data-confirm-button="是"
                               data-cancel-button="否"><i class="fa fa-trash-o"></i></a>
                        </td>
                        <td>
                            <a href='/user/tea/chp_mgr?crs_id=${item.id!}'><i class="fa fa-pencil"></i></a>
                        </td>
                        </tr>
                        </#list>

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
<script src="/js/jquery.confirm.min.js"></script>
<script type="text/javascript">
    $(".delete").confirm();
</script>
</body>
</html>