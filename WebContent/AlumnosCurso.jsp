<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="Dominio.Persona" %>
<%@page import="Negocio.PersonaNegocio" %>
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
<%
session = request.getSession();
if(session.getAttribute("IDProfesor") == null) {
	response.sendRedirect("./Inicio.jsp");
}
else if(request.getAttribute("tablaAlumnosCurso")==null){
	response.sendRedirect("./servletMisCursos");
}

%>
</head>
<body onresize="cantidadPaginas()">
<jsp:include page="NavBarProfesor.html"></jsp:include>
<!--<div style="height: 85vh">-->
<div class="container" style="height: 100%">
<div id="Titulo">
	<span><a id="btnVolver" href="./servletMisCursos" class="btn btn-primary" style="float: right;">Volver a Mis cursos</a></span>
	<h2 id="TitleCurso">Curso X - Alumnos </h2>
</div>     
<form id="FormNotas" method="post" action="servletAlumnosCurso">
<jsp:include page="ModalCargarNotas.html"></jsp:include>
<!--	<input type="submit" class="btn btn-primary" value="Cargar notas" id="btnCargar" name="btnCargar" style="float: right; margin-bottom: 5px;">-->
		<button type="button" data-toggle="modal" class="btn btn-primary" data-target="#ModalNotas" id="btnCargar" name="btnCargar" style="float: right; margin-bottom: 5px;">Cargar notas</button>
	<table id="GridAlumnos" class="table table-hover">
	        <thead class="thead-dark">
	            <tr>
	                <th scope="col" class="colLegajo">Legajo</th>
	                <th scope="col" class="colApeNom">Apellido y nombre</th>
	                <th scope="col" class="colNota">Par. 1</th>
	                <th scope="col" class="colNota">Rec. 1</th>
	                <th scope="col" class="colNota">Par. 2</th>
	                <th scope="col" class="colNota">Rec. 2</th>
	                <th scope="col" class="colNotaF">Nota final</th>
	                <th scope="col" class="colEstado">Estado</th>
	            </tr>
	        </thead>
	        <tbody id="grilla">
        		<%
			String listadoAlumnos = null;
			if(request.getAttribute("tablaAlumnosCurso")!=null)
			{
				listadoAlumnos = (String)request.getAttribute("tablaAlumnosCurso");
			}		
				%>
		        <%=listadoAlumnos %>
	        </tbody>
	    </table>
  </form>
</div>
<!-- </div> -->
<jsp:include page="ToastResultado.html"></jsp:include>
</body>
<script type="text/javascript">
<%
String nombreProfesor = null;
String cursoActual = null;
if(request.getAttribute("NombreP")!=null)
{
	nombreProfesor = (String)request.getAttribute("NombreP");
	cursoActual = request.getAttribute("CursoActual").toString();
	%> $("#NombreProfesor").html("<%=nombreProfesor%>");<%
	   %>$("#TitleCurso").html("Curso <%=cursoActual%> - Alumnos");
	<%
	
}	

if(request.getAttribute("ResultToast")!=null)
{
	String Resultado = (String)request.getAttribute("ResultToast");
	%>mostrarToast("<%=Resultado%>")<%
	request.setAttribute("ResultToast", null);
}
%>

$(document).ready(function(){
	$("#TituloModal").html("Añadir alumno");
	
	$('#GridAlumnos').DataTable({
		"ordering":false,
		"bInfo": false,
		"lengthChange": false,
		"pageLength": -1,
		"dom":'frti',
		"oLanguage": {
			   "sSearch": "Busqueda:",
			 },
	 	"language": {
			   "zeroRecords": "No se encontraron registros coincidentes",
				"paginate": {
				       "next": "Siguiente",
					   "previous": "Previo"
				},
	 	},
	    "columns": [
	        { "data": "Legajo" },
	        { "data": "ApeNom" },
	        { "data": "Par1" },
	        { "data": "Rec1" },
	        { "data": "Par2" },
	        { "data": "Rec2" },
	        { "data": "NF" },
	        { "data": "Situacion" }
	        	]
	});
	
	$("input").keyup(function(){
		  ID = $(this).attr("name");
		  Legajo = ID.substring(5,9);
		  var Par1 = parseInt($("input[name='Par1-"+Legajo+"']").val());
		  if(Par1 >= 6){
			  $("input[name='Rec1-"+Legajo+"']").prop('disabled', true);
		  }
		  else{
			  $("input[name='Rec1-"+Legajo+"']").prop('disabled', false);
		  }
		  var Rec1 = parseInt($("input[name='Rec1-"+Legajo+"']").val());
		  var Par2 = parseInt($("input[name='Par2-"+Legajo+"']").val());
		  if(Par2 >= 6){
			  $("input[name='Rec2-"+Legajo+"']").prop('disabled', true);
		  }
		  else{
			  $("input[name='Rec2-"+Legajo+"']").prop('disabled', false);
		  }
		  var Rec2 = parseInt($("input[name='Rec2-"+Legajo+"']").val());
		  var Nota;
		  if(isNaN(Rec1)){
			  Rec1 = 0;
		  }
		  if(isNaN(Rec2)){
			  Rec2 = 0;
		  }
		  if(!isNaN(Par1) && !isNaN(Par2)){
			  
			  if(Par1 > Rec1){
				  Nota = Par1
			  }
			  else{
				  Nota = Rec1
			  }
			  
			  if(Par2 > Rec2){
				  Nota = (Nota + Par2)/2
			  }
			  else{
				  Nota = (Nota + Rec2)/2
			  }
			  
			  $("input[name='NotF-"+Legajo+"']").val(Nota);
		  }
		  else{
			  $("input[name='NotF-"+Legajo+"']").val("");
		  }
		  
		});
	
	$("#btnCargarModal").click(function(){
		$("#FormCargar").submit();
	} )
});

function mostrarToast(R){
	if(R == "Cargado"){
		$("#toastTitle").html("Cargadas");
		$("#toastMsj").html("Se han cargado las notas exitosamente.");
	}
	$(".toast").toast('show');
}

function cargarNotas(){
	var table = $('#GridAlumnos').DataTable();
	//var Par1 = table.rows().data().pluck(2);
	//var Par1 = table.$('input').serializeArray();
	table.rows().invalidate();
	var data = table.rows().data();
	$.post("servletAlumnosCurso",{"Notas": data}, function(responseJson) {

	});
	
}

function mostrarModal(){
	$("#ModalRegistro").modal('show');
	document.getElementById("txtApellido").focus();
}
</script>
</html>