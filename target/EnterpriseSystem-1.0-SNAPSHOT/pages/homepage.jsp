
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage.css?time=<%=Math.random()%>" type="text/css">
</head>
<body>
    <div class="homepage_body">
        <div class="homepage_personnel_matters">
            <a href="/pages/personnel_management/BackPeople1HomePage.jsp"><img class="personal_matters_photo" /></a>
        </div>
        <div class="homepage_finance">
            <a href="/pages/salary_management/BackSalaryHomePage.jsp"><img class="finance_photo" /></a>
        </div>
        <div class="homepage_sale">
            <a href="/pages/sales_management/BackSaleHomePage.jsp"><img class="sale_photo" /></a>
        </div>
    </div>
</body>
</html>
