<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">

<script	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<script src="/resources/js/controllers.js"></script>

<title>Koszyk</title>
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
			<a class="navbar-brand" href='<spring:url value="/onlineHome"/>'>Mati Shop</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href='<spring:url value="/onlineHome"/>'>Strona główna</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
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
			<h1>Koszyk</h1>
			<p>Produkty w Twoim koszyku</p>
		</div>
	</div>
</section>
<section class="container" ng-app="cartApp">
    <div data-ng-controller="cartCtrl" data-ng-init="initCardId('${cartId}')">
        <div>
            <a class="btn btn-danger pull-left" data-ng-click="clearCart()"> <span class="glyphicon glyphicon-remove-sign"></span> Wyczyść koszyk </a>
            <a href="#" class="btn btn-success pull-right"> <span class="glyphicon-shopping-cart glyphicon"></span> Kupuję </a>
        </div>
        <table class="table table-hover">
            <tr>
                <th>Produkt</th>
                <th>Cena za sztukę</th>
                <th>Liczba sztuk</th>
                <th>Cena</th>
                <th>Akcja</th>
            </tr>
            <tr data-ng-repeat="item in cart.cartItems">
                <td>{{item.product.productId}}-{{item.product.name}}</td>
                <td>{{item.product.unitPrice}} PLN</td>
                <td>{{item.quantity}}</td>
                <td>{{item.totalPrice}} PLN</td>
                <td><a href="#" class="label label-danger" data-ng-click="removeFromCart(item.product.productId)">
                    <span class="glyphicon glyphicon-remove" /></span> Usuń </a>
                </td>
            </tr>
				<tr>
					<th></th>
					<th></th>
					<th>Łączna cena</th>
					<th>{{cart.grandTotal}} PLN</th>
					<th></th>
				</tr>
			</table>
			<a href="<spring:url value="/onlineHome" />" class="btn btn-default">
						<span class="glyphicon-hand-left glyphicon"></span> Wróć do zakupów </a>
		</div>
	</section>
</body>
</html>