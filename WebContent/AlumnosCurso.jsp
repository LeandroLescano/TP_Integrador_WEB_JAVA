<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
<script type="text/javascript" src="js/funciones.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">
<meta charset="ISO-8859-1">
<title>Gestor educativo</title>
</head>
<jsp:include page="ModalPersona.html"></jsp:include>
<body onresize="cantidadPaginas()">
<jsp:include page="NavBarProfesor.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%">
<div id="Titulo">
	<span><a id="btnVolver" href="./MisCursos.jsp" class="btn btn-primary" style="float: right;" onclick="ocultarAlumnos()">Volver a Mis cursos</a></span>
	<h2>Curso X - Alumnos </h2>
</div>     
<form id="FormNotas" method="get" action="servletNotas">
	<input class="btn btn-primary" type="submit" value="Cargar notas" name="btnAgregar" style="float: right; margin-bottom: 5px;">
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
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	var screenH = window.innerHeight;
	var cantPags;
	if(screenH < 615){
		cantPags = 3;
	}
	else if(screenH < 680){
		cantPags = 4;
	}
	else if(screenH < 740){
		cantPags = 5;
	}
	else{
		cantPags = 6;
	}


	$("#TituloModal").html("Añadir alumno");
	
	$('#GridAlumnos').DataTable({
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
});

function mostrarModal(){
	$("#ModalRegistro").modal('show');
	document.getElementById("txtApellido").focus();
}
</script>
</html>