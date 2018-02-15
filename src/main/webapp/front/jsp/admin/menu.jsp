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
        <%@include file="/front/html/search.html" %>
        <!— Menu —>
        <nav id="menu">
            <header class="major">
                <h2>${menu_word}</h2>
            </header>
            <ul>
                <li>
                    <p>${range_word}</p>
                    <span class="opener" id="type_food">${type_food_word1}</span>
                    <ul>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=soup">${soup_word}</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=hotDish">${hot_dishes_word}</a>
                        </li>
                        <li><a class="podmenu"
                               href="/cafe.by/find_by_type?product_type=bakeryProduct">${bakery_products_word}</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=salad">${salad_word}</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=garnish">${garnish_word}</a>
                        </li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=pizza">${pizza_word}</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=dessert">${desserts_word}</a>
                        </li>
                    </ul>
                    <span class="opener" id="type_drink">${type_drink_word1}</span>
                    <ul class="podmenu">
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=juice">${juice_word}</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=water">${water_word}</a></li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=hotDrink">${hot_drink_word}</a>
                        </li>
                        <li><a class="podmenu" href="/cafe.by/find_by_type?product_type=soda">${soda_word}</a></li>
                    </ul>
                </li>
                <li><a href="/cafe.by/edit_clients">${list_clients_word}</a></li>
                <li><a href="/cafe.by/staff_list">${list_personal_word}</a></li>
                <c:choose>
                    <c:when test="${user.isMain == 1}">
                        <li><a href="/cafe.by/admin_list">${list_admin_word}</a></li>
                    </c:when>
                </c:choose>
                <li><a href="/cafe.by/change_password_forward">${change_password_word}</a></li>
            </ul>
        </nav>

        <%@include file="/front/html/contacts.html" %>
        <%@include file="/front/html/footer.html" %>

    </div>
</div>
</html>