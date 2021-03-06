<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${applicationPath}/css/custom.css">
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <%@ include file="../logo.jsp" %>
        <%@ include file="../switch_locale_panel.jsp" %>
        <a href="${pageContext.request.contextPath}/home"
           role="button"
           class="btn btn-xs navbar-btn headerLink">
            <fmt:message key="page.home.header"/>
        </a>
        <div class="btn-group btn-group-xs" role="header">
            <form method="GET"
                  action="${applicationPath}${controllerUrl}">
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn headerButton"
                        value="GO_TO_DOGS_PAGE">
                    <fmt:message key="page.dogs.header"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn headerButton"
                        value="GO_TO_PUPPIES_PAGE">
                    <fmt:message key="page.puppies.header"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn headerButton"
                        value="GO_TO_PHOTOS_PAGE">
                    <fmt:message key="page.photos.header"/>
                </button>
                <button type="submit"
                        name="command"
                        class="btn btn-xs navbar-btn headerButton"
                        value="GO_TO_MAKE_REQUEST_PAGE">
                    <fmt:message key="page.make_request.header"/>
                </button>
            </form>
        </div>
    </div>
</nav>
</body>
</html>
