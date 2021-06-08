layui.use(['element', 'dropdown', 'jquery'], function () {
    var $ = layui.jquery;
    var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
    let dropdown = layui.dropdown;
    $('.layui-nav-tree').on('click', 'li', function () {
        $(this).addClass('layui-nav-itemed').siblings('li').removeClass('layui-nav-itemed')
    })

    //触发事件
    var active = {
        //在这里给active绑定几项事件，后面可通过active调用这些事件
        tabAdd: function (url, id, name) {
            //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
            //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
            element.tabAdd('demo', {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="/' + url + '" style="width:100%;height:99%;"></iframe>',
                id: id //规定好的id
            })
            CustomRightClick(id); //给tab绑定右击事件
            FrameWH();  //计算ifram层的大小
        },
        tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('demo', id); //根据传入的id传入到指定的tab项2
        },
        tabDelete: function (id) {
            element.tabDelete("demo", id);//删除
        }
        , tabDeleteAll: function (ids) {//删除所有
            $.each(ids, function (i, item) {
                element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
            })
        }
    };


    //当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
    $('.lis').on('click', function () {
        var dataid = $(this);

        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            //如果比零小，则直接打开新的tab项
            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
        } else {
            //否则判断该tab项是否以及存在
            var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
            $.each($(".layui-tab-title li[lay-id]"), function () {
                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if ($(this).attr("lay-id") === dataid.attr("data-id")) {
                    isData = true;
                }
            })
            if (isData === false) {
                //标志为false 新增一个tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            }
        }
        //最后不管是否新增tab，最后都转到要打开的选项页面上
        active.tabChange(dataid.attr("data-id"));
    });

    //Hash地址的定位
    let layid = location.hash.replace(/^#demo=/, '');
    element.tabChange('test', layid);

    element.on('tab(demo)', function(elem){
        location.hash = 'demo='+ $(this).attr('lay-id');

    });


    function CustomRightClick(id) {
        //取消右键  rightmenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
        $('.layui-tab-title li').on('contextmenu', function () {
            return false;
        })
        //桌面点击右击
        $('.layui-tab-title li').on('contextmenu', function (e) {
            let that = $(this);
            that.find("li").attr("data-id", id);
            //下拉菜单
            dropdown.render({
                elem: this //触发事件的 DOM 对象
                , show: true //外部事件触发即显示
                , trigger: 'contextmenu' //右键事件
                , data: [{
                    title: '关闭当前'
                    , id: 'closeThis'
                }, {
                    title: '关闭其他'
                    ,id: 'closeOther'
                }, {
                    title: '关闭所有'
                    , id: 'closeAll'

                }]
                , click: function (menudata) {
                    if (menudata.id === 'closeThis') {
                        active.tabDelete($(that).attr("lay-id"));
                    }else if(menudata.id === 'closeOther'){
                        let tabTitle = $(".layui-tab-title li");
                        let ids = [];
                        $.each(tabTitle, function (i) {
                            ids[i] = $(this).attr("lay-id");
                        })
                        let id = $(that).attr("lay-id");
                        let index = ids.indexOf(id);
                        ids.splice(index, 1);
                        //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
                        active.tabDeleteAll(ids);
                    } else if (menudata.id === 'closeAll') {
                        let tabTitle = $(".layui-tab-title li");
                        let ids = [];
                        $.each(tabTitle, function (i) {
                            ids[i] = $(this).attr("lay-id");
                        })
                        //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
                        active.tabDeleteAll(ids);
                    }
                }
                , align: 'right' //右对齐弹出（v2.6.8 新增）
                , style: 'box-shadow: 1px 1px 10px rgb(0 0 0 / 12%);' //设置额外样式
            })
            return false;
        });
    }


    function FrameWH() {
        var h = $(window).height() - 41 - 10 - 60 - 10 - 44 - 10;
        $("iframe").css("height", h + "px");
    }

    $(window).resize(function () {
        FrameWH();
    })
});