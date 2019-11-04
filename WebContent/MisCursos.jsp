<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="Negocio.AlumnoNegocio" %>
<%@page import="Dominio.Alumno" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="styles.css">
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
<body>
<jsp:include page="NavBarProfesor.html"></jsp:include>
<div style="height: 85vh">
<div class="container" style="height: 100%;">
<div id="Titulo">
	<h2 id="TituloCursos">Mis cursos</h2>
</div>
<div id="PanelFiltros" style="height: 62px">
  <div class="row">
    <div class="col colName">Busqueda:</div>
    <div class="col colName">Materia:</div>
    <div class="col colName">Semestre:</div>
    <div class="col colName">Año:</div>
    <div class="col colName"></div>
    <div class="w-100"></div>
    <div class="col"><input type="text" id="txtBusqueda" class="form-control"></div>
    <div class="col"><select id="slMateria" name="slMateria" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todas</option> 
	 		 	<option value="0" class="dropdown-item">Laboratorio IV</option>
	 			 </select></div>
    <div class="col"><select name="slSemestre" class="custom-select">
	 		 	<option value="0" class="dropdown-item">Todos</option> 
	 			 </select></div>
    <div class="col"><select name="slDocente" class="custom-select">
	 		 	<option value="0" class="dropdown-item">Todos</option> 
	 			 </select></div>
	 			  <div class="col"><button class="btn btn-primary btn-add" >Añadir curso</button></div>	
	</div>
</div> 
	<div id="TableCursos">
	 <table id="Gridview" class="table table-hover">
	        <thead class="thead-dark">
	            <tr>
	                <th scope="col">Nro</th>
	                <th scope="col">Materia</th>
	                <th scope="col">Semestre</th>
	                <th scope="col">Año</th>
	                <th scope="col" class="colDetalles">Detalles</th>
	            </tr>
	        </thead>
	        <tbody id="grilla">
	            <tr>
			      <th scope="row">1</th>
			      <td>Materia1</td>
			      <td>Semestre1</td>
			      <td>2019</td>
			      <td><a href="#" id="IDCURSO" onclick="mostrarAlumnos(this.id)" class="btn btn-primary">Ver alumnos</a></td>
	   			 </tr>
 	             <tr>
			      <th scope="row">2</th>
			      <td>Materia2</td>
			      <td>Semestre2</td>
			      <td>2019</td>
			      <td><a href="#" id="IDCURSO" onclick="mostrarAlumnos(this.id)" class="btn btn-primary">Ver alumnos</a></td>
	   			 </tr>
	        </tbody>
	    </table>
    </div>
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
		
		CurrentItem = document.getElementById("mnMisCursos");
		CurrentItem.className +=" active";		
		$("#TableAlumnosCurso").hide();
		$("#TituloAlumnos").hide();
		$("#btnVolver").hide();
		
		$('#Gridview').DataTable({
			"ordering":false,
			"bInfo": false,
			"lengthChange": false,
			"pageLength": cantPags,
			"dom":'rtip',
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
	
	function mostrarAlumnos(){
		TablaCurso = document.getElementById("TableCursos");
		TablaCurso.className ="table table-hover slideOutLeft";
		
		TablaAlumn = document.getElementById("TableAlumnosCurso");
		$("#TableAlumnosCurso").show(400);
		TablaAlumn.className = "table table-hover slideInRight";
		$("#TituloCursos").hide()
		$("#TituloAlumnos").show();
		$("#btnVolver").show();
		cambiarEstadoFiltros(true);
	}
	
	function ocultarAlumnos(){
		TablaCurso = document.getElementById("TableCursos");
		TablaCurso.className = "table table-hover slideInLeft";
		
		TablaAlumn = document.getElementById("TableAlumnosCurso");
		TablaAlumn.className = "table table-hover slideOutRight";
		$("#TituloAlumnos").hide()
		$("#TituloCursos").show();
		$("#btnVolver").hide();
	}
	
	function cambiarEstadoFiltros(estado){
		$('#slMateria').attr('disabled', estado)
	}

</script>
</html>