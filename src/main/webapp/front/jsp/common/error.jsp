<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.pechuro.entity.Product" scope="page" id="product"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        <%@include file="/front/css/menu/main.css" %>
        <%@include file="/front/css/form/form.css"%>
    </style>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/lib/skel.min.js" %>
    </script>

    <%@include file="/front/html/allBundle.html"%>

    <title>Cafe</title>
</head>
<body>
<!-- Wrapper -->
<div id="wrapper">
    <!-- Main -->
    <div id="main">
        <div class="inner">

            <p>Произошла ошибка!</p>

        </div>
    </div>

</div>

<!-- Scripts -->
<script>
    <%@include file="/front/js/menu/main.js" %>
    <%@include file="/front/js/form/form.js"%>
    <%@include file="/front/js/validation.js" %>
</script>


</body>
