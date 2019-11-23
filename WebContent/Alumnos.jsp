<%@page import="java.util.ArrayList" %>
<%@page import="Dominio.Persona" %>
<%@page import="Dominio.Provincia" %>
<%@page import="Negocio.PersonaNegocio" %>
<%@page import="Negocio.ProvinciaNegocio" %>
<%@page import="Dominio.Localidad" %>
<%@page import="Negocio.LocalidadNegocio" %>
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

<link href="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/js/bootstrap-datepicker.min.js"></script>
<script src="https://unpkg.com/bootstrap-datepicker@1.9.0/dist/locales/bootstrap-datepicker.es.min.js" charset="UTF-8"></script>
<meta charset="ISO-8859-1">
<title>Gestor educativo</title>
</head>
<%
session = request.getSession();
if(session.getAttribute("MailUsuario") == null) {
	response.sendRedirect("./Inicio.jsp");
}
else if(request.getAttribute("tabla") ==null){
	response.sendRedirect("./servletAlumno");
}

%>
 <!--    MODAL   -->
        <div id="ModalRegistro" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true" onchange="habilitarAgregar()" onkeyup="habilitarAgregar()">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="TituloModal">Añadir alumno</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="FormAgregar" method="post" action="servletAlumno">
                    <div class="modal-body">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtLegajo" class="col-form-label">Legajo:</label>
                                            <input type="text" disabled class="form-control" name="txtLegajo" id="txtLegajo" placeholder="1000">
                                        </div>

                                        <div class="form-group">
                                            <label for="txtMail" class="col-form-label">Mail:</label>
                                            <input type="email" aria-describedby="emailHelp" class="form-control" name="txtMail" required id="txtMail" placeholder="email@ejemplo.com" tabindex="3" onkeyup="validarEmail()" onblur="sacarFoco(this.id)" >
                                        </div>
                                        <div class="form-group">
                                            <label for="txtProvincia" class="col-form-label">Provincia:</label>
                                            <select id="txtProvincia" name="txtProvincia" class="form-control" tabindex="6">
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
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtApellido" class="col-form-label">Apellido:</label>
                                            <input type="text" class="form-control" name="txtApellido" id="txtApellido" placeholder="Gomez" tabindex="1" onkeyup="validarVacio(this.id)" onblur="sacarFoco(this.id)" onfocus="enFoco(this.id)">
                                        </div>
                                        <div class="form-group">
                                            <label for="txtTelefono" class="col-form-label">Telefono:</label>
                                            <input type="text" class="form-control" name="txtTelefono" id="txtTelefono" placeholder="12345678" tabindex="4" onkeyup="validarNumero()" onblur="sacarFoco(this.id)" onfocus="enFoco(this.id)">
                                        </div>
                                        <div class="form-group">
                                            <label for="txtLocalidad" class="col-form-label">Localidad:</label>
                                            <select id="txtLocalidad" name="txtLocalidad" class="form-control" tabindex="7">
                                            <%
		 	 		 							    LocalidadNegocio negocioL = new LocalidadNegocio();
		 	 		 	 	 		 			   ArrayList<Localidad> loc = negocioL.listarLocalidades();
		 	 		 	 	 		 			   for (Localidad l : loc)
		 	 		 	 	 		 			   {
							 	 		 			%><option value="<%=l.getID()%>"> <%=l.getNombre()%> </option>
											   		<%
		 	 		 	 	 		 			   }
											   		%>
								 			 </select>
                                        </div>
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="form-group">
                                            <label for="txtNombre" class="col-form-label">Nombre:</label>
                                            <input type="text" class="form-control" name="txtNombre" id="txtNombre" placeholder="Juan" tabindex="2" onkeyup="validarVacio(this.id)" onblur="sacarFoco(this.id)" onfocus="enFoco(this.id)">
                                        </div>
                                        <div class="form-group">
                                            <label for="dtpFechNac" class="col-form-label">Fecha de nacimiento:</label>
                                            <input type='text' class="form-control" name="dtpFechNac" id='dtpFechNac' placeholder="DD/MM/YYYY" tabindex="5" onkeyup="validarVacio(this.id)" onchange="validarVacio(this.id)" onblur="sacarFoco(this.id)" onfocus="enFoco(this.id)" />
                                        </div>
                                        <div class="form-group">
                                            <label for="txtCalle" class="col-form-label">Dirección:</label>
                                            <input type="text" class="form-control" name="txtCalle" id="txtCalle" placeholder="Calle 123" tabindex="8" onkeyup="validarVacio(this.id)" onblur="sacarFoco(this.id)" onfocus="enFoco(this.id)">
                                        </div>
                                    </div>
                                </div>
                            </div>
                    <div class="modal-footer">
                        <button type="button" id="btnCancelar" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="submit" id="btnAgregar" name="btnAgregar" class="btn btn-primary" tabindex="9" disabled>Añadir</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!--  FIN MODAL  -->
