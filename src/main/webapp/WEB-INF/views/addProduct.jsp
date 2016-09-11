<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>
        Produkty
    </title>

</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>
                Produkty
                <p>
                    Dodaj produkty
                </p>
            </h1>
        </div>
    </div>
</section>
<section class="container">
    <form:form modelAttribute="newProduct" class="form-horizontal">
        <fieldset>
            <legend>
                Dodaj nowy prosuktc
            </legend>
            <div class="form-group">
                <label class="conrol-label col-lg-2 col-lg-2" for ="productId"> Id produktu</label>

            <div class="col-lg-10">
                <form:input path="productId" id="productId" type="text" class="form:input-large"/>

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
                <label class="control-label col-lg-2" for="discontinued">Wycofany</label>
                <div class="col-lg-10">
                    <form:checkbox path="discontinued" id="discontinued"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-lg-2" for="condition"> Stan</label>
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
