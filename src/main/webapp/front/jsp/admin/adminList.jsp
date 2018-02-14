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

            <section>
                <div class="posts">
                    <!— Section —>
                    <article>
                        <header class="major">
                            <h2>${list_admin_word}</h2>
                        </header>
                        <c:choose>
                            <c:when test="${allAdmins!=null}">
                                <c:forEach var="admin" items="${allAdmins}">
                                    <c:choose>
                                        <c:when test="${admin.isMain == 0}">
                                            <li id="client_s">${admin.id} ${admin.login} </li>
                                            <div class="wall_form" id="admin-password-${admin.id}"
                                                 style="display:none;">
                                                <form type="post" class="cd-form" onclick="checkStaffPassword()"
                                                      action="/cafe.by/change_admin">
                                                    <input style='visibility:hidden' name="adminId" value="${admin.id}">
                                                    <p class="fieldset">
                                                        <label class="image-replace cd-password"
                                                               for="admin-new-password">{password_word}</label>
                                                        <input class="full-width has-padding has-border"
                                                               name="changePassword_new"
                                                               id="admin-new-password" type="password"
                                                               placeholder=${new_password_word}>
                                                        <a href="#0" class="hide-password">""</a>
                                                        <span id="admin-new-password-span"
                                                              class="cd-error-message">${mistake_password_word}</span>
                                                    </p>
                                                    <input type="submit" id="change_staff_password"
                                                           value="${send_word}">
                                                </form>
                                            </div>
                                            <ul class="actions">
                                                <li>
                                                    <a href="/cafe.by/delete_admin?adminId=${admin.id}"
                                                       class="button">${delete_word}</a>
                                                </li>
                                                <li>
                                                    <input type="button" value="${edit_word}"
                                                           id="admin-password-button-${admin.id}">
                                                </li>
                                                <script type="text/javascript">
                                                    $(document).ready(function () {
                                                        $("#admin-password-button-${admin.id}").click(function () {
                                                            $("#admin-password-${admin.id}").slideToggle("slow");
                                                            return false;
                                                        });
                                                    });
                                                </script>
                                            </ul>
                                        </c:when>
                                    </c:choose>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <h2>${found_nothing_word}</h2>
                            </c:otherwise>
                        </c:choose>
                    </article>
                    <article>
                        <h3>${add_admin_error}</h3>
                        <header class="major">
                            <h2>${add_admin_word}</h2>
                        </header>
                        <form id="admin-form" class="cd-form" method="POST" action="/cafe.by/add_admin">
                            <p class="fieldset">
                                <label class="image-replace cd-login" for="admin-login">${login_word}</label>
                                <input class="full-width has-padding has-border" name="admin_login" id="admin-login"
                                       type="text"
                                       placeholder="${login_word}">
                                <span id="admin-login-span" class="cd-error-message">${mistake_login_word}</span>
                            </p>
                            <p class="fieldset">
                                <label class="image-replace cd-password" for="admin-password">"${password_word}</label>
                                <input class="full-width has-padding has-border" name="admin_password"
                                       id="admin-password"
                                       type="password" placeholder="${password_word}">
                                <a href="#0" class="hide-password">""</a>
                                <span id="admin-password-span" class="cd-error-message">${mistake_password_word}</span>
                            </p>
                            <p class="fieldset">
                                <input class="full-width" type="submit" value=${add_word}>
                            </p>
                        </form>
                    </article>
                </div>
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
    <%@include file="/front/js/validation.js"%>
</script>

</body>
</html>
