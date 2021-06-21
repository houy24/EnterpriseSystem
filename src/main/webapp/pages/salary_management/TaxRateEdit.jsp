
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>税率信息编辑</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use('element', function(){
            var element = layui.element;
        });
    </script>

    <style>
        .divlabel1 {
            margin-top: 40px;
            margin-left: 0px;

        }
        .divlable3 {
            /*width: 50px;*/
            /*margin-left: -11px;*/
            /*z-index: -1;*/
            /*text-align: center;*/
            text-align: left;
            width: 178px;
            z-index: -1;
            margin-left: 7px;
        }
        .laberText {
            text-align: center; /* label文字 */
            width: 130px;
            /*margin-left: 10px;*/
            z-index: -1;
        }

        input {
            font-size: 18px;!important;
            width: 200px;
            margin-left: -20px;
        }

        .x-nav{     /*弹出层，缩小版*/
            padding: 0 15px;
            position: relative;
            z-index: 99;
            border-bottom: 1px solid #e5e5e5;
            line-height: 40px;
            height: 40px;
            overflow: hidden;
            background: #fff;
        }
        .spanTitle {
            font-size: 20px;
        }
        .aFlush {
            width: 77px;
            margin-top: -2px;
            float: right;
            margin-right: 18px;
        }

    </style>

    <%
        String taxRateId = request.getParameter("taxRateId");
        String money = request.getParameter("money");
        String rate = request.getParameter("rate");
        String quicklyReduce = request.getParameter("quicklyReduce");

        System.out.println("TaxRateEdit.jsp => " + taxRateId + "," + money + "," + rate + "," + quicklyReduce);
    %>

</head>

<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
                <span class="layui-breadcrumb" style="visibility: visible">
                    <span class="spanTitle"><a>首页</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>税率管理</a> </span>
        <%--            <span lay-separator>/</span>--%>
                    <span class="spanTitle"><a>税率信息编辑</a> </span>
                </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"

       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>

</div>


<%--表单部分--%>
<div style="margin-left: 50px;margin-top: 20px;">
    <form class="layui-form" >

        <%--税率编号，隐藏--%>
        <input hidden name="taxRateId" value="<%=taxRateId%>" disabled />

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label laberText" >
                    <span style="font-size: 18px;">工资档位：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="money" lay-verify="required|number" style="width: 230px;"
                            autocomplete="off" class="layui-input" value="<%=money%>">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label laberText" >
                    <span style="font-size: 18px;">税率：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="rate" lay-verify="required|number" style="width: 230px;"
                            autocomplete="off" class="layui-input" value="<%=rate%>">
                </div>
            </div>
        </div>

        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label laberText" >
                    <span style="font-size: 18px;">速算扣除数：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="quicklyReduce" lay-verify="required|number"
                            style="width: 230px;" autocomplete="off" disabled
                            class="layui-input" value="<%=quicklyReduce%>">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <%--提交重置按钮--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 260px;">
                <button class="layui-btn " lay-submit lay-filter="myForm"
                        style="font-size: 18px; width: 97px;!important; margin-right: 50px;">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary"
                        style="font-size: 18px; width: 97px;!important;">重置</button>
            </div>
        </div>

    </form>

</div>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        var layer = layui.layer;

        //监听提交
        form.on('submit(myForm)', function(data){
            // layer.msg(JSON.stringify(data.field));

            $.ajax({
                url: "${pageContext.request.contextPath}/saveTaxRate",
                data: {     // 表单数据
                    taxRateId : data.field.taxRateId,
                    money : data.field.money,
                    rate : data.field.rate,
                    quicklyReduce : data.field.quicklyReduce,
                },
                dataType: 'json',
                success: function (result) {

                    // 对编辑信息进行校验
                    if (result.check.toString() === "false") {

                        layer.msg("保存失败，请输入合法信息！");

                    } else if (result.check.toString() === "true") {

                        // layer.msg("保存成功！",{  time: 1000 } ,
                        //     function() {
                        //         // 关闭当前弹出层
                        //         var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                        //         parent.layer.close(index); //再执行关闭
                        //         // 父页面更新
                        //         window.parent.location.replace(window.parent.location.href)
                        //     }
                        // );


                        layer.open({
                            shade:0,
                            type:0,
                            icon:6,
                            content:'保存成功！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                // 父页面更新
                                window.parent.location.replace(window.parent.location.href)
                            }
                        });
                    }
                },

                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    layer.msg("保存失败！");
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


