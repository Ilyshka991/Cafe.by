<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="tags" %>
<jsp:useBean class="bsuir.pechuro.entity.Product" scope="page" id="product"/>
<jsp:useBean class="bsuir.pechuro.entity.Order" scope="page" id="order"/>

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
                    <h2>${basket_word}</h2>
                </header>
                <div class="posts">
                    <article>
                        <c:choose>
                            <c:when test="${products!=null}">
                                <c:forEach var="product" items="${products}">
                                    <c:choose>
                                        <c:when test="${product.ordered == 0}">
                                            <c:choose>
                                                <c:when test="${locale eq 'ru'}">
                                                    <span>${product.nameRu}(x${product.number})</span>
                                                </c:when>
                                                <c:when test="${locale eq 'en'}">
                                                    <span>${product.nameEn}(x${product.number})</span>
                                                </c:when>
                                            </c:choose>
                                            <span>${product.commonCost} BYN</span>
                                            <form method="POST"
                                                  action="/cafe.by/remove_product_from_basket?productId=${product.id}">
                                                <input type="number" step="1" min="0" max="${product.number}" value="0"
                                                       id="number-for-delete-${product.id}" name="number_for_delete"
                                                       onkeypress="return false" class="number_delete"
                                                       onclick="incrementProductDelete(${product.id},${product.number})">
                                                <input type="submit" id="delete-button-from-basket"
                                                       value="${delete_from_basket_word}">
                                            </form>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <h1>${total_word}: ${orderCost} BYN</h1>
                            </c:when>
                            <c:otherwise>
                                <h2>${found_nothing_word}</h2>
                            </c:otherwise>
                        </c:choose>
                    </article>
                    <article>
                        <h2>${account_payment_error}</h2>
                        <form method="POST" name="payment" action="/cafe.by/payment">
                            <c:choose>
                                <c:when test="${products!=null}">
                                    <h2>
                                            ${points_word}: ${point}
                                    </h2>
                                    <input type="number" step="0.05" min="0" class="number_delete"
                                           max="${(user.point<orderCost)?user.point:orderCost}" value="0"
                                           id="point-to-payment" name="point_to_payment"
                                           onkeypress="return false"
                                           onclick="incrementBonus(${(user.point<orderCost)?user.point:orderCost})">
                                    <h2>
                                        <p>${choose_the_form_payment_word}</p>
                                        <div>
                                            <input type="radio" class="radio_payment" id="by-card"
                                                   name="choise_of_payment"
                                                   value="card" checked>
                                            <label for="by-card">${card_word}</label>
                                            <input type="radio" class="radio_payment" id="by-cash"
                                                   name="choise_of_payment"
                                                   value="cash">
                                            <label for="by-cash">${cash_word}</label>
                                        </div>
                                        <h3>
                                            <p>${desired_acquisition_time_word}</p>
                                            <input id="dateTime" type="datetime-local" name="dateTime" required>
                                            <span class="validity"></span>
                                        </h3>
                                        <div>
                                            <input type="submit" id="payment_button" value="${pay_word}">
                                        </div>
                                    </h2>
                                </c:when>
                            </c:choose>
                        </form>
                    </article>
                </div>
            </section>

            <section>
                <header class="major">
                    <h2>${ordered_word}</h2>
                </header>

                <ul>
                    <c:choose>
                        <c:when test="${orders!=null}">
                            <c:forEach var="order" items="${orders}">
                                <h3>${order.id}</h3>
                                <c:forEach var="product" items="${products}">
                                    <c:choose>
                                        <c:when test="${product.orderId == order.id}">
                                            <li>
                                                <c:choose>
                                                    <c:when test="${locale eq 'ru'}">
                                                        <p>${product.nameRu}(x${product.number})</p>
                                                    </c:when>
                                                    <c:when test="${locale eq 'en'}">
                                                        <p>${product.nameEn}(x${product.number})</p>
                                                    </c:when>
                                                </c:choose>
                                            </li>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                                <div style="display: inline-block">
                                    <h3 id="COST" style="float: left">${total_word}: ${order.cost} BYN</h3>
                                    <label id="cost_data" for="COST">${date_of_receiving_word}: ${order.date}</label>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h2>${found_nothing_word}</h2>
                        </c:otherwise>
                    </c:choose>
                </ul>
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
    <%@include file="/front/js/elementcontroller.js"%>
    <%@include file="/front/js/functions.js"%>
</script>

</body>
</html>