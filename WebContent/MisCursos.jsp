<%@page import="Negocio.PersonaNegocio" %>
<%@page import="Dominio.Persona" %>
<%@page import="java.util.ArrayList" %>
<%@page import="Negocio.MateriaNegocio" %>
<%@page import="Dominio.Materia" %>
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
if(session.getAttribute("IDProfesor") == null) {
	response.sendRedirect("./Inicio.jsp");
}
else if(request.getAttribute("tabla") ==null){
	response.sendRedirect("./servletMisCursos");
}
%>
<body onresize="cantidadPaginas()">
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
    <div class="w-100"></div>
    <div class="col"><input type="text" id="txtBusqueda" class="form-control"></div>
    <div class="col"><select id="slMateria" name="slMateria" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todas</option> 
				<%
		    	MateriaNegocio negocioM = new MateriaNegocio();
			   	ArrayList<Materia> materias = negocioM.listarMaterias();
		  	 	for (Materia m : materias){
					%><option value="<%=m.getID()%>"> <%=m.getNombre()%> </option><%
			   	}
		 		%>
	 			 </select></div>
    <div class="col"><select id="slSemestre" name="slSemestre" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todos</option>
	 		 	<option value="1" class="dropdown-item">Primer</option>
	 		 	<option value="2" class="dropdown-item">Segundo</option> 
	 			 </select></div>
    <div class="col"><select id="slAño" name="slAño" class="custom-select">
	 		 	<option value="-1" class="dropdown-item">Todos</option>
	 		 	<option value="1" class="dropdown-item">2019</option>
	 		 	<option value="2" class="dropdown-item">2018</option>
	 		 	<option value="2" class="dropdown-item">2017</option> 
	 			 </select></div>
	</div>
</div> 
    <form id="FormAlumnos" method="post" action="servletMisCursos">
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
			<%
			String listadoCursos = null;
			if(request.getAttribute("tabla")!=null)
			{
				listadoCursos = (String)request.getAttribute("tabla");
			}		
			%>
        <%=listadoCursos %>
				
	        </tbody>
	    </table>
    </div>
	<input type="hidden" value="" name="CursoSelect" id="CursoSelect">
    </form>
</div>
</div>
</body>
<script type="text/javascript">
<%
String nombreProfesor = null;
if(request.getAttribute("NombreP")!=null)
{
	nombreProfesor = (String)request.getAttribute("NombreP");
	%> $("#NombreProfesor").html("<%=nombreProfesor%>")<%
}		
%>

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
		
		CurrentItem = document.getElementById("mnMisCursos");
		CurrentItem.className +=" active";		
		$("#TableAlumnosCurso").hide();
		$("#TituloAlumnos").hide();
		$("#btnVolver").hide();
		
		$(".btn-primary").on("click", function(){
			var button = $(this);
			$("#CursoSelect").val(button.val());
		});
		
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
		
		$("#txtBusqueda").on( 'keyup', function () {
			$('#Gridview').DataTable().search( this.value ).draw();
		});
		
		$("#slMateria").on( 'change', function () {
	 		if($("#slMateria").val() > -1){
				$('#Gridview').DataTable().column(1).search( $("#slMateria option:selected").text() ).draw();	
			}
			else{
				$('#Gridview').DataTable().column(1).search("").draw();		
			}
		});
		
		$("#slSemestre").on( 'change', function () {
			if($("#slSemestre").val() > -1){
				$('#Gridview').DataTable().column(2).search( $("#slSemestre option:selected").text()).draw();	
			}
			else{
				$('#Gridview').DataTable().column(2).search("").draw();		
			}
		});
		
		$("#slAño").on( 'change', function () {
			if($("#slAño").val() > -1){
				$('#Gridview').DataTable().column(3).search( $("#slAño option:selected").text() ).draw();	
			}
			else{
				$('#Gridview').DataTable().column(3).search("").draw();		
			}
		});
		
	});
	
	function mostrarAlumnos(IDCurso){
		$.post("servletCurso",{"setIDCurso": IDCurso}, function(responseJson) {
			
		});
		
	}
	
	function cambiarEstadoFiltros(estado){
		$('#slMateria').attr('disabled', estado)
	}

</script>
</html>