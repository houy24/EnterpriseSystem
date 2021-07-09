<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/30
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
    <title>生产型企业信息化管理系统</title>
</head>
<body>
<form class="layui-form" lay-filter="formTest" action="">
    <div class="layui-form-item" style="margin-top: 30px;">
        <label class="layui-form-label lab">
            <span>库存编号:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="productWarehouseId" name="productWarehouseId" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 460px;" readonly = "readonly"
                   class="layui-input">
        </div>
    </div><div class="layui-form-item" style="margin-top: 30px;">
    <label class="layui-form-label">
        <span>产品编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productId" name="productId" lay-verify="title" autocomplete="off" placeholder="产品名称" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input">
    </div>
</div>
    <div class="layui-form-item">
        <label class="layui-form-label lab">
            <span>产品名称:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="productName" name="productName" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 150px;" readonly = "readonly"
                   class="layui-input inp">
        </div>
        <label class="layui-form-label lab">
            <span>产品类型:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="productTypeName" name="productTypeName" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 150px;" readonly = "readonly"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label lab">
            <span>产品数量:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="productNumber"  name="productNumber" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 150px;"
                   class="layui-input inp">
        </div>
        <label class="layui-form-label lab">
            <span>单品进价:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="oneInPrice" name="oneInPrice" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 150px;"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label lab">
            <span>产品总进价:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="totalInPrice" name="totalInPrice" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 150px;"
                   class="layui-input inp">
        </div>
        <label class="layui-form-label lab">
            <span>入厂日期:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="InWareHouseTime" name="InWareHouseTime" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 150px;" readonly = "readonly"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label lab">
            <span>生产地址:</span></label>
        <div class="layui-input-inline">
            <input type="text" id="productionAddress" name="productionAddress" lay-verify="title" autocomplete="off" placeholder="产品名称" required
                   lay-verify="required" style="width: 460px;"
                   class="layui-input inp">
        </div>
    </div>
    <div class="layui-form-item item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit=""
                    lay-filter="demo1">立即提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary" style="margin-left: 40px;">重置</button>
        </div>
    </div>
</form>
<script>
    layui.use(['form', 'element', 'jquery'], function () {
        let form = layui.form,
            $ = layui.jquery;

        //监听提交
        form.on('submit(demo1)', function (data) {
            console.log(data);
            let json = data.field;
            $.ajax({
                url: "${pageContext.request.contextPath}/updateInventoryManagement",
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
</body>
</html>