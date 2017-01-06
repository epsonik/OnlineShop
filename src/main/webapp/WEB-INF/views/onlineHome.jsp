<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <br><h1> Witaj ${user}! </h1><br>
            Twoje dane osobwe:
            <br><br>
            ${contactData.firstName}
            <br>
            ${contactData.lastName}
            <br>
            ${contactData.email}
            <br><br>
            Twoje dane adresowe:
            <br>
            ${contactData.street}
            <br>
            ${contactData.city}
            <br><br>
            <h2>Produkty</h2>
            <p>Wszystkie produkty dostępne w naszym sklepie</p>
        </div>
    </div>
</section>

<section class="container">
    <div class="row">
        <c:forEach items="${products}" var="product">
            <div class="col-sm-6 col-md-3" style="padding-bottom: 15px">
                <div class="thumbnail">
                    <img src="<c:url value="/resource/images/${product.productId}.png">
                         </c:url>" alt="image" style= "width:100%"/>
                    <div class="caption">
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <p>${product.unitPrice} PLN</p>
                        <p>Liczba sztuk w magazynie: ${product.unitsInStock}</p>
                        <p>
                            <a
                                    href=" <spring:url value="/products/onlineProduct?id=${product.productId}" /> "
                                    class="btn btn-primary"> <span class="glyphicon-info-sign glyphicon" /></span> Szczegóły
                            </a>
                        </p>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>

</body>
</html>