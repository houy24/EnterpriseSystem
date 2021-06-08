package com.xxx.utils;

import java.util.List;

public class MyPageHelperUtils { // 自己编写分页工具

    public static <T> List<T> getListByPagesLimit(List<T> list,int page,int limit) {
        int count = list.size(); // 总数
        if (page <= 0 || limit <= 0) { // 没有第 page 页
            list.clear();
            return list;
        }
        int pre = (page - 1) * limit;
        if (pre >= count) { // 没有了
            list.clear();
            return list;
        }
        int yu = count - pre;
        if (yu > limit) {
            yu = limit;
        }
        return list.subList(pre,pre + yu);
    }





}
