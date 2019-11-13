<%-- 
    Document   : region-maintener
    Created on : 16-08-2019, 17:13:23
    Author     : ccv
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COMUNA MAINTENER</title>
        <jsp:include page="/jsp/template/header.jsp"/>
    </head>
    <body>       
        <jsp:include page="/jsp/template/menu.jsp"/>
        <div class="container">        
            <form id="frm-login" action="../CreateComunaServlet" method="POST">
                <div style="margin-top: 2.5%;margin-left: 15%; max-width: 600px">
                    <h1>COMUNA REGIÓN</h1>
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
                            <select name="cmb-regiones" class="form-control form-control-sm">
                                <c:forEach var="region" items="${regiones}" varStatus="i">
                                    <option value="${region.codigo}">${region.nombre}</option>

                                </c:forEach>
                            </select>
                        </div>
                    </div>                      
                    <div class="row">
                        <div class="form-group col-sm-8">                        
                        </div>                 
                        <div class="form-group col-sm-4">                        
                            <button id="bnt-calcular" type="submit" 
                                    class="btn btn-success col-sm-12">CREATE</button>
                        </div>
                    </div>
                    <div class="row">
                        <c:if test="${not empty sessionScope.errorCreateComuna}">
                            <div id="msg-error" class="form-group col-sm-12 btn btn-danger" style="font-size: 10px !important">
                                ${sessionScope.errorCreateComuna}
                            </div>                    
                        </c:if>
                    </div>                         
                </div>
                <div style="margin-top: 2.5%;margin-left: 8%;max-width:800px">                    
                    <div class="row">
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">ID</th>
                                    <th scope="col">NOMBRE</th>
                                    <th scope="col">DESCRIPCION</th>
                                    <th scope="col">REGIÓN</th>                                    
                                </tr>
                            </thead>                        
                            <c:forEach var="comuna" items="${comunas}" varStatus="i">
                                <tr>
                                    <td>
                                        <input type="text" class="form-control" id="txt-id-${i.count}"
                                               placeholder="ID" value="${comuna.id}" disabled="true"
                                               name="id" style="font-size: 8px !important">                                    
                                    </td>
                                    <td>
                                        <input type="text" class="form-control" id="txt-nombre-${i.count}"
                                               placeholder="ID" value="${comuna.nombre}" disabled="true"
                                               name="id" style="font-size: 8px !important">                                        
                                    </td> 
                                    <td>
                                        <input type="text" class="form-control" id="txt-descripcion-${i.count}"
                                               placeholder="ID" value="${comuna.descripcion}" disabled="true"
                                               name="id" style="font-size: 8px !important">                                      
                                    </td>
                                    <td>
                                        <select id="cmb-regiones-${i.count}" name="cmb-regiones-${i.count}" class="form-control form-control-sm" disabled="true" style="font-size: 8px !important">
                                            <c:forEach var="region" items="${regiones}" varStatus="j">
                                                <c:if test="${region.id == comuna.region.id}">
                                                    <option value="${region.id}" selected="true">${region.nombre}</option>
                                                </c:if>
                                                <c:if test="${region.id != comuna.region.id}">
                                                    <option value="${region.id}">${region.nombre}</option>
                                                </c:if>                        
                                            </c:forEach>
                                        </select>                                        
                                    </td>                                    
                                    <td>
                                        <button id="bnt-edit-${i.count}" type="button" 
                                                onclick="edit('${i.count}')"
                                                class="btn btn-warning col-sm-5">EDIT</button>    
                                        <button id="bnt-delete-${i.count}" type="button" 
                                                onclick="deleteRow('${i.count}')"                                               
                                                class="btn btn-danger col-sm-5">DELETE</button>  
                                        <button id="bnt-save-${i.count}" type="button"
                                                onclick="saveRow('${i.count}')"                                                
                                                class="btn btn-success col-sm-5" hidden="true">SAVE</button>    
                                        <button id="bnt-cancel-${i.count}" type="button"
                                                onclick="cancelRow('${i.count}')"                                                
                                                class="btn btn-danger col-sm-5" hidden="true">CANCEL</button>                                      
                                    </td>                                   
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>

            </form>
        </div>
    </body>
    <script>
        /**
         * Funcion encargada de editar la fila
         * @param {type} row
         * @returns {undefined}
         */
        function edit(row) {


            editRow(row, false, false, false, true, true,
                    false, false);
        }

        /**
         * Metodo encargado de cancelar la edicion de una fila
         * @param {type} row
         * @returns {undefined}
         */
        function cancelRow(row) {
            editRow(row, true, true, true, false, false,
                    true, true);
        }

        /*
         * Funcion que modifica componentes de una fila de la tabla con listado de regiones
         * @param {type} txtNombre
         * @param {type} txtDescripcion
         * @param {type} btnEdit
         * @param {type} btnDelete
         * @param {type} btnSave
         * @param {type} btnCancel
         * @param {type} nombreDisabled
         * @param {type} descripcionDisbled
         * @param {type} editDisabled
         * @param {type} deleteDisabled
         * @param {type} saveDisabled
         * @param {type} cancelDisabled
         * @returns {undefined}
         */
        function editRow(row, nombreDisabled, descripcionDisabled, regionesDisabled, editDisabled, deleteDisabled,
                saveDisabled, cancelDisabled) {

            var txtNombre = "txt-nombre-" + row;
            var txtDescripcion = "txt-descripcion-" + row;
            var cmbRegiones = "cmb-regiones-" + row;            
            var btnEdit = 'bnt-edit-' + row;
            var btnDelete = 'bnt-delete-' + row;
            var btnSave = 'bnt-save-' + row;
            var btnCancel = 'bnt-cancel-' + row;

            document.getElementById(txtNombre).disabled = nombreDisabled;
            document.getElementById(txtDescripcion).disabled = descripcionDisabled;
            document.getElementById(cmbRegiones).disabled = regionesDisabled;            
            document.getElementById(btnEdit).hidden = editDisabled;
            document.getElementById(btnDelete).hidden = deleteDisabled;
            document.getElementById(btnSave).hidden = saveDisabled;
            document.getElementById(btnCancel).hidden = cancelDisabled;
        }

        /** Funcion encargada de modificar una region
         * 
         * @param {type} row
         * @returns {undefined}
         */
        function saveRow(row) {

            var id = document.getElementById("txt-id-" + row).value;
            var nombre = document.getElementById("txt-nombre-" + row).value;
            var descripcion = document.getElementById("txt-descripcion-" + row).value;
            var idRegion = document.getElementById("cmb-regiones-" + row).value;
            cancelRow(row);
            const Http = new XMLHttpRequest();
            const url = '../UpdateComunaServlet?id=' + id + '&nombre=' + nombre
                    + '&descripcion=' + descripcion + '&idRegion=' + idRegion;
            Http.open('GET', url);
            Http.send();
        }

        function deleteRow(row) {
            var id = document.getElementById("txt-id-" + row).value;
            cancelRow(row);
            $.ajax({
                url: '../DeleteComunaServlet?id=' + id,
                method: 'GET',
                success: window.location.reload()
            });

        }
    </script>
</html>
