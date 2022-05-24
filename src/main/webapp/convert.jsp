<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*, com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" %>

<jsp:useBean id="currencies" class="com.epam.rd.jsp.currencies.CurrenciesOfCurrentTestCase" scope="request"/>

<html>
    <head>
        <title>Convert</title>
    </head>
    <body>
        <H1>
            Converting ${param.from} to ${param.to}
        </H1>
       <c:set var="result" scope="session" value="${currencies.convert(param.amount, param.from, param.to) }" />
       <p> ${param.amount}  ${param.from} = ${result} ${param.to}
        </p>

    </body>
</html>
