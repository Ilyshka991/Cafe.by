<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <section>
                <div class="posts">
                    <article>
                        <div id="reset-password-div">
                            <form method="POST" class="cd-form" id="reset-password-form"
                                  action="/cafe.by/reset_password_confirm">
                                <header class="major">
                                    <h2>${reset_password_word}</h2>
                                </header>
                                <h3>${reset_password_error}</h3>
                                <input style="visibility: hidden" value="${code}" id="main_code">
                                <p class="fieldset">
                                    <input type="text" class="code" id="code" placeholder=${code_word}>
                                    <span class="cd-error-message" id="code-span">${code_mistake}</span>
                                </p>
                                <p class="fieldset">
                                    <label class="image-replace cd-password"
                                           for="resetPassword-new">{password_word}</label>
                                    <input class="full-width has-padding has-border" id="resetPassword-new"
                                           name="resetPassword_new"
                                           type="password" placeholder=${new_password_word}>
                                    <a href="#0" class="hide-password">""</a>
                                    <span id="span-resetPassword-new"
                                          class="cd-error-message">${mistake_password_word}</span>
                                </p>
                                <p class="fieldset">
                                    <label class="image-replace cd-password"
                                           for="resetPassword-re-new">{re_password_word}</label>
                                    <input class="full-width has-padding has-border" id="resetPassword-re-new"
                                           type="password"
                                           placeholder=${re_new_password_word}>
                                    <a href="#0" id="a-resetPassword-re-new" class="hide-password">""</a>
                                    <span id="span-re-resetPassword-new"
                                          class="cd-error-message">${re_password_mistake}</span>
                                </p>
                                <input type="submit" id="reset" value=${reset_password_button}>
                            </form>
                        </div>
                    </article>
                    <article></article>
                    <article>
                        <form method="post" action="/cafe.by/index">
                            <input type="submit" onclick="" value="${home_word}">
                        </form>
                    </article>
                </div>
            </section>
        </div>
    </div>
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