
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>职称查询</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>

    <style>

        .x-nav{     /*正常版*/
            padding: 0 20px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            line-height: 50px;
            height: 50px;
            overflow: hidden;
            background: #fff;
        }
        .spanTitle {
            font-size: 24px;
        }
        .aFlush {
            width:90px;
            margin-top:3px;
            float:right;
            margin-right: 10px;
        }

        /*表单项*/
        .divlabel1 {
            margin-top: 40px;
            margin-left: 0px;
        }
        .divlable3 {
            width: 100px;
            margin-left: -70px;
        }
        input {
            font-size: 18px;!important;
            width: 240px;
        }
        .layui-input {
            width: 240px;
        }

        .layui-layer-title {
            font-size: 20px;
        }
        .layui-layer-dialog .layui-layer-content {
            font-size: 18px;
        }


    </style>

</head>


<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
            <span class="layui-breadcrumb" style="visibility: visible">
                <span class="spanTitle"><a>首页</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>职称管理</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>查询员工职称</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--表单部分--%>
<div style="margin-left: 50px;margin-top: 20px;">
    <form class="layui-form" >

        <input hidden name="workTitleId" value="" disabled />

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: left;">
                    <span style="font-size: 18px;">员工手机号：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="userPhone" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="">
                </div>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: left;">
                    <span style="font-size: 18px;">员工姓名：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="userName" lay-verify="<%--required--%>"
                            autocomplete="off" class="layui-input" value="">
                </div>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: left;">
                    <span style="font-size: 18px;">员工邮箱：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="userEmail" lay-verify="<%--email--%>"
                            autocomplete="off" class="layui-input" value="">
                </div>
            </div>
        </div>

        <%--提交按钮--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 260px;">
                <button class="layui-btn " lay-submit lay-filter="myForm"
                        style="font-size: 18px; margin-right: 50px;">一键查询</button>
            </div>
        </div>

    </form>

</div>



<script>

    layui.use('form', function(){
        var form = layui.form;
        var layer = layui.layer;

        //监听提交
        form.on('submit(myForm)', function(data){
         //   layer.msg(JSON.stringify(data.field));

            $.ajax({
                url: "${pageContext.request.contextPath}/queryWorkTitle",
                data: {     // 表单数据
                    userPhone : data.field.userPhone,
                    userName : data.field.userName,
                    userEmail : data.field.userEmail,
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);

                    if (result.check.toString() === "false") {

                        layer.alert("查询失败！");

                    } else if (result.check.toString() === "true") {
                        let obj = eval(result);
                        console.log(obj.check);
                        console.log(obj.nowWorkTitle);
                        // console.log("eval(result) : " + eval(result))
                        // console.log("eval(result.nowWorkTitle) : " + eval(result.nowWorkTitle))

                        // let nowWorkTitle = eval(obj.nowWorkTitle);
                        // let nowUserData = eval(obj.nowUserData);

                        let nowWorkTitle = obj.nowWorkTitle;
                        let nowUserData = obj.nowUserData;

                        // layer.alert(nowWorkTitle);
                        // layer.alert(nowUserData.userName);

                        layer.alert("查询成功！ </br>" + "员工：" + nowUserData.userName
                                    + ",员工手机号：" + nowUserData.userPhone
                                    + ",员工职称：" + nowWorkTitle.workTitleName
                                    + ",职称工资：" + nowWorkTitle.workTitleMoney);

                        // layer.msg("保存成功！",{  time: 1000 } ,
                        //     function() {
                        //         // 关闭当前弹出层
                        //         var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //         parent.layer.close(index); //再执行关闭
                        //         // 父页面更新
                        //         window.parent.location.replace(window.parent.location.href)
                        //     }
                        // );


                        // layer.open({
                        //     shade:0,
                        //     type:0,
                        //     icon:6,
                        //     content:'添加成功！',
                        //     anim:3,
                        //     button:'确定',
                        //     yes() {
                        //         // 关闭当前弹出层
                        //         var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //         parent.layer.close(index); //再执行关闭
                        //         // 父页面更新
                        //         window.parent.location.replace(window.parent.location.href)
                        //     }
                        // });
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.alert("查询失败！");
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });

            return false; // 表单提交
        });
    });

</script>






</body>
</html>


