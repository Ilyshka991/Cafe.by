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
    <div class="posts">
        <c:choose>
            <c:when test="${products!=null}">
                <c:forEach var="product" items="${products}">
                    <article>
                        <a href="#0" class="image"><img src="/images/products/${product.imagePath}"
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
                        <div class="wall_form" id="popup_edit_form_${product.id}" style="display:none;">
                            <form method="post" class="cd-form" id="edit-product-${product.id}"
                                  action="/cafe.by/edit_product?productId=${product.id}"
                                  enctype="multipart/form-data" onsubmit="return checkEdit(${product.id})">
                                <%@include file="/front/html/editForm.html" %>
                            </form>
                        </div>
                        <ul class="actions">
                            <li>
                                <input class="admin_action" type="button" id="click_mes_form_${product.id}"
                                       value="${view_word}">
                            </li>
                            <li>
                                <input class="admin_action" type="button" id="click_edit_form_${product.id}"
                                       value="${edit_word}">
                            </li>
                            <li>
                                <form method="post" action="/cafe.by/delete_product?productId=${product.id}">
                                    <input class="admin_action" type="submit" value="${delete_button}">
                                </form>
                            </li>
                            <script type="text/javascript">
                                $(document).ready(function () {
                                    $("#click_edit_form_${product.id}").click(function () {
                                        if ($('#popup_message_form_${product.id}').is(':visible')) {
                                            $("#popup_message_form_${product.id}").slideToggle("slow");
                                        }
                                        $("#popup_edit_form_${product.id}").slideToggle("slow");
                                        return false;
                                    });
                                    $("#click_mes_form_${product.id}").click(function () {
                                        if ($('#popup_edit_form_${product.id}').is(':visible')) {
                                            $("#popup_edit_form_${product.id}").slideToggle("slow");
                                        }
                                        $("#popup_message_form_${product.id}").slideToggle("slow");
                                        return false;
                                    });
                                });
                            </script>
                        </ul>
                    </article>

                </c:forEach>
            </c:when>
        </c:choose>
        <article>
            <h2>${not_find}</h2>
        </article>
    </div>
    <c:choose>
        <c:when test="${user.role eq 'client'}">
            <div class="posts">
                <article>
                    <input type="submit" id="add-button-to-basket"
                           value="${basket_add_word}">
                </article>
            </div>
        </c:when>
    </c:choose>
    <div class="posts">
        <article>
            <form method="post" class="cd-form" id="add-product" action="/cafe.by/add_product"
                  enctype="multipart/form-data">
                <%@include file="/front/html/addForm.html" %>
            </form>
        </article>
    </div>
</section>
