<%--
  Created by IntelliJ IDEA.
  User: 86150
  Date: 2021/6/30
  Time: 16:17
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
        <span>任务编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="saleTaskId" lay-verify="title" autocomplete="off" placeholder="库存编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input">
    </div>
</div>
<div class="layui-form-item" style="margin-top: 30px;">
    <label class="layui-form-label">
        <span>产品编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productId" lay-verify="title" autocomplete="off" placeholder="产品编号" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input">
    </div>
</div>
<div class="layui-form-item" style="margin-top: 30px;">
    <label class="layui-form-label lab">
        <span>库存编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productWarehouseId" lay-verify="title" autocomplete="off" placeholder="产品名称" required
               lay-verify="required" style="width: 460px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>用户编号:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="userId" lay-verify="title" autocomplete="off" placeholder="产品类型" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>需销售量:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productPreSumNumber" lay-verify="title" autocomplete="off" placeholder="产品类型" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>剩余销售量:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="productNumber" lay-verify="title" autocomplete="off" placeholder="产品数量" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>最低单价:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="oneLowestPrice" lay-verify="title" autocomplete="off" placeholder="单品进价" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>最低总额:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="sumLowestPrice" lay-verify="title" autocomplete="off" placeholder="产品总进价" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>开始时间:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="taskStartTime" lay-verify="title" autocomplete="off" placeholder="入厂日期" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
<div class="layui-form-item">
    <label class="layui-form-label lab">
        <span>结束时间:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="latestFinishTime" lay-verify="title" autocomplete="off" placeholder="生产地址" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
    <label class="layui-form-label lab">
        <span>完成状态:</span></label>
    <div class="layui-input-inline">
        <input type="text" id="finishState" lay-verify="title" autocomplete="off" placeholder="完成状态" required
               lay-verify="required" style="width: 150px;" readonly = "readonly"
               class="layui-input inp">
    </div>
</div>
</body>
</html>