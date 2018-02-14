<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.pechuro.entity.Product" scope="page" id="product"/>
<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>

<!— Sidebar —>
<div id="sidebar">
    <div class="inner">

        <!— Menu —>
        <nav id="menu">
            <header class="major">
                <h2>${menu_word}</h2>
            </header>
            <ul>
                <li><a href="/cafe.by/order_show">${list_orders_word}</a></li>
                <li><a href="/cafe.by/change_password_forward">${change_password_word}</a></li>
            </ul>
        </nav>


        <%@include file="/front/html/contacts.html" %>
        <%@include file="/front/html/footer.html" %>
    </div>
</div>
</html>