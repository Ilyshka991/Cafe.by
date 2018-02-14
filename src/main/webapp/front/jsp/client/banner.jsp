<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@include file="/front/html/allBundle.html" %>
</head>
<!-- Banner -->
<section id="banner">
    <div class="content">
        <header>
            <h1>
                <br>${welcome_word}<br/>
                ${user.name}, ${cafename_with_other_information_word}
            </h1>
            <p>${cafe_description_word}</p>
        </header>
        <p>${description_under_welcome_word}</p>
    </div>
    <span class="image object"><img src="/images/other/canteen.jpg" alt=""/></span>
</section>