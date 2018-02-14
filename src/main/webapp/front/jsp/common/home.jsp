<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.pechuro.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.pechuro.entity.Review" scope="page" id="review"/>
<%@ taglib prefix="ctg" uri="tags" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <style>
        <%@include file="/front/css/menu/main.css" %>
        <%@include file="/front/css/form/form.css"%>
        <%@include file="/front/css/index/review.css"%>
        <%@include file="/front/css/menu/search.css"%>
        <%@include file="/front/css/form/addForm.css"%>
        <%@include file="/front/css/form/button.css"%>
    </style>
    <script>
        <%@include file="/front/js/lib/jquery.min.js" %>
        <%@include file="/front/js/lib/skel.min.js" %>
    </script>
    <%@include file="/front/html/allBundle.html" %>
    <title>Cafe</title>
</head>
<body>
<!-- Wrapper -->
<div id="wrapper">
    <!-- Main -->
    <div id="main">
        <div class="inner">

            <%@include file="/front/html/header.html" %>

            <%@include file="/front/html/forms.html" %>

            <jsp:include page="${ctg:chooseBanner(user)}"/>

            <ctg:list/>

            <%@include file="/front/html/navigation.html" %>
            <c:choose>
                <c:when test="${user.role eq 'client'}">
                    <section>
                        <header class="major">
                            <h2>${review_add}</h2>
                        </header>
                        <%@include file="/front/html/addReview.html" %>
                    </section>
                </c:when>
            </c:choose>

            <section>
                <header class="major">
                    <h2>${review_header}</h2>
                </header>
                <c:choose>
                    <c:when test="${reviews!=null}">
                        <div class="layer">
                            <c:forEach var="review" items="${reviews}">
                                <%@include file="/front/html/review.html" %>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h3>${found_nothing_word}</h3>
                    </c:otherwise>
                </c:choose>
            </section>

        </div>
    </div>

    <ctg:menu/>

</div>

<!-- Scripts -->
<script>
    <%@include file="/front/js/menu/main.js" %>
    <%@include file="/front/js/menu/util.js" %>
    <%@include file="/front/js/form/form.js"%>
    <%@include file="/front/js/validation.js" %>
    <%@include file="/front/js/functions.js" %>
    <%@include file="/front/js/elementcontroller.js" %>
</script>


</body>
</html>