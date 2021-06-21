<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>动态时钟显示</title>
</head>
<body>
<!--时间-->
<section class="loadTime">
    <span id="time">2015年1月1日 11:11  星期一</span>
</section>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/time.js"></script>
</html>
