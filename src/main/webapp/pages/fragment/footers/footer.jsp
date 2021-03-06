<%--
  Created by IntelliJ IDEA.
  User: sidel
  Date: 8/7/2021
  Time: 3:19 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head></head>
<body>
<div class="container-fluid panel-footer" style="{ font-size: 5px; }">
    <div class="row">
        <small>
            <div class="col-md-1">
                <a href="${pageContext.request.contextPath}/home">
            <fmt:message key="footer.link.home.name"/>
                </a>
                <p><small><fmt:message key="cookie.notification" /></small></p>
            </div>
        </small>
        <small>
            <div class="col-md-3">
                <fmt:message key="footer.link.coordinates.name"/>
                : &#9737;
                <b><osd:output-site-description
                        applicationUrl="${initParam.application_url}"
                        elementTagName="cordinates" />
                </b>
                &#10711;
                <c:out value="${currentDate}"/>
            </div>
        </small>
        <small>
            <div class="col-md-3">
            <fmt:message key="footer.link.address.name"/>
                : &#8962;
            <b>
                <fmt:message key="kennel.address"/>
            </b>
            </div>
        </small>
        <small>
            <div class="col-md-2">
            <fmt:message key="footer.link.phone.name"/>
                : &#9742;
            <b><osd:output-site-description
                    applicationUrl="${initParam.application_url}"
                    elementTagName="phone" />
            </b>
            </div>
        </small>
        <small>
            <div class="col-md-1">
                <form method="GET"
                      action="${applicationPath}${controllerUrl}">
                    <button type="submit"
                            name="command"
                            class="btn btn-link"
                            value="GO_TO_LOGIN_PAGE">
                        <fmt:message key="page.login.header"/>
                    </button>
                </form>
            </div>
        </small>
        <small>
            <div class="col-md-2">
            <fmt:message key="footer.link.email.name"/>
                : &#9993;
            <b><osd:output-site-description
                    applicationUrl="${initParam.application_url}"
                    elementTagName="email" />
            </b>
            </div>
        </small>
    </div>
</div>
</body>
</html>
