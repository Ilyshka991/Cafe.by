<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean class="bsuir.pechuro.entity.Product" scope="page" id="product"/>
<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>
<section>
    <header class="major">
        <h2>${range_word}</h2>
    </header>
    <form method="POST"
          action="/cafe.by/add_product_to_basket">
        <div class="posts">
            <c:choose>
                <c:when test="${products!=null}">
                    <c:forEach var="product" items="${products}">
                        <article>
                            <a href="#" class="image"><img src="/images/products/${product.imagePath}"
                                                           alt="lorem"/></a>
                            <c:choose>
                                <c:when test="${locale eq 'ru'}">
                                    <h3>${product.nameRu}</h3>
                                </c:when>
                                <c:when test="${locale eq 'en'}">
                                    <h3>${product.nameEn}</h3>
                                </c:when>
                            </c:choose>
                            <p>${cost_word}: ${product.cost} BYN</p>
                            <div class="wall_form" id="popup_message_form_${product.id}"
                                 style="display:none;">
                                <c:choose>
                                    <c:when test="${!(product.type eq 'weight')}">
                                        <p>${volume1_word}: ${product.weight} ${ml_word}</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>${weight_word}: ${product.weight} ${g_word}</p>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${locale eq 'en'}">
                                        <p>${product.descriptionEn}</p>
                                    </c:when>
                                    <c:when test="${locale eq 'ru'}">
                                        <p>${product.descriptionRu}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <ul class="actions">
                                <li>
                                    <input type="button" id="click_mes_form_${product.id}"
                                           value="${view_word}">
                                    <script type="text/javascript">
                                        $(document).ready(function () {
                                            $("#click_mes_form_${product.id}").click(function () {
                                                $("#popup_message_form_${product.id}").slideToggle("slow");
                                                return false;
                                            });
                                        });
                                    </script>
                                </li>
                                <li>
                                    <input type="number" step="1" min="0" max="10" value="0" class="number"
                                           id="number-for-add-${product.id}"
                                           name="number_for_add_${product.id}" onkeypress="return false"
                                           onclick="incrementProduct(${product.id})">
                                    <input type="text" style="display:none;"
                                           name="productId_${product.id}"
                                           value="${product.id}">
                                </li>
                            </ul>

                        </article>
                    </c:forEach>
                </c:when>
            </c:choose>
            <article>
                <h2>${not_find}</h2>
            </article>
        </div>
        <div class="posts">
            <article>
                <input type="submit" id="add-button-to-basket"
                       value="${basket_add_word}">
            </article>
        </div>
    </form>
</section>