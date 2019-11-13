<%-- 
    Document   : menu
    Created on : 03-sep-2019, 19:34:46
    Author     : CETECOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-sm bg-secondary navbar-dark">
    <ul class="navbar-nav">
        <li class="nav-item active">
            <a class="nav-link" href="home.jsp">Active</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="region.jsp">Region</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
        </li>
        <li class="nav-item">
            <a class="nav-link disabled" href="#">Disabled</a>
        </li>
    </ul>
    <form id="frm-login" action="" method="POST">
        <c:if test="${not empty sessionScope.user}">
            <i class="fas fa-user"></i>
                ${sessionScope.user}
            </c:if>
    </form>
</nav>