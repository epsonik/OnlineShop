<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet"	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
	<script src="/resources/js/controllers.js"></script>
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
            <a class="navbar-brand" href='<spring:url value="/"/>'>Mati Shop</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href='<spring:url value="/"/>'>Strona główna</a></li>
                <li><a href='<spring:url value="/login"/>'>Logowanie</a></li>
                <li><a href='<spring:url value="/registration"/>'>Rejestracja</a></li>
            </ul>
        </div>
    </div>
</nav>
<section>
	<div class="jumbotron">
		<div class="container">
			<h1>${product.name}</h1>
		</div>
	</div>
</section>
<section class="container" ng-app="cartApp">
	<div class="row">
		<div class="col-md-5">
			<img src="<c:url value="/resource/images/${product.productId}.png"></c:url>" alt="image"  style = "width:100%"/>
		</div>

		<div class="col-md-5">
			<h3>${product.name}</h3>
			<p>${product.description}</p>
			<p>
				<strong>Identyfikator produktu: </strong><span class="label label-warning">${product.productId}</span>
			</p>
			<p>
				<strong>Producent</strong>: ${product.manufacturer}
			</p>
			<p>
				<strong>Kategoria</strong>: ${product.category}
			</p>
			<p>
				<strong>Stan</strong>: ${product.product_condition}
			</p>
			<p>
				<strong>Liczba sztuk w magazynie</strong>: ${product.unitsInStock}
			</p>
			<h4>${product.unitPrice} PLN</h4>
			<p data-ng-controller="cartCtrl">
				<a href="<spring:url value="/" />" class="btn btn-default">
					<span class="glyphicon-hand-left glyphicon"></span> Wstecz
				</a>
			</p>
		</div>
	</div>
</section>
</body>
</html>
