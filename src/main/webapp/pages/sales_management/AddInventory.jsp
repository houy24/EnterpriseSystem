<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/10
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>添加库存</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/layui/css/layui.css?time=<%=Math.random()%>">
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/AddSalesRecord.css?time=<%=Math.random()%>">
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js?time=<%=Math.random()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/layui/layui.js?time=<%=Math.random()%>"></script>
    <script>
        layui.use(['form', 'element', 'jquery'], function () {
            let form = layui.form,
                $ = layui.jquery;

            //监听提交
            form.on('submit(demo1)', function (data) {
                console.log(data);
                let json = data.field;
                $.ajax({
                    url:"${pageContext.request.contextPath}/addOneInventory",
                    type:"post",
                    data:JSON.stringify(json),
                    dataType:"json",
                    contentType:"json/application",
                    success:function (data) {
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
    添加库存
</blockquote>
<form class="layui-form" lay-filter="formTest" action="">
    <div class="layui-form-item">
        <label class="layui-form-label lab">
            <span>产品名称:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="productName" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>产品类型:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="productTypeName" lay-verify="title" autocomplete="off" placeholder="产品类型" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>产品数量:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="productNumber" lay-verify="title" autocomplete="off" placeholder="产品数量" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>生产地址:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="productionAddress" lay-verify="title" autocomplete="off" placeholder="生产地址" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>单进价:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="oneInPrice" lay-verify="title" autocomplete="off" placeholder="单进价" required
                   lay-verify="required"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <label class="layui-form-label lab">
            <span>总进价:</span></label>
        <div class="layui-input-block bl">
            <input type="text" name="totalInPrice" lay-verify="title" autocomplete="off" placeholder="总进价" required
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
