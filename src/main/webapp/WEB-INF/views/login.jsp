<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Witaj</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href='<spring:url value="/"/>'>Mati Shop</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href='<spring:url value="/"/>'>Strona główna</a></li>
                <li><a href='<spring:url value="/login"/>'>Logowanie</a></li>
                <li><a href='<spring:url value="/registrationForm"/>'>Rejestracja</a></li>
            </ul>
        </div>
    </div>
</nav>

<section>
    <div class="jumbotron">
        <div class="container">
            <br>
            <h1> Witaj w sklepie internetowym! </h1>
            <p> Wyjatkowym i jedynym sklepie internetowym </p>
            <h2>Formularz logowania</h2>
            <br>
            <c:url var="loginUrl" value="/login"/>
            <form action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <div>
                        <p>Zly uzytkownik lub haslo!</p>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div>
                        <p>Zostales wylogowany cwoku</p>
                    </div>
                </c:if>
                <div>
                    <input type="text" id="username" name="login" placeholder="Podaj Login" required>
                </div>
                <div>
                    <input type="text" id="password-panel" name="password" placeholder="Podaj haslo" required>
                </div>
                <div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf_token}"/>
                    <input type="submit" value="Zaloguj sie!">
                </div>
            </form>
        </div>
    </div>
</section>

</body>
</html>
