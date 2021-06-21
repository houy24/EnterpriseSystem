<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/8
  Time: 0:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加销售记录</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/AddSalesRecord.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use(['form', 'element', 'jquery'], function () {
            let form = layui.form,
                element = layui.element,
                $ = layui.jquery;

            //监听提交
            form.on('submit(demo1)', function (data) {
                console.log(data);
                let userId = $("#userid").text()
                let userName = $("#username").text();
                let json = data.field;
                json["userId"] = userId;
                json["userName"] = userName;
                $.ajax({
                    url: "/AddOneSaleRecord",
                    type: "post",
                    data: JSON.stringify(json),
                    dataType: "json",
                    contentType: "json/application",
                    success: function (data) {
                        console.log(data);
                        layer.msg(JSON.stringify(data));
                    },
                    error: function () {
                        console.log("error")
                    }
                })
                return false;
            });
        })
    </script>
</head>
<body>
<blockquote class="layui-elem-quote layui-text" style="font-size: 28px">
    添加销售记录
</blockquote>
<form class="layui-form" lay-filter="formTest" action="">
    <div class="layui-form-item">
        <label class="layui-form-label lab1"><span>用户编号：</span>
            <span id="userid">${userData.userId}</span>
        </label>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab1"><span>用户姓名：</span>
            <span id="username">${userData.userName}</span>
        </label>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>销售任务编号:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="salesTaskId" lay-verify="title" autocomplete="off" placeholder="销售任务编号" required
                   lay-verify="required" value="${saleTaskId}"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>产品编号:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="productId" lay-verify="title" autocomplete="off" placeholder="产品编号" required
                   lay-verify="required" value="${productId}"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>销售数量:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="saleNumber" lay-verify="title" autocomplete="off" placeholder="销售数量" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>销售单价:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="saleOneMoney" lay-verify="title" autocomplete="off" placeholder="销售单价" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab"><span>销售总额:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="saleSumMoney" lay-verify="title" autocomplete="off" placeholder="销售总额" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn " lay-submit="" style="margin-left: -100px!important;"
                    lay-filter="demo1">立即提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary" style="margin-left: 40px;">重置</button>
        </div>
    </div>
</form>
</body>
</html>
