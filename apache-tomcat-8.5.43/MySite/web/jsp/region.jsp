<%-- 
    Document   : region
    Created on : 03-sep-2019, 20:27:30
    Author     : CETECOM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGION</title>
        <jsp:include page="/jsp/template/header.jsp"/>
    </head>
    <body>
        <jsp:include page="/jsp/template/menu.jsp"/>
        <h1 class="text-center">REGION</h1>

        <form id="frm-login" action="../RegionServlet" method="POST">
            <div class="container" style="margin-top: 50px; max-width: 500px">
                <div class="row">
                    <div class="form-group col-sm-12">
                        <input type="text" class="form-control" id="txt-id" placeholder="ID"
                               name="id">
                    </div>                
                </div>
                <div class="row">
                    <div class="form-group col-sm-12">
                        <input type="text" class="form-control" id="txt-nombre" placeholder="NOMBRE"
                               name="nombre">
                    </div>                
                </div>
                <div class="row">
                    <div class="form-group col-sm-12">
                        <input type="text" class="form-control" id="txt-descripcion" placeholder="DESCRIPCION"
                               name="descripcion">
                    </div>                
                </div>
                <div class="row">
                    <div class="form-group col-sm-12">                        
                    </div>
                    <div class="form-group col-sm-8">
                        <button id="btn-crear" type="submit" class="btn btn-success col-sm-12">CREAR</button>
                    </div>
                </div>
                <div class="row">
                    <table class="table table-dark">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">NOMBRE</th>
                                <th scope="col">DESCRIPCION</th>
                                <th scope="col"></th>
                            </tr>
                        </thead>
                        <c:forEach var="region" items="${regiones}">
                            <tbody>
                                <tr>
                                    <td>
                                        <p>${region.id}</p>
                                    </td>
                                    <td>
                                        <p>${region.nombre}</p>
                                    </td>
                                    <td>
                                        <p>${region.descripcion}</p>
                                    </td>
                                    <td>
                                        <button id="bnt-delete"${i.count} type="button"
                                                onclick="deleteRow('${i.count}')" 
                                                class="fas fa-trash btn btn-danger"></button>
                                    </td>
                                </tr>
                            <tbody>
                            </c:forEach>
                    </table>
                </div>
            </div>
        </form>
    </body>
    <script>
        function deleteRow(row){
            
            var id = document.getElementById("txt-id-"+row).value;
            $ajax
        }
    </script>
</html>