<%@page import="java.util.ArrayList" %>
<%@page import="Dominio.Curso" %>
<%@page import="Dominio.Materia" %>
<%@page import="Dominio.Persona" %>
<%@page import="Negocio.PersonaNegocio" %>
<%@page import="Negocio.CursoNegocio" %>
<%@page import="Negocio.MateriaNegocio"%>
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
<body>
<div id="Menu">
<jsp:include page="NavBarAdmin.html"></jsp:include>
 <div class="container" style="height: 100%">
 	<h2>Reportes</h2>
<div id="PanelFiltros" style="height: 62px; margin: 0 0 5px 0;">
<form id="FormFiltro" method="get" action="servletReportes">
  <div class="row">
    <div class="col colName">Materia:</div>
    <div class="col colName">Semestre:</div>
    <div class="col colName">Año:</div>
    <div class="col colName"></div>
    <div class="w-100"></div>
    <div class="col"><select id="slMateria" name="slMateria" class="custom-select">
    <option value="-1" class="dropdown-item">Todas</option>
	<%
    	MateriaNegocio negocioM = new MateriaNegocio();
	   	ArrayList<Materia> materias = negocioM.listarMaterias();
  	 	for (Materia m : materias){
			%><option value="<%=m.getID()%>"> <%=m.getNombre()%> </option><%
	   	}
 		%>
 		</select>></div>
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
	<div class="col colName"><button type="submit" name="btnFiltrar" id="btnFiltrar">Filtrar</button></div>
	</div>
</form>
</div>  	
  <table id="Gridview" class="table table-hover">
       <thead class="thead-dark">
            <tr>
                <th scope="col">Materia</th>
                <th scope="col">Cant. alumnos</th>
                <th scope="col">Cant. regularizados</th>
                <th scope="col">Porcentaje</th>
            </tr>
        </thead>
        <tbody id="grilla">
        		<%
			String listadoReporte = null;
			if(request.getAttribute("tabla")!=null)
			{
				listadoReporte = (String)request.getAttribute("tabla");
			}
		%>
        <%=listadoReporte%>
        </tbody>
    </table>
 </div> 
</div>  
</body>
<script type="text/javascript">
 <%if(request.getAttribute("Materia") != null)
	{
		int Mat = (int)request.getAttribute("Materia");%>
		$('#slMateria').val(<%=Mat%>);	
	<%}
    if(request.getAttribute("Semestre") != null)
	{
		int Sem = (int)request.getAttribute("Semestre");%>
		$('#slSemestre').val(<%=Sem%>);
	<%}
    if(request.getAttribute("Año") != null)
	{
		int Año = (int)request.getAttribute("Año");%>
		$('#slAño').val(<%=Año%>);
	<%}
%>

$(document).ready(function(){
	CurrentItem = document.getElementById("mnReportes");
	CurrentItem.className +=" active";

	$('#Gridview').DataTable({
		"ordering":false,
		"bInfo": false,
		"lengthChange": false,
		"pageLength": -1,
		"dom": 'rti',
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



</script>
</html>

