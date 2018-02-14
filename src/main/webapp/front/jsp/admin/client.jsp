<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="tags" %>
<jsp:useBean class="bsuir.pechuro.entity.Product" scope="page" id="product"/>

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
<!— Wrapper —>
<div id="wrapper">
    <!— Main —>
    <div id="main">
        <div class="inner">

            <%@include file="/front/html/header.html" %>

            <%@include file="/front/html/forms.html" %>


            <!— Section —>
            <section>
                <header class="major">
                    <h2>${list_clients_word}</h2>
                </header>
                <c:choose>
                    <c:when test="${clients!=null}">
                        <c:forEach var="client" items="${clients}">
                            <li id="client_s">${client.login}</li>
                            <h4>${client.name} ${client.surname}</h4>
                            <c:choose>
                                <c:when test="${client.status eq 'active'}">
                                    <a href="/cafe.by/change_client_status?clientId=${client.id}"
                                       class="button">${client_ban_word}</a>
                                </c:when>
                                <c:when test="${client.status eq 'banned'}">
                                    <a href="/cafe.by/change_client_status?clientId=${client.id}"
                                       class="button">${client_unban_word}</a>
                                </c:when>
                            </c:choose>
                            <a href="/cafe.by/delete_client?clientId=${client.id}" class="button">${delete_word}</a>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2>${found_nothing_word}</h2>
                    </c:otherwise>
                </c:choose>
            </section>


        </div>
    </div>

    <ctg:menu/>

</div>

<!— Scripts —>
<script>
    <%@include file="/front/js/menu/main.js" %>
    <%@include file="/front/js/menu/util.js" %>
    <%@include file="/front/js/form/form.js"%>
</script>

</body>
</html>
