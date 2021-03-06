<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/4/2021
  Time: 4:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"  %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="osd" uri="output-site-description" %>
<%@ taglib prefix="dla" uri="define-language-attributes" %>
<c:set var="applicationPath" value="${pageContext.request.contextPath}" scope="application"/>
<c:set var="controllerUrl" value="${initParam.controller_url_pattern}" scope="application"/>
<c:set var="currentPageUrl" value="${pageContext.request.servletPath}" scope="application"/>
<c:set var="currentFullUrl" value="${pageContext.request.requestURL}"/>
<dla:define-language-attributes/>
<fmt:setLocale value="${sessionScope.locale}" scope="application"/>
<fmt:setBundle basename="${sessionScope.bundle}" scope="application"/>
<c:set var="role" value="${sessionScope.role}" scope="session"/>
<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate type="date" pattern="yyyy" value="${date}" var="currentDate"/>
<!DOCTYPE html>
<html dir="ltr">
<head>
    <%@include file="validation_variables.jsp"%>
    <link rel="icon" type="image/x-icon" href="${applicationPath}/favicon.ico">
    <link rel="stylesheet" href="${applicationPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${applicationPath}/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${applicationPath}/css/custom.css">
    <meta name="description" content="Kennel presentation for clients"/>
    <meta name="keywords"
          content="dogEntity, leonberger, Gerard, leon, kennel, собака, питомник, Жерард, леонбергер,сабака, пітомнік, Жерард, леонбергер"/>
    <meta name="author" content="Liliya Siadzelnikava"/>
</head>
<body>
<script src="${applicationPath}/js/jquery-3.1.0.min.js"></script>
<script src="${applicationPath}/js/bootstrap.min.js"></script>
<script src="${applicationPath}/js/jquery-3.1.0.min.js" type="text/javascript"></script>
</body>
</html>
