<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/30
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
        <span>记录编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleRecordId" lay-verify="title" autocomplete="off" placeholder="销售记录编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input">
    </div>
</div><div class="layui-form-item" style="margin-top: 30px;">
    <label class="layui-form-label lab">
        <span>任务编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleTaskId" lay-verify="title" autocomplete="off" placeholder="销售任务编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>产品编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productId" lay-verify="title" autocomplete="off" placeholder="产品编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label">
        <span>用户编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="userId" lay-verify="title" autocomplete="off" placeholder="用户编号" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input">
    </div>

    <label class="layui-form-label lab">
        <span>销售状态:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleRecordState" lay-verify="title" autocomplete="off" placeholder="销售状态" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>销售数量:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleNumber" lay-verify="title" autocomplete="off" placeholder="销售数量" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>销售单价:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleOneMoney" lay-verify="title" autocomplete="off" placeholder="销售单价成交价" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>销售总额:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleSumMoney" lay-verify="title" autocomplete="off" placeholder="销售总金额" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>销售时间:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleFinishTime" lay-verify="title" autocomplete="off" placeholder="销售时间" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
</body>
</html>