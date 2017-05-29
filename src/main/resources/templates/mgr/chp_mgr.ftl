<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>章节管理</title>

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
                <li><a href="/user/tea/crs_mgr?do=mod"><i class="fa fa-edit"></i> 课程管理</a></li>
            </ul>

            <h3 style="text-align: center;color: #ffffff;"> 章节管理</h3>
        </div><!-- /.navbar-collapse -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <button class="btn btn-default btn-lg" id="post_chp"><i class="fa fa-plus"></i> <span class="network-name">添加章节</span></button>
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
                            <th>课时管理</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list chps as chp>
                        <td>${chp.pos!}</td>
                        <td>${chp.name!}</td>
                        <td>
                            <a href='/user/tea/les_mgr?crs_id=${crs!}&chp_id=${chp.id!}'><i class="fa fa-pencil"></i></a>
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
<script src="/js/bootbox.min.js"></script>
<script type="text/javascript">
        $(document).on("click", "#post_chp",
                function() {
                    bootbox.dialog({
                        title: "新的章节",
                        message: '<div class="row"><div class="col-md-12"><form class="form-horizontal" id="post_chp_form"><div class="form-group"> <label class="col-md-2 control-label">名称</label> <div class="col-md-9"> <input name="name" type="text" placeholder="章节名" class="form-control input-md"> </div></div><input type="hidden" name="crs_id" value="${crs!}"/> <input type="hidden" name="pos" value="${pos!0}"/></form></div></div>',
                        buttons: {
                            success: {
                                label: "提交",
                                className: "btn-success",
                                callback: function() {
                                    var a = document.getElementById("post_chp_form");
                                    a.name="chapter",
                                    a.action = "/user/tea/chp_mgr/add",
                                    a.method = "post",
                                    a.submit()
                                }
                            }
                        }
                    })
                });
</script>
</body>
</html>