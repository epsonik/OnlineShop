<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

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
            <a class="navbar-brand" href='<spring:url value="/onlineAdminHome"/>'>Mati Shop</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href='<spring:url value="/onlineAdminHome"/>'>Strona główna</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>Witaj: <strong>${user}</strong></li>
                <li>
                    <form action="${pageContext.request.contextPath}/logout" method="post">
                        <input type="submit" value="Wyloguj sie"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<section>
    <div class="jumbotron">
        <div class="container">
            <br><h1> Witaj Administratorze! </h1>
            <h3>Produkty
                <p> Dodaj produkty </p>
            </h3>
        </div>
    </div>
</section>

<section class="container">
    <form:form modelAttribute="newProduct" class="form-horizontal">
        <fieldset>
            <legend>
                Dodaj nowy produkt
            </legend>
            <div class="form-group">
                <label class="conrol-label col-lg-2 col-lg-2" for ="productId">Id produktu</label>

                <div class="col-lg-10">
                    <form:input path="productId" id="productId" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="conrol-label col-lg-2 col-lg-2" for ="unitPrice">Cena jednostkowa</label>

                <div class="col-lg-10">
                    <form:input path="unitPrice" id="unitPrice" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="conrol-label col-lg-2 col-lg-2" for ="manufacturer">Producent</label>

                <div class="col-lg-10">
                    <form:input path="manufacturer" id="manufacturer" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="conrol-label col-lg-2 col-lg-2" for ="category">Kategoria</label>

                <div class="col-lg-10">
                    <form:input path="category" id="category" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="conrol-label col-lg-2 col-lg-2" for ="UnitsInStock">Ilosc w magazynie</label>

                <div class="col-lg-10">
                    <form:input path="UnitsInStock" id="UnitsInStock" type="text" class="form:input-large"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="description">
                    Opis
                </label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="description" rows="2"/>
                </div>
            </div>

            <div class="form-group">
                    <%--@declare id="condition"--%><label class="control-label col-lg-2" for="condition">Stan</label>
                <div class="col=lg-10">
                    <form:radiobutton path="condition" value="New"/> Nowy
                    <form:radiobutton path="condition" value="Old"/> Uzywany
                    <form:radiobutton path="condition" value="Refurbsihed"/> Odnowiony
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="Dodaj"/>
                </div>
            </div>
        </fieldset>
    </form:form>
</section>

</body>
</html>