<%-- 
    Document   : menu.jsp
    Created on : 16-08-2019, 15:33:20
    Author     : ccv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-light navbar-light">
    <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
            <a class="nav-link" href="../CreateRegionServlet">REGION MAINTENER</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="../CreateComunaServlet">COMUNA MAINTENER</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="client-maintener.jsp">CLIENT MAINTENER</a>
        </li>             
    </ul>
    <form id="frm-login" action="" method="POST">
        <c:if test="${not empty sessionScope.user}">
            <i class="fas fa-user">
                ${sessionScope.user}
            </i>                   
        </c:if>                
    </form>   
</nav>
