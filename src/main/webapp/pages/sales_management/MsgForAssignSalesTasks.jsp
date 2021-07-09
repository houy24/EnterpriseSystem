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
<div class="layui-form-item" style="margin-top: 30px;">
    <label class="layui-form-label lab">
        <span>库存编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productWarehouseId" lay-verify="title" autocomplete="off" placeholder="库存编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input">
    </div>
</div><div class="layui-form-item" style="margin-top: 30px;">
    <label class="layui-form-label">
        <span>产品编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productId" lay-verify="title" autocomplete="off" placeholder="产品编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>产品名称:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productName" lay-verify="title" autocomplete="off" placeholder="产品名称" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>产品类型:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productTypeName" lay-verify="title" autocomplete="off" placeholder="产品类型" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>产品数量:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productNumber" lay-verify="title" autocomplete="off" placeholder="产品数量" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>单品进价:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="oneInPrice" lay-verify="title" autocomplete="off" placeholder="单品进价" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>产品总进价:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="totalInPrice" lay-verify="title" autocomplete="off" placeholder="产品总进价" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>入厂日期:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="InWareHouseTime" lay-verify="title" autocomplete="off" placeholder="入厂日期" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>生产地址:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productionAddress" lay-verify="title" autocomplete="off" placeholder="生产地址" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
</body>
</html>