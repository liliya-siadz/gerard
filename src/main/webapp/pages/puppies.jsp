<%--
  Created by IntelliJ IDEA.
  User: l.sidelnikova
  Date: 7/19/2021
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="fragment/include.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="page.puppies.title"/></title>
</head>
<body style="background-image: url(${applicationPath}/img/background.jpg);">
<%@include file="fragment/dynamic-headering.jsp" %>

<%--1 MAKE REQUEST--%>

<div style="display:inline-block;">
    <c:set var="puppies" value="${allPuppies}" scope="request"/>
    <c:forEach items="${puppies}" var="puppy">
        <c:set var="avatar" value="${puppy.avatarPhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${avatar}"
             alt="?"
             width="512"/>
        <c:set var="pedigree" value="${puppy.pedigreePhotoPath}"/>
        <img class="img"
             src="${applicationPath}/${pedigree}"
             alt="?"
             width="512"/>
        <div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.sex"/> </b>
                    <c:out value="${puppy.dogSex}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.nickname"/> </b>
                    <c:out value="${puppy.nickname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.fullname"/> </b>
                    <c:out value="${puppy.fullname}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.birthday"/> </b>
                    <c:out value="${puppy.birthday}"/>
                </h3>
            </div>
            <div style="display:block;">
                <h3><b><fmt:message key="page.dogs.view.title.description"/> </b>
                    <c:out value="${puppy.description}"/><c:if test="${empty puppy.description}">&#10006;</c:if>
                </h3>
            </div>
            <h3><b>
            <a href="${pageContext.request.contextPath}/make_request?dogId=${puppy.id}"
               role="button btn-primary"
               style="background-color: greenyellow;"
               class="btn btn-xs">
               <fmt:message key="page.puppies.button.make_request.name"/>
            </a>
            </b>
            </h3>
        </div>
    </c:forEach>
</div>
<%@ include file="fragment/footers/footer.jsp" %>
</body>
</html>
