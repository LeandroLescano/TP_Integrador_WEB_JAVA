<%@page import="java.util.ArrayList" %>
<%@page import="Dominio.Persona" %>
<%@page import="Dominio.Provincia" %>
<%@page import="Negocio.PersonaNegocio" %>
<%@page import="Negocio.ProvinciaNegocio" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<script type="text/javascript" src="js/funciones.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<meta charset="ISO-8859-1">
<title>Gestor educativo</title>
</head>
 <!--    MODAL   -->
        <div id="ModalRegistro" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="TituloModal">Añadir profesor</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtLegajo" class="col-form-label">Legajo:</label>
                                            <input type="text" readonly class="form-control" id="txtLegajo" placeholder="1000">
                                        </div>

                                        <div class="form-group">
                                            <label for="txtMail" class="col-form-label">Mail:</label>
                                            <input type="email" aria-describedby="emailHelp" class="form-control" id="txtMail" placeholder="email@ejemplo.com" tabindex="3" >
                                        </div>
                                        <div class="form-group">
                                            <label for="txtCalle" class="col-form-label">Dirección:</label>
                                            <input type="text" class="form-control" id="txtCalle" placeholder="Calle 123" tabindex="6">
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtApellido" class="col-form-label">Apellido:</label>
                                            <input type="text" class="form-control" id="txtApellido" placeholder="Gomez" tabindex="1">
                                        </div>
                                        <div class="form-group">
                                            <label for="txtTelefono" class="col-form-label">Telefono:</label>
                                            <input type="text" class="form-control" id="txtTelefono" placeholder="12345678" tabindex="4" >
                                        </div>
                                        <div class="form-group">
                                            <label for="txtLocalidad" class="col-form-label">Localidad:</label>
                                            <input type="text" class="form-control" id="txtLocalidad" placeholder="Tigre" tabindex="7" >
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtNombre" class="col-form-label">Nombre:</label>
                                            <input type="text" class="form-control" id="txtNombre" placeholder="Juan" tabindex="2">
                                        </div>
                                        <div class="form-group">
                                            <label for="dtpFechNac" class="col-form-label">Fecha de nacimiento:</label>
                                            <input type='text' class="form-control" id='dtpFechNac' placeholder="DD/MM/YYYY" tabindex="5" />
                                        </div>
                                        <div class="form-group">
                                            <label for="txtProvincia" class="col-form-label">Provincia:</label>
                                            <!--<input type="text" class="form-control" id="txtProvincia" placeholder="Buenos Aires" tabindex="8" >-->
                                            <select id="txtProvincia" class="form-control">
								 		 	<%
		 	 		 							    ProvinciaNegocio negocioP = new ProvinciaNegocio();
		 	 		 	 	 		 			   ArrayList<Provincia> prov = negocioP.listarProvincias();
		 	 		 	 	 		 			   for (Provincia p : prov)
		 	 		 	 	 		 			   {
							 	 		 			%><option value="<%=p.getID()%>"> <%=p.getNombre()%> </option>
											   		<%
											   			}
											   		%>
								 			 </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    <div class="modal-footer">
                        <button type="button" id="btnCancelar" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" id="btnAñadir" class="btn btn-primary" disabled tabindex="9">Añadir</button>
                    </div>
                </div>
            </div>
        </div>
        <!--  FIN MODAL  -->
<body onresize="cantidadPaginas()">
<jsp:include page="NavBarAdmin.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%">
<div id="Titulo">
	<h2>Profesores</h2>
</div>
<button id="btnAgregar" class="btn btn-primary btn-add" onclick="mostrarModal()" data-toggle="modal" data-target=".bd-example-modal-lg">Añadir profesor</button>	 			 
 <table id="Gridview" class="table table-hover" style="table-layout: fixed;">
        <thead class="thead-dark">
            <tr>
                <th scope="col" class="colLegajo">Legajo</th>
                <th scope="col">Apellido y nombre</th>
                <th scope="col">E-mail</th>
                <th scope="col" class="colTelefono">Teléfono</th>
                <th scope="col" id="colAcciones">Acciones</th>
            </tr>
        </thead>
        <tbody id="grilla">
        		<%
			String listadoProfesores = null;
			if(request.getAttribute("tabla")!=null)
			{
				listadoProfesores = (String)request.getAttribute("tabla");
			}		
		%>
        <%=listadoProfesores %>
        </tbody>
    </table>
</div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var screenH = window.innerHeight;
	var cantPags;
	if(screenH < 615){
		cantPags = 4;
	}
	else if(screenH < 680){
		cantPags = 5;
	}
	else if(screenH < 740){
		cantPags = 6;
	}
	else{
		cantPags = 7;
	}
	
	CurrentItem = document.getElementById("mnProfesores");
	CurrentItem.className +=" active";
	
	$("#TituloModal").html("Añadir profesor");
	
	$('#Gridview').DataTable({
		"ordering":false,
		"bInfo": false,
		"lengthChange": false,
		"pageLength": cantPags,
		"dom":'frtip',
		"oLanguage": {
			   "sSearch": "Busqueda:",
			 },
	 	"language": {
			   "zeroRecords": "No se encontraron registros coincidentes",
				"paginate": {
				       "next": "Siguiente",
					   "previous": "Previo"
				},
	 	}
	});

	$('#myModal').on('shown.bs.modal', function () {
	    $('#myInput').trigger('focus')
	});
});

function mostrarProfesor(legajo){
	$.post("servletProfesor",{"legajo": legajo}, function(responseJson) {
		$("#txtLegajo").val(legajo);
		$("#txtNombre").val(responseJson.Nombre);
		$("#dtpFechNac").val(responseJson.FechNac);
		$("#txtMail").val(responseJson.Mail);
		$("#txtTelefono").val(responseJson.Telefono);
		$("#txtApellido").val(responseJson.Apellido);
		$("#txtCalle").val(responseJson.Domicilio.Calle);
		$("#txtProvincia").val(responseJson.Domicilio.Provincia.ID);
		$("#txtLocalidad").val(responseJson.Domicilio.Localidad.Nombre);
		$("#TituloModal").html("Profesor/a: " + responseJson.Apellido + ", " + responseJson.Nombre);
	});
	
	$("#txtLegajo").prop('readonly', true);
	$("#txtNombre").prop('readonly', true);
	$("#dtpFechNac").prop('readonly', true);
	$("#txtMail").prop('readonly', true);
	$("#txtTelefono").prop('readonly', true);
	$("#txtApellido").prop('readonly', true);
	$("#txtCalle").prop('readonly', true);
	$("#txtProvincia").prop('disabled', true);
	$("#txtLocalidad").prop('readonly', true);
 	$("#btnAñadir").prop('hidden', true);
	
	mostrarModal();
}

</script>
</html>