<body onresize="cantidadPaginas()">
<jsp:include page="ModalEliminarAlumno.html"></jsp:include>
<jsp:include page="NavBarAdmin.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%">
<div id="Titulo">
	<h2>Alumnos</h2>
</div>
<button id="btnAgregar" class="btn btn-primary btn-add" onclick="nuevoAlumno()" data-toggle="modal" data-target=".bd-example-modal-lg">Añadir alumno</button>	  			 
 <table id="Gridview" class="table table-hover">
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
			String listadoAlumnos = null;
			if(request.getAttribute("tabla")!=null)
			{
				listadoAlumnos = (String)request.getAttribute("tabla");
			}		
		%>
        <%=listadoAlumnos %>
        </tbody>
    </table>
</div>
</div>
<jsp:include page="ToastResultado.html"></jsp:include>
</body>
<script type="text/javascript">
<%

String mailUsuario = null;
if(request.getAttribute("Mail")!=null)
{
	mailUsuario = (String)request.getAttribute("Mail");
    %>$("#MailUsuario").html("<%=mailUsuario%>");<%	
}		

if(request.getAttribute("ResultToast")!=null)
{
	String Resultado = (String)request.getAttribute("ResultToast");
	%>mostrarToast("<%=Resultado%>")<%
	request.setAttribute("ResultToast", null);
}
%>

$(function () {
	var hoy = new Date();
	var dd = String(hoy.getDate()).padStart(2, '0');
	var mm = String(hoy.getMonth() + 1).padStart(2, '0');
	var yyyy = hoy.getFullYear();
	
	hoy = dd + '/' + mm + '/' + yyyy;
	
	$('#dtpFechNac').datepicker({
	    language: "es",
	    endDate: hoy
	});
});

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
	
	CurrentItem = document.getElementById("mnAlumnos");
	CurrentItem.className +=" active";

	$("#TituloModal").html("Añadir alumno");
	
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
	

	$("#ModalRegistro").on("hidden.bs.modal", function(){
		listarLocalidadesUsadas();
	});
	

	$("#ModalRegistro").on('shown', function(){
		$("#txtLocalidad option[value =' "+ $("#SelectLocalidad").val() +"']").prop("selected", true);
		
	}); 
	
	
	$("#txtProvincia").change(function() {
		listarLocalidades($("#txtProvincia").val());
		});

	$("#ModalRegistro").on("hidden.bs.modal", function(){
		listarLocalidadesUsadas();
	});
	
	$("#txtLocalidad").focus(function(){
		var ValueActual = $("#txtLocalidad").val();
		listarLocalidades($("#txtProvincia").val());
		$("#txtLocalidad").val(ValueActual);
	});
});

function mostrarToast(R){
	if(R == "Eliminado"){
		$("#toastTitle").html("Eliminado");
		$("#toastMsj").html("Se ha eliminado a un alumno exitosamente.");
	}
	else if (R == "Agregado"){
		$("#toastTitle").html("Nuevo alumno");
		$("#toastMsj").html("Se ha agregado a un alumno exitosamente.");
	}
	else if (R == "ErrorE"){
		$("#toastTitle").html("Error");
		$("#toastMsj").html("Ha ocurrido un error al momento de eliminar a un alumno.");
	}
	else{
		$("#toastTitle").html("Error");
		$("#toastMsj").html("Ha ocurrido un error al momento de agregar a un alumno.");
	}
	$(".toast").toast('show');
}
</script>
</html>