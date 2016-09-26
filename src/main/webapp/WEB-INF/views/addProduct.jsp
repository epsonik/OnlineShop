<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Produkty</title>
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
            <a class="navbar-brand" href='<spring:url value="/products/add"/>'>Mati Shop</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href='<spring:url value="/products/add"/>'>Strona główna</a></li>
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
            <br><h3>Dodaj produkt</h3>
        </div>
    </div>
</section>
<section class="container">
    <form action="${pageContext.request.contextPath}/products/add" modelAttribute="newProduct" method="post"  class="form-horizontal">
        <fieldset>
            <legend>Dodaj nowy produkt</legend>
            <div class="form-group">
                <label class="control-label col-lg-2 col-lg-2" for="productId"><spring:message code="addProdcut.form.productId.label"/></label>
                <div class="col-lg-10">
                    <input id="productId" path="productId" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="name">Nazwa</label>
                <div class="col-lg-10">
                    <input id="name" path="name" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitPrice">Cena</label>
                <div class="col-lg-10">
                    <div class="form:input-prepend">
                        <input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="description">Opis</label>
                <div class="col-lg-10">
                    <input type="text" id="description" path="description" rows = "2"></inputtextarea>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="manufacturer">Producent</label>
                <div class="col-lg-10">
                    <input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="category">Kategoria</label>
                <div class="col-lg-10">
                    <input id="category" path="category" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock">Liczba sztuk w magazynie </label>
                <div class="col-lg-10">
                    <input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="condition">Stan</label>
                <div class="col-lg-10">
                    <input type="radio" path="condition" value="New" /> Nowy
                    <radiobutton path="condition" value="Old" /> Używany
                    <radiobutton path="condition" value="Refurbished" /> Odnowiony
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="productImage"`> <spring:message
                        code="addProdcut.form.productImage.label" />
                </label>
                <div class="col-lg-10">
                    <input id="productImage" path="productImage" type="file"
                                class="form:input-large" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value ="Dodaj"/>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </div>
            </div>
        </fieldset>
    </form>
</section>
</body>
</html>
