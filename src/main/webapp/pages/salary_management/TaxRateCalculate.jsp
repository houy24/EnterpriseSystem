
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>个人所得税计算</title>
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
            margin-left: -15px;
            z-index: -1;
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

        /* 下拉框内字体大小 */
        .layui-form-select dl dd {
            cursor: pointer;
            font-size: 16px;
        }
        /* 下拉框宽度 */
        .layui-select-title {
            width: 240px;
        }


    </style>

</head>


<body>

<%--标题，刷新按钮 部分--%>
<div class="x-nav" style="margin-top: 10px;margin-left: 10px;">
            <span class="layui-breadcrumb" style="visibility: visible">
                <span class="spanTitle"><a>首页</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>税率管理</a> </span>
    <%--            <span lay-separator>/</span>--%>
                <span class="spanTitle"><a>个人所得税计算</a> </span>
            </span>

    <a class="layui-btn layui-btn-small layui-btn-normal aFlush"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="height:10px;font-size: 20px;!important;">&#xe669;</i>
    </a>
</div>

<%--表单部分--%>

<%--计算税率--%>
<div style="margin-left: 50px;margin-top: 20px;">
    <form class="layui-form" >


<div class="layui-form-item">
    <%--。。。。。左边。。。--%>
    <div class="layui-inline">

        <%--收入类型    taxRateType--%>
        <%--1、除去五险一金        1   不计算五险一金     --%>
        <%--2、未除去五险一金      2     计算五险一金      --%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: center;">
                    <span style="font-size: 18px;">收入类型：</span>
                </label>
                <div class="layui-input-inline ">
                    <%-- 尝试给下拉框添加事件  --%>
                    <select class="selectTaxRateType" name="taxRateType" id="taxRateType"
                            lay-verify="required" lay-filter="selectDemo" >
                        <option value="1" selected>总工资（不计算五险一金）</option>
                        <option value="2">总工资（计算五险一金）</option>
                    </select>
                </div>
            </div>
        </div>

        <%--税前工资    preMoney--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: center;">
                    <span style="font-size: 18px;">税前工资：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="preMoney" lay-verify="required|number"
                            autocomplete="off" class="layui-input" value="">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <%--起征点     baseMoney--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: center;">
                    <span style="font-size: 18px;">起征点：</span>
                </label>
                <div class="layui-input-inline ">
                    <select name="baseMoney" id="baseMoney" lay-verify="required" style="width: 260px;">
                        <option value="5000" selected>5000</option>
                        <option value="3500">3500</option>
                    </select>
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>


    </div>

    <%--。。。。。右边显示结果。。。--%>
    <div class="layui-inline" style="margin-left: 160px;">

        <%--计算结果--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="" style="width: 110px;text-align: center;">
                    <span style="font-size: 20px;color: black;margin-left: 200px;">计算结果</span>
                </label>
            </div>
        </div>

        <%--应交税款    shouldTaxRateMoney--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: center;">
                    <span style="font-size: 18px;">应交税款：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="shouldTaxRateMoney" id="shouldTaxRateMoney"
                            style="color: red;" lay-verify="" readonly
                            autocomplete="off" class="layui-input" value="">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <%--五险一金    fiveOneMoney--%>
        <div class="layui-form-item divlabel1"  id="fiveOneMoneyDiv" style="display: none;"> <%--初始不显示--%>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: center;">
                    <span style="font-size: 18px;">五险一金：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="fiveOneMoney" id="fiveOneMoney" style="color: red;"
                            lay-verify="" readonly
                            autocomplete="off" class="layui-input" value="">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>

        <%--税后工资    realyMoney--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 110px;text-align: center;">
                    <span style="font-size: 18px;">税后工资：</span>
                </label>
                <div class="layui-input-inline ">
                    <input  type="text" name="realyMoney" id="realyMoney"
                            style="color: red;" lay-verify="" readonly
                            autocomplete="off" class="layui-input" value="">
                </div>
                <label class="layui-form-label divlable3" >
                    <span style="font-size: 18px;">元/月</span>
                </label>
            </div>
        </div>


    </div>


</div>


        <%--提交按钮，重置按钮--%>
        <div class="layui-form-item divlabel1" >
            <div class="layui-input-block" style="margin-left: 260px;">
                <button class="layui-btn " lay-submit lay-filter="myForm"
                        style="font-size: 18px; margin-right: 50px;">一键查询</button>
                <button type="reset" class="layui-btn layui-btn-primary"
                        style="font-size: 18px; width: 100px;!important;">重置</button>
            </div>
        </div>

    </form>

</div>



<script>

    layui.use('form', function(){
        var form = layui.form;
        var layer = layui.layer;


        // 监听下拉框改变事件
        form.on('select(selectDemo)', function (data) {

            let s = document.getElementById('fiveOneMoneyDiv').style.display;

            if (data.value === '1') {
                console.log('111');

                // 当前要隐藏
                if (s === '') {
                    // 未隐藏，清空三个框的值
                    document.getElementById('shouldTaxRateMoney').value = '';
                    document.getElementById('fiveOneMoney').value = '';
                    document.getElementById('realyMoney').value = '';
                }

                $('#fiveOneMoneyDiv').hide();
            } else if (data.value === '2') {
                console.log('222');

                // 当前要展示
                if (s === 'none') {
                    // 隐藏了，清空三个框的值
                    document.getElementById('shouldTaxRateMoney').value = '';
                    document.getElementById('fiveOneMoney').value = '';
                    document.getElementById('realyMoney').value = '';
                }

                $('#fiveOneMoneyDiv').show();
            }



        });

        //监听提交
        form.on('submit(myForm)', function(data){
            //   layer.msg(JSON.stringify(data.field));

            $.ajax({
                url: "${pageContext.request.contextPath}/queryTaxRateMoney",
                data: {     // 表单数据
                    taxRateType : data.field.taxRateType,   // 收入类型
                    preMoney : data.field.preMoney,     // 税前工资
                    baseMoney : data.field.baseMoney,   // 起征点
                },
                dataType: 'json',
                success: function (result) {
                    console.log(result);

                    if (result.check.toString() === "false") {

                        layer.alert("查询失败！");

                    } else if (result.check.toString() === "true") {
                        let obj = eval(result);
                        console.log(obj.check);
                        // console.log("eval(result) : " + eval(result))
                        // console.log("eval(result.nowWorkTitle) : " + eval(result.nowWorkTitle))

                        // let nowWorkTitle = eval(obj.nowWorkTitle);
                        // let nowUserData = eval(obj.nowUserData);

                        layer.open({
                            shade:0,
                            type:0,
                            icon:6,
                            content:'查询成功！',
                            anim:3,
                            button:'确定',
                            yes() {
                                // 更改值
                                $('#shouldTaxRateMoney').val(obj.shouldTaxRateMoney);
                                $('#fiveOneMoney').val(obj.fiveOneMoney);
                                $('#realyMoney').val(obj.realyMoney);

                                // 关闭当前弹出层
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index); //再执行关闭
                                // // 父页面更新
                                // window.parent.location.replace(window.parent.location.href)
                                layer.closeAll();
                            }
                        });
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